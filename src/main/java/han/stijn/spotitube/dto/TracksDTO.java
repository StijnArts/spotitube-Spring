package han.stijn.spotitube.dto;

import han.stijn.spotitube.datasource.dao.*;

import java.util.*;

public class TracksDTO {
    private ArrayList<TrackDTO> tracks = new ArrayList<>();
    public TracksDTO(ArrayList<TrackDTO> tracks){
        this.tracks = tracks;
    }

    public TracksDTO(){

    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
