package han.stijn.spotitube.dto;

public class SongDTO extends TrackDTO{

    public SongDTO(){

    }
    public SongDTO(Integer id, String titel, String performer, Integer afspeelduurInSecondes,
                   Integer playcount, String album, boolean offlineAvailable) {
        super(id, titel, performer, afspeelduurInSecondes, playcount, offlineAvailable);
        setAlbum(album);
    }
}
