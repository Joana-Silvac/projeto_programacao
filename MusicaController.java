import java.util.ArrayList;
import java.util.List;

public class MusicaController{
    private List<Musica>musicas;
    private int nextId;

    public MusicaController(){
        this.musicas=new ArrayList< >();
        this.nextId =1;


    }
    public void adicionarMusica(String titulo, String artista, int duracao, String genero){
        Musica musica = new Musica(nextId, titulo, artista, duracao, genero);
        musicas.add(musica);
        nextId++;
    }



