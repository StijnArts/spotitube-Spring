package han.stijn.spotitube.resource;

import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static java.net.HttpURLConnection.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrackResourceTest {

    private static TrackResource sut = new TrackResource();
    private static LoginDAO mockedLoginDAO = Mockito.mock(LoginDAO.class);
    private static TrackDAO mockedTrackDAO = Mockito.mock(TrackDAO.class);
    private static TrackDTO track = new SongDTO(0,"title", "performer",
            0,0,"album",false);
    @BeforeAll
    public static void setup(){
        sut.setTracksDAO(mockedTrackDAO);
        sut.setLoginDAO(mockedLoginDAO);
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);

        Mockito.doReturn(new TracksDTO(tracks)).when(mockedTrackDAO)
                .getTracks(ArgumentMatchers.anyInt());
        Mockito.doReturn(new TracksDTO(tracks)).when(mockedTrackDAO)
                .getAvailableTracks(ArgumentMatchers.anyInt());
    }

    @Test
    public void executeGetTracksWithValidToken(){
        //Arrange
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        //Assert
        assertEquals(HTTP_OK, sut.getTracks(0,"token").getStatusCode());
    }

    @Test
    public void executeGetTracksWithInvalidToken(){
        //Arrange
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        //Assert
        assertEquals(HTTP_FORBIDDEN, sut.getTracks(0,"wrongtoken").getStatusCode());
    }

    @Test
    public void executeGetAvailableTracksWithValidToken(){
        //Arrange
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        //Assert
        assertEquals(HTTP_OK, sut.getAvailableTracks(0,"token").getStatusCode());
    }

    @Test
    public void executeGetAvailableWithInvalidToken(){
        //Arrange
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        //Assert
        assertEquals(HTTP_FORBIDDEN, sut.getAvailableTracks(0,"wrongtoken").getStatusCode());
    }


}
