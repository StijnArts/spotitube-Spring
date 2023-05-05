package han.stijn.spotitube.resource;


import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import jakarta.ws.rs.core.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static java.net.HttpURLConnection.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlaylistResourceTest {

    private PlaylistResource sut;
    private PlaylistDAO mockedPlaylistDAO = Mockito.mock(PlaylistDAO.class);

    private static LoginDAO mockedLoginDAO = Mockito.mock(LoginDAO.class);
    private static TrackDAO mockedTrackDAO = Mockito.mock(TrackDAO.class);
    private static TrackResource mockedTrackResource = Mockito.mock(TrackResource.class);
    private static TrackDTO track = new SongDTO(0,"title", "performer",
            0,0,"album",false);
    private static ArrayList<TrackDTO> tracks = new ArrayList<>();
    private static TracksDTO tracksDTO = new TracksDTO(tracks);
    @BeforeEach
    public void setup(){
        this.sut = new PlaylistResource();
        this.sut.setPlaylistDAO(mockedPlaylistDAO);
        sut.setTrackDAO(mockedTrackDAO);
        sut.setLoginDAO(mockedLoginDAO);
        sut.setTrackResource(mockedTrackResource);
        tracks.add(track);
        Mockito.doReturn(new TracksDTO(tracks)).when(mockedTrackDAO)
                .getTracks(ArgumentMatchers.anyInt());
        Mockito.doReturn(new TracksDTO(tracks)).when(mockedTrackDAO)
                .getAvailableTracks(ArgumentMatchers.anyInt());
        Mockito.doReturn(Response.status(200).build()).when(mockedTrackResource).getTracks(ArgumentMatchers.anyInt(),ArgumentMatchers.anyString());
    }

    @Test
    public void executeGetPlaylistWithValidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(testPlaylists);
        PlaylistsDTO playlistsDTO = new PlaylistsDTO(playlists,0);
        Mockito.when(mockedPlaylistDAO.getPlaylists("token")).thenReturn(playlistsDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.getPlaylists("token");
        //Assert
        assertEquals(HTTP_OK,response.getStatus());
    }

    @Test
    public void executeGetPlaylistWithInvalidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(testPlaylists);
        PlaylistsDTO playlistsDTO = new PlaylistsDTO(playlists,0);
        Mockito.when(mockedPlaylistDAO.getPlaylists("token")).thenReturn(playlistsDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.getPlaylists("this token is wrong");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }

    @Test
    public void executeDeletePlaylistWithValidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(testPlaylists);
        PlaylistsDTO playlistsDTO = new PlaylistsDTO(playlists,0);
        Mockito.when(mockedPlaylistDAO.getPlaylists("token")).thenReturn(playlistsDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.deletePlaylist(0,"token");
        //Assert
        assertEquals(HTTP_OK,response.getStatus());
    }

    @Test
    public void executeDeletePlaylistWithInvalidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(testPlaylists);
        PlaylistsDTO playlistsDTO = new PlaylistsDTO(playlists,0);
        Mockito.when(mockedPlaylistDAO.getPlaylists("token")).thenReturn(playlistsDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.deletePlaylist(0,"this token is wrong");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }

    @Test
    public void executeAddTrackToPlaylistWithValidTokenWithFailure(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.when(mockedTrackDAO.addTrackToPlaylist(0,0)).thenReturn(false);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.addTrackToPlaylist(track,0,"token");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }

    @Test
    public void executeAddTrackToPlaylistWithValidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.when(mockedTrackDAO.addTrackToPlaylist(0,0)).thenReturn(true);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.addTrackToPlaylist(track,0,"token");
        //Assert
        assertEquals(HTTP_OK,response.getStatus());
    }

    @Test
    public void executeAddTrackToPlaylistWithInvalidToken(){
        //Arrange
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.addTrackToPlaylist(track,0,"this token is wrong");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }

    @Test
    public void executeDeleteTrackFromPlaylistWithValidTokenWithFailure(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.when(mockedTrackDAO.deleteTrackFromPlaylist(0,0)).thenReturn(false);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.deleteTrackFromPlaylist(0,0,"token");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }

    @Test
    public void executeDeleteTrackFromPlaylistWithValidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.when(mockedTrackDAO.deleteTrackFromPlaylist(0,0)).thenReturn(true);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.deleteTrackFromPlaylist(0,0,"token");
        //Assert
        assertEquals(HTTP_OK,response.getStatus());
    }

    @Test
    public void executeDeleteTrackFromPlaylistWithInvalidToken(){
        //Arrange
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.deleteTrackFromPlaylist(0,0,"this token is wrong");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }

    @Test
    public void executeAddPlaylistWithValidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.when(mockedTrackDAO.deleteTrackFromPlaylist(0,0)).thenReturn(true);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.addPlaylist(new NewPlaylistDTO(0,"title",false,tracks),"token");
        //Assert
        assertEquals(HTTP_OK,response.getStatus());
    }

    @Test
    public void executeAddPlaylistWithInvalidToken(){
        //Arrange
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.addPlaylist(new NewPlaylistDTO(0,"title",false,tracks),"wrongtoken");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }

    @Test
    public void executeEditPlaylistWithValidToken(){
        //Arrange
        PlaylistDTO testPlaylists = new PlaylistDTO();
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.when(mockedTrackDAO.deleteTrackFromPlaylist(0,0)).thenReturn(true);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.editPlaylist(new NewPlaylistDTO(0,"title",false,tracks),0,"token");
        //Assert
        assertEquals(HTTP_OK,response.getStatus());
    }

    @Test
    public void executeEditPlaylistWithInvalidToken(){
        //Arrange
        Mockito.when(mockedTrackDAO.getTracks(0)).thenReturn(tracksDTO);
        Mockito.doReturn(true).when(mockedLoginDAO).checkToken("token");
        //Act
        var response = sut.editPlaylist(new NewPlaylistDTO(0,"title",false,tracks),0,"wrongtoken");
        //Assert
        assertEquals(HTTP_FORBIDDEN,response.getStatus());
    }
}
