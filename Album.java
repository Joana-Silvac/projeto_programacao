
import java.util.ArrayList;
import java.util.List;

public class Album {
    private int id;
    private String titulo;
    private String artista;
    private String genero;
    private List<Musica> musicas;

    public Album(int id, String titulo, String artista, String genero) {
        this.id= id;
        this.titulo= titulo;
        this.artista = artista;
        this.genero= genero;
        this.musicas= new ArrayList<>();
    }

    public void adicionarMusica(Musica musica){
        musicas.add(musica);
    }

    public int getQuantidadeMusicas(){
        return musicas.size();
    }

    public int getDuracaoTotal(){
        int total= 0;
        for (Musica musica : musicas) {
            total+= musica.getDuracao();
        }
        return total;
    }

    public String getDuracaoTotalFormatada(){
        int total= getDuracaoTotal();
        int minutos= total/ 60;
        int segundos = total% 60;
        return String.format("%d:%02d", minutos, segundos);
    }

    public int getId(){ return id;}
    public String getTitulo(){ return titulo;}
    public String getArtista(){ return artista;}
    public String getGenero(){return genero;}
    public List<Musica> getMusicas(){return new ArrayList<>(musicas);}

    public String toString() {
        return String.format("Album [%d] %s - %s | %d musicas", id, titulo, artista, getQuantidadeMusicas());
    }
}