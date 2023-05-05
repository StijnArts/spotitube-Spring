package han.stijn.spotitube.dto;

import java.util.*;

public class PlaylistsDTO {
    private List<PlaylistDTO> playlists =new ArrayList<>();
    private int length;

    public PlaylistsDTO(){

    }

    public PlaylistsDTO(List<PlaylistDTO> playlists, int length){
        this.playlists=playlists;
        this.length = length;
    }

    @Override
    public String toString() {
        String string = "";
        for (PlaylistDTO playlist :
                playlists) {
            string += playlist.toString()+"\n";
        }
        return string;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
