package han.stijn.spotitube.datasource.dao;

import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.dto.*;
import han.stijn.spotitube.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Component
public class PlaylistDAO {

    private TrackDAO trackDAO;
    private ResultSetReader resultSetReader;
    private PreparedStatementHelper preparedStatementHelper;

    public PlaylistsDTO getPlaylists(String token){
            String query = "SELECT TOP 10 pl.PLAYLISTID as [PLAYLISTID], pl.[NAME] as [NAME], pl.[OWNER] as [OWNER] " +
                    "FROM [PLAYLIST] pl " +
                    "WHERE pl.[OWNER] IN (SELECT [USERNAME] FROM [USER] WHERE [TOKEN] = ?) " +
                    "UNION " +
                    "SELECT TOP 10 ps.PLAYLISTID as [PLAYLISTID], p.[NAME] as [NAME],p.[OWNER] as [OWNER] " +
                    "FROM PLAYLIST_SAVED_BY ps " +
                    "JOIN PLAYLIST p on p.PLAYLISTID = ps.PlaylistID " +
                    "where ps.USERNAME in (SELECT [USERNAME] FROM [USER] WHERE [TOKEN] = ?) ";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            parameters.add(SQLParameterFactory.createParameter(2,token));
            List<PlaylistDTO> playlists = (List<PlaylistDTO>) preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters, ((rs, rowNum) -> {
                return new PlaylistDTO(
                        resultSetReader.readInteger(rs,"PLAYLISTID"),
                        resultSetReader.readString(rs,"NAME"),
                        checkOwnership(token, resultSetReader.readString(rs,"OWNER")),
                        trackDAO.getTracks(resultSetReader.readInteger(rs,"PLAYLISTID")).getTracks());
            }));
        return new PlaylistsDTO(playlists,determineLength(playlists));
    }

    public void deletePlaylist(int id, String token) {
        if(checkOwnership(id, token)){
            var query = "DELETE FROM PLAYLIST WHERE PLAYLISTID = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,id));
            preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        }
    }

    @Autowired
    public void setTracksDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public boolean checkOwnership(String token, String owner){
            var query = "SELECT [USERNAME] FROM [USER] WHERE Token = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            return (boolean) preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters, (rs, rowSet)->{
                String foundUser = resultSetReader.readString(rs,"USERNAME");
                if(foundUser.equals(owner)){
                    return true;
                }
                return false;
            }).get(0);
    }

    public boolean checkOwnership(int id, String token){
            var query = "SELECT [OWNER] FROM PLAYLIST " +
                    "WHERE [OWNER] IN (SELECT [USERNAME] FROM [USER] WHERE Token = ?) AND PLAYLISTID = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            parameters.add(SQLParameterFactory.createParameter(2, id));
            return (Boolean) preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters, (rs, rowNum)->true).get(0);
    }

    public void addPlaylist(NewPlaylistDTO newPlaylistDTO, String token) {
        var query = "INSERT INTO PLAYLIST SELECT ?,?, [USERNAME] FROM [USER] where [Token] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,newPlaylistDTO.getId()));
        parameters.add(SQLParameterFactory.createParameter(2,newPlaylistDTO.getName()));
        parameters.add(SQLParameterFactory.createParameter(3,token));
        preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
    }

    public void editPlaylist(NewPlaylistDTO newPlaylistDTO, String token, int id) {
        if(checkOwnership(newPlaylistDTO.getId(), token)){
            var query = "UPDATE PLAYLIST SET [NAME] = ? WHERE PLAYLISTID = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,newPlaylistDTO.getName()));
            parameters.add(SQLParameterFactory.createParameter(2, id));
            preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
        }
    }
    @Autowired
    public void setResultSetReader(ResultSetReader resultSetReader) {
        this.resultSetReader = resultSetReader;
    }
    @Autowired
    public void setPreparedStatementHelper(SpringPreparedStatementHelper preparedStatementHelper) {
        this.preparedStatementHelper = preparedStatementHelper;
    }

    public int determineLength(List<PlaylistDTO> playlistDTOS){
        int length =0;
        for(PlaylistDTO playlist : playlistDTOS){
            for(TrackDTO track : playlist.getTracks()){
                length +=track.getDuration();
            }
        }

        return length;
    }
}
