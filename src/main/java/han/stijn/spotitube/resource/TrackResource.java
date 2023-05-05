package han.stijn.spotitube.resource;

import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import jakarta.websocket.server.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@Component
@RestController
public class TrackResource {

    private LoginDAO loginDAO;
    private TrackDAO trackDAO;

    @GetMapping("/playlists/{id}/tracks")
    @ResponseBody
    public ResponseEntity<TracksDTO> getTracks(@PathVariable Integer id, @RequestParam String token){
        if(loginDAO.checkToken(token)) {
            return ResponseEntity.ok(trackDAO.getTracks(id));
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/tracks")
    @ResponseBody
    public ResponseEntity<TracksDTO> getAvailableTracks(@PathParam("forPlaylist")int playlistID, @RequestParam String token){
        if(loginDAO.checkToken(token)) {
            return ResponseEntity.ok(trackDAO.getAvailableTracks(playlistID));
        }
        return ResponseEntity.status(403).build();
    }

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Autowired
    public void setTracksDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
