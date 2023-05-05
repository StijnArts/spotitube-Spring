package han.stijn.spotitube.datasource.dao;

import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.dto.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TrackDAOTest {
    private static SpringPreparedStatementHelper mockedStatementHelper = Mockito.mock(SpringPreparedStatementHelper.class, Mockito.RETURNS_MOCKS);
    private static ResultSetReader mockedResultSetReader = Mockito.mock(ResultSetReader.class);
    private static TrackDAO sut = new TrackDAO();

    @BeforeAll
    public static void setup() throws SQLException {
        sut.setPreparedStatementHelper(mockedStatementHelper);
        sut.setResultSetReader(mockedResultSetReader);
        Mockito.doReturn(0).when(mockedResultSetReader)
                .readInteger(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("TRACKID"));
        Mockito.doReturn("title").when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("TRACKTITLE"));
        Mockito.doReturn("performer").when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("PERFORMER"));
        Mockito.doReturn(0).when(mockedResultSetReader)
                .readInteger(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("TRACKDURATION"));
        Mockito.doReturn(0).when(mockedResultSetReader)
                .readInteger(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("PLAYCOUNT"));
        Mockito.doReturn(false).when(mockedResultSetReader)
                .readBoolean(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("OFFLINEAVAILABLE"));
        Mockito.doReturn("album").when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("ALBUM"));
        Mockito.doReturn(0).when(mockedResultSetReader)
                .readInteger(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("PUBLICATIONDATE"));
        Mockito.doReturn(null).when(mockedResultSetReader)
                .readString(ArgumentMatchers.any(ResultSet.class),ArgumentMatchers.contains("TRACKDESCRIPTION"));
    }

    @Test
    public void getTracksTest() throws SQLException {
        //Arrange
        TrackDTO track = new SongDTO(0,"title", "performer",
                0,0,"album",false);
        Mockito.doReturn(true,false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act

        //Assert
        for (TrackDTO sutDTO :
                sut.getTracks(0).getTracks()) {
            assertEquals(sutDTO.toString(),track.toString());
        }
    }

    @Test
    public void getAvailableTracksTest() throws SQLException {
        //Arrange
        TrackDTO track = new SongDTO(0,"title", "performer",
                0,0,"album",false);
        Mockito.doReturn(true,false).when(mockedResultSetReader).determineNextInResult(ArgumentMatchers.any(ResultSet.class));
        //Act

        //Assert
        for (TrackDTO sutDTO : sut.getAvailableTracks(0).getTracks()) {
            assertEquals(sutDTO.toString(),track.toString());
        }
    }

    @Test
    public void addTrackToPlaylist(){
        //Arrange

        //Act
        sut.addTrackToPlaylist(0,0);
        //Assert
        Mockito.verify(mockedStatementHelper).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }

    @Test
    public void deleteTrackFromPlaylist(){
        //Arrange

        //Act
        sut.deleteTrackFromPlaylist(0,0);
        //Assert
        Mockito.verify(mockedStatementHelper,Mockito.times(2)).executeQueryWithoutResultWithTransaction(ArgumentMatchers.anyString(),ArgumentMatchers.isA(ArrayList.class));
    }
}
