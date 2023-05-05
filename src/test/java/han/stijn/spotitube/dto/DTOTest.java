package han.stijn.spotitube.dto;
import com.openpojo.reflection.*;
import com.openpojo.reflection.filters.FilterNonConcrete;
import com.openpojo.reflection.impl.*;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.*;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import han.stijn.spotitube.dto.factory.*;
import org.junit.jupiter.api.*;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
public class DTOTest {

    private static ArrayList<TrackDTO> tracks = new ArrayList<>();

    @BeforeAll
    public static void setup(){
        tracks.add(new VideoDTO(0,"title","performer",0,
                0,0,"description",false));
    }


    @Test
    public void testGettersSettersDTO(){
        Validator validator = ValidatorBuilder.create().with(new SetterTester()).with(new GetterTester()).build();
        List<PojoClass> testClasses = PojoClassFactory.getPojoClasses("han.stijn.spotitube.dto", null);
        testClasses.remove(PojoClassFactory.getPojoClass(TrackFactory.class));
        validator.validate(testClasses);
    }

    @Test
    public void constructLoginDTO(){
        //Arrange
        LoginDTO testDTO = new LoginDTO("user","password123");
        LoginDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new LoginDTO("user","password123");
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
    }

    @Test
    public void constructUserDTO(){
        //Arrange
        UserDTO testDTO = new UserDTO("user","token");
        UserDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new UserDTO("user","token");
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
    }

    @Test
    public void constructSongDTO(){
        //Arrange
        SongDTO testDTO = new SongDTO(0,"title","performer",0,
                0,"album",false);
        SongDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new SongDTO(0,"title","performer",0,
                0,"album",false);
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
    }

    @Test
    public void constructVideoDTO(){
        //Arrange
        VideoDTO testDTO = new VideoDTO(0,"title","performer",0,
                0,0,"description",false);
        VideoDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new VideoDTO(0,"title","performer",0,
                0,0,"description",false);
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
    }

    @Test
    public void constructNewPlaylistDTO(){
        //Arrange
        NewPlaylistDTO testDTO = new NewPlaylistDTO(0,"title",false,tracks);
        NewPlaylistDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new NewPlaylistDTO(0,"title",false,tracks);
        sut.setTracksDTO(tracks);
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
        assertEquals(testDTO.getTracksDTO(),sut.getTracksDTO());
    }

    @Test
    public void constructPlaylistDTO(){
        //Arrange
        PlaylistDTO testDTO = new PlaylistDTO(0,"title",false,tracks);
        PlaylistDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new PlaylistDTO(0,"title",false,tracks);
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
    }

    @Test
    public void constructPlaylistsDTO(){
        //Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO(0,"title",false,tracks);
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(playlistDTO);
        PlaylistsDTO testDTO = new PlaylistsDTO(playlists,0);
        PlaylistsDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new PlaylistsDTO(playlists,0);
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
    }

    @Test
    public void constructTrackDTO(){
        //Arrange
        TrackDTO testDTO =  new TrackDTO(0,"title","performer",0,
                0,false);
        TrackDTO sut;
        String testDTOToString;
        String sutToString;
        //Act
        sut = new TrackDTO(0,"title","performer",0,
                0,false);
        sutToString = sut.toString();
        testDTOToString = testDTO.toString();
        //Assert
        assertEquals(testDTOToString,sutToString);
    }
}
