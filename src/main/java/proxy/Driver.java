package proxy;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Viva La Vida", "Coldplay", "Viva La Vida", 242));
        songs.add(new Song(2, "Fix You", "Coldplay", "X&Y", 295));
        songs.add(new Song(3, "Yellow", "Coldplay", "Parachutes", 268));
        songs.add(new Song(4, "Lose Yourself", "Eminem", "8 Mile", 326));
        songs.add(new Song(5, "Stan", "Eminem", "The Marshall Mathers LP", 404));
        songs.add(new Song(6, "Shape of You", "Ed Sheeran", "Divide", 263));
        songs.add(new Song(7, "Blinding Lights", "The Weeknd", "After Hours", 200));

        SongService realService = new RealSongService(songs);
        SongService proxyService = new ProxySongService(realService);

        System.out.println("\n--- First call (real server) ---");
        System.out.println(proxyService.searchById(1));

        System.out.println("\n--- Second call (real server) ---");
        System.out.println(proxyService.searchByAlbum("8 Mile"));

        System.out.println("\n--- Third call (real server) ---");
        System.out.println(proxyService.searchByTitle("Yellow"));

        System.out.println("\n--- Fourth call (real server) ---");
        System.out.println(proxyService.searchById(5));

        System.out.println("\n--- Fifth call (real server) ---");
        System.out.println(proxyService.searchByAlbum("Divide"));

        System.out.println("\n--- Cached call 1 (cache) ---");
        System.out.println(proxyService.searchById(1));

        System.out.println("\n--- Cached call 2 (cache) ---");
        System.out.println(proxyService.searchByAlbum("8 Mile"));

        System.out.println("\n--- Cached call 3 (cache) ---");
        System.out.println(proxyService.searchByTitle("Yellow"));

        System.out.println("\n--- Cached call 4 (cache) ---");
        System.out.println(proxyService.searchById(5));

        System.out.println("\n--- Cached call 5 (cache) ---");
        System.out.println(proxyService.searchByAlbum("Divide"));
    }
}
