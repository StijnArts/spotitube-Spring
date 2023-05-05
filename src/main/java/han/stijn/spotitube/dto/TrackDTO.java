package han.stijn.spotitube.dto;

public class TrackDTO {
    private Integer id;
    private String title;
    private String performer;
    private Integer duration;
    private Integer playcount;
    private boolean offlineAvailable;
    private Integer publicationDate;
    private String description;

    @Override
    public String toString() {
        return "Id: "+id+". Title: "+title+". Performer: "+performer+". Duration: "+duration+". Playcount: "+playcount+
                ". publicationDate: "+publicationDate+". Descriptoin: "+description+". Offline Available: "+offlineAvailable;
    }
    public TrackDTO(){}
    public TrackDTO(Integer id, String title, String performer, Integer duration,
                    Integer playcount, boolean offlineAvailable){
        this.id=id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playcount = playcount;
        this.offlineAvailable = offlineAvailable;
    }
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String album;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
