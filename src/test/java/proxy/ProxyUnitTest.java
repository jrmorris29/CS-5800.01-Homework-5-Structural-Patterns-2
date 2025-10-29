package proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProxyUnitTest {
    private SongService proxyService;
    private SongService realService;

    @BeforeEach
    public void setup() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Test Song", "Artist1", "Album1", 300));
        songs.add(new Song(2, "Another Song", "Artist2", "Album2", 250));
        realService = new RealSongService(songs);
        proxyService = new ProxySongService(realService);
    }

    @Test
    public void testCacheById() {
        long start = System.currentTimeMillis();
        proxyService.searchById(1);
        long firstCall = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        proxyService.searchById(1);
        long secondCall = System.currentTimeMillis() - start;

        assertTrue(firstCall > secondCall, "Cached call should be faster than real server call");
    }

    @Test
    public void testSearchByTitle() {
        List<Song> songs = proxyService.searchByTitle("Test Song");
        assertEquals(1, songs.size());
        assertEquals("Artist1", songs.get(0).getArtist());
    }

    @Test
    public void testSearchByAlbumCaching() {
        proxyService.searchByAlbum("Album2");
        proxyService.searchByAlbum("Album2");
        assertNotNull(proxyService.searchByAlbum("Album2"));
    }

    @Test
    public void testNonexistentSongReturnsNull() {
        Song song = proxyService.searchById(999);
        assertNull(song, "Searching for a nonexistent ID should return null");
    }

    @Test
    public void testMultipleCacheEntries() {
        proxyService.searchById(1);
        proxyService.searchById(2);
        assertNotNull(proxyService.searchById(1));
        assertNotNull(proxyService.searchById(2));
        assertNotEquals(proxyService.searchById(1), proxyService.searchById(2),
                "Each ID should retrieve its own cached song");
    }


}
