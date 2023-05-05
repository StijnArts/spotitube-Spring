package han.stijn.spotitube.dto;

import java.util.*;

public class NewPlaylistDTO {
    private int id;
    private String name;
    private boolean owner;
    private List<TrackDTO> tracks;

    @Override
    public String toString() {
        return "NewPlaylistDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", tracksDTO=" + tracks.toString() +
                '}';
    }

    public NewPlaylistDTO(){

    }
    public NewPlaylistDTO(int id, String name, boolean owner, List<TrackDTO> tracksDTO){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracksDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTracksDTO() {
        return tracks;
    }

    public void setTracksDTO(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
