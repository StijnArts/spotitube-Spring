package han.stijn.spotitube.datasource.dao;

import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.dto.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PlaylistDAOTest {

    private static SpringPreparedStatementHelper mockedStatementHelper = Mockito.mock(SpringPreparedStatementHelper.class, Mockito.RETURNS_MOCKS);
    private static ResultSetReader mockedResultSetReader = Mockito.mock(ResultSetReader.class);
    private static TrackDAO mockedTrackDAO = Mockito.mock(TrackDAO.class);
    private static PlaylistDAO sut = new PlaylistDAO();
    private static TrackDTO track = new SongDTO(0,"title", "performer",
        0,0,"album",false);
    @BeforeAll
    public static void setup() throws SQLException {
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);
        sut.setPreparedStatementHelper(mockedStatementHelper);
        sut.setResultSetReader(mockedResultSetReader);
        sut.setTracksDAO(mockedTrackDAO);
        Mockito.doReturn(0).when(mockedResultSetReader)
                .readInteger(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("PLAYLISTID"));
        Mockito.doReturn("title").when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("NAME"));
        Mockito.doReturn("username").when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("OWNER"));
        Mockito.doReturn(new TracksDTO(tracks)).when(mockedTrackDAO)
                .getTracks(ArgumentMatchers.anyInt());
    }

    @Test
    public void getPlaylists() throws SQLException {
        //Arrange
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);
        PlaylistDTO testPlaylist = new PlaylistDTO(0,"title",false,tracks);
        PlaylistsDTO testPlaylists = new PlaylistsDTO(List.of(testPlaylist),0);
        Mockito.doReturn(true,false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act

        //Assert
        for (PlaylistDTO playlist :
                sut.getPlaylists("token").getPlaylists()) {
            assertEquals(playlist.toString(),testPlaylist.toString());
        }
    }

    @Test
    public void checkOwnerShipNotOwner() throws SQLException {
        //Arrange
        Mockito.doReturn("notOwner").when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("USERNAME"));
        Mockito.doReturn(true,false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //ACT

        //ASSERT
        assertEquals(false, sut.checkOwnership("token","owner"));
    }

    @Test
    public void checkOwnerShipIsOwner() throws SQLException {
        //Arrange
        Mockito.doReturn("owner").when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("USERNAME"));
        Mockito.doReturn(true,false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //ACT

        //ASSERT
        assertEquals(true, sut.checkOwnership("token","owner"));
    }

    @Test
    public void deletePlaylistNotOwner() throws SQLException {
        //Arrange
        Mockito.doReturn(false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act
        sut.deletePlaylist(0,"token");
        //Assert
        Mockito.verify(mockedStatementHelper, Mockito.times(0)).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }

    @Test
    public void deletePlaylistIsOwner() throws SQLException {
        //Arrange
        Mockito.doReturn(true).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act
        sut.deletePlaylist(0,"token");
        //Assert
        Mockito.verify(mockedStatementHelper, Mockito.times(1)).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }

    @Test
    public void addPlaylist() throws SQLException {
        //Arrange
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);
        NewPlaylistDTO newPlaylistDTO = new NewPlaylistDTO(0,"title",false,tracks);
        //Act
        sut.addPlaylist(newPlaylistDTO,"token");
        //Assert
        Mockito.verify(mockedStatementHelper, Mockito.times(3)).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }

    @Test
    public void editPlaylistIsOwner() throws SQLException {
        //Arrange
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);
        NewPlaylistDTO newPlaylistDTO = new NewPlaylistDTO(0,"title",false,tracks);
        Mockito.doReturn(true).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act
        sut.editPlaylist(newPlaylistDTO,"token",0);
        //Assert
        Mockito.verify(mockedStatementHelper, Mockito.times(2)).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }

    @Test
    public void editPlaylistIsNotOwner() throws SQLException {
        //Arrange
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);
        NewPlaylistDTO newPlaylistDTO = new NewPlaylistDTO(0,"title",false,tracks);
        Mockito.doReturn(false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act
        sut.editPlaylist(newPlaylistDTO,"token",0);
        //Assert
        Mockito.verify(mockedStatementHelper, Mockito.times(3)).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }
}
