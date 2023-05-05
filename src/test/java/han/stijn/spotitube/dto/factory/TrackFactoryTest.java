package han.stijn.spotitube.dto.factory;

import han.stijn.spotitube.dto.*;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;
public class TrackFactoryTest {

    @Test
    public void createSongDTO(){
        //Arrange
        SongDTO testDTO = new SongDTO(0,"title","performer",0,
                0,"album",false);
        //Act

        //Assert
        assertEquals(testDTO.toString(), TrackFactory.createTrack(0,"title","performer",0,
                0,"album",0,null,false).toString());
    }

    @Test
    public void createVideoDTO(){
        //Arrange
        VideoDTO testDTO = new VideoDTO(0,"title","performer",0,
                0,0,"description",false);
        //Act

        //Assert
        assertEquals(testDTO.toString(), TrackFactory.createTrack(0,"title","performer",0,
                0,null,0,"description",false).toString());
    }

    @Test
    public void createFactory(){
        //Arrange
        TrackFactory testFactory = new TrackFactory();
        TrackFactory sut;
        //Act
        sut = new TrackFactory();
        //Assert
        assertEquals(testFactory.toString(),sut.toString());
    }
}
