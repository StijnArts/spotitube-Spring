package han.stijn.spotitube.resource;

import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistResource {

    private LoginDAO loginDAO;

    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;

    private TrackResource trackResource;


    @GetMapping(value = "/playlists{token}")
    @ResponseBody
    public ResponseEntity<PlaylistsDTO> getPlaylists(@RequestParam String token){
        if(loginDAO.checkToken(token)) {
            return ResponseEntity.ok(playlistDAO.getPlaylists(token));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/playlists/{id}")
    @ResponseBody
    public ResponseEntity<PlaylistsDTO> deletePlaylist(@PathVariable("id") int id, @RequestParam String token){
        if(loginDAO.checkToken(token)) {
            playlistDAO.deletePlaylist(id, token);
            return getPlaylists(token);
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/playlists/{id}/tracks")
    @ResponseBody
    public ResponseEntity<TracksDTO> addTrackToPlaylist(TrackDTO trackDTO, @PathVariable("id") int playlistID, @RequestParam String token){
        if(loginDAO.checkToken(token)){
            if(trackDAO.addTrackToPlaylist(playlistID,trackDTO.getId())){
                return trackResource.getTracks(playlistID,token);
            }
        }
        return ResponseEntity.status(403).build();
    }

    @DeleteMapping("/playlists/{playlistID}/tracks/{trackID}")
    @ResponseBody
    public ResponseEntity<TracksDTO> deleteTrackFromPlaylist(@PathVariable("playlistID") int playlistID,@PathVariable("trackID") int trackID, @RequestParam String token){
        if(loginDAO.checkToken(token)){
            if(trackDAO.deleteTrackFromPlaylist(playlistID,trackID)){
                return trackResource.getTracks(playlistID,token);
            }
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/playlists")
    @ResponseBody
    public ResponseEntity<PlaylistsDTO> addPlaylist(@RequestBody NewPlaylistDTO newPlaylistDTO, @RequestParam String token){
        if(loginDAO.checkToken(token)){
            playlistDAO.addPlaylist(newPlaylistDTO, token);
            return getPlaylists(token);
        }
        return ResponseEntity.status(403).build();
    }

    @PutMapping("playlists/{id}")
    @ResponseBody
    public ResponseEntity<PlaylistsDTO> editPlaylist(NewPlaylistDTO newPlaylistDTO,@PathVariable("id") int id, @RequestParam String token){
        if(loginDAO.checkToken(token)){
            playlistDAO.editPlaylist(newPlaylistDTO, token, id);
            return getPlaylists(token);
        }
        return ResponseEntity.status(403).build();
    }

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Autowired
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Autowired
    public void setTrackResource(TrackResource trackResource) {
        this.trackResource = trackResource;
    }

    @Autowired
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
}
