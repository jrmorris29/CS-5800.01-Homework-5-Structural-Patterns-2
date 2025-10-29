package proxy;

import java.util.*;

public class ProxySongService implements SongService {
    private final SongService realService;
    private final Map<Integer, Song> cacheById = new HashMap<>();
    private final Map<String, List<Song>> cacheByTitle = new HashMap<>();
    private final Map<String, List<Song>> cacheByAlbum = new HashMap<>();

    public ProxySongService(SongService realService) {
        this.realService = realService;
    }

    @Override
    public Song searchById(Integer songID) {
        if (cacheById.containsKey(songID)) {
            System.out.println("Fetching song by ID from cache...");
            return cacheById.get(songID);
        }
        System.out.println("Fetching song by ID from real server...");
        Song song = realService.searchById(songID);
        if (song != null) cacheById.put(songID, song);
        return song;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        if (cacheByTitle.containsKey(title.toLowerCase())) {
            System.out.println("Fetching songs by Title from cache...");
            return cacheByTitle.get(title.toLowerCase());
        }
        System.out.println("Fetching songs by Title from real server...");
        List<Song> songs = realService.searchByTitle(title);
        cacheByTitle.put(title.toLowerCase(), songs);
        return songs;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        if (cacheByAlbum.containsKey(album.toLowerCase())) {
            System.out.println("Fetching songs by Album from cache...");
            return cacheByAlbum.get(album.toLowerCase());
        }
        System.out.println("Fetching songs by Album from real server...");
        List<Song> songs = realService.searchByAlbum(album);
        cacheByAlbum.put(album.toLowerCase(), songs);
        return songs;
    }
}

