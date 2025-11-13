import java.util.List;

public class TesteDeezer {
    public static void main(String[] args) {
        DeezerAPI api = new DeezerAPI();

        System.out.println("ARTISTA");
        List<Musica> artista = api.buscarMusicasArtista("lana del rey");
        for (Musica musica : artista) {
            System.out.println(musica.getTitulo() + " - " + musica.getArtista());
        }

        System.out.println();
        System.out.println("MUSICA");
        List<Musica> musica = api.buscarMusicaEspecifica("summertime sadness");
        for (Musica m : musica) {
            System.out.println(m.getTitulo() + " - " + m.getArtista());
        }

        System.out.println();
        System.out.println("ALBUM");
        List<Musica> album = api.buscarMusicasAlbum("lana del rey", "born to die");
        for (Musica m : album) {
            System.out.println(m.getTitulo() + " - " + m.getArtista());
        }
    }
}