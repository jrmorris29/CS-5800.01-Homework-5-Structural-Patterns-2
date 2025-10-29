package proxy;

import java.util.ArrayList;
import java.util.List;

public class RealSongService implements SongService {
    private final List<Song> songsDatabase;

    public RealSongService(List<Song> songsDatabase) {
        this.songsDatabase = songsDatabase;
    }

    @Override
    public Song searchById(Integer songID) {
        simulateNetworkDelay();
        for (Song song : songsDatabase) {
            if (song.getId() == songID) return song;
        }
        return null;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        simulateNetworkDelay();
        List<Song> results = new ArrayList<>();
        for (Song song : songsDatabase) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                results.add(song);
            }
        }
        return results;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        simulateNetworkDelay();
        List<Song> results = new ArrayList<>();
        for (Song song : songsDatabase) {
            if (song.getAlbum().equalsIgnoreCase(album)) {
                results.add(song);
            }
        }
        return results;
    }

    private void simulateNetworkDelay() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
