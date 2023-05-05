package han.stijn.spotitube.dto;

public class VideoDTO extends TrackDTO{

    public VideoDTO(Integer id, String title, String performer, Integer duration, Integer playcount, Integer publicationDate,
                    String description, boolean offlineAvailable) {
        super(id, title, performer, duration, playcount, offlineAvailable);
        setDescription(description);
        setPublicationDate(publicationDate);
    }

    public VideoDTO(){

    }
}
