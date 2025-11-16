import java.util.ArrayList;
import java.util.List;

public class AlbumController{
    private List<Album> albuns;
    private int nextId;

    public AlbumController(){
        this.albuns=new ArrayList<>();
        this.nextId= 1;
    }

    public Album criarAlbum(String titulo, String artista, String genero){
        Album album = new Album(nextId, titulo, artista, genero);
        albuns.add(album);
        nextId++;
        return album;
    }

    public boolean adicionarMusicaNoAlbum(int albumId, Musica musica){
        Album album= buscarAlbumPorId(albumId);
        if (album != null) {
            album.adicionarMusica(musica);
            return true;
        }
        return false;
    }

    public Album buscarAlbumPorId(int id){
        for (Album album : albuns){
            if (album.getId() == id){
                return album;
            }
        }
        return null;
    }

    public List<Album> listarAlbuns() {
        return new ArrayList<>(albuns);
    }

    public int getTotalAlbuns() {
        return albuns.size();
    }

    public List<Album>buscarAlbunsPorArtista(String artista) {
        List<Album> resultado=new ArrayList<>();
        for (Album album : albuns) {
            if (album.getArtista().toLowerCase().contains(artista.toLowerCase())){
                resultado.add(album);
            }
        }
        return resultado;
    }
}