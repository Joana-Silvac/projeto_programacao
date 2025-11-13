import java.util.List;

public class TesteMusicaController{
    public static void main(String[] args){
        MusicaController controller= new MusicaController();
        DeezerAPI deezer= new DeezerAPI();



        List<Musica>musicas=deezer.buscarMusicaEspecifica("Leandro & Leonardo Pense em Mim");

        for (Musica musica : musicas){
            if (musica.getArtista().toLowerCase().contains("leandro & leonardo")) {
                controller.adicionarMusica(musica.getTitulo(), musica.getArtista(),
                        musica.getDuracao(), "Sertanejo");
            }
        }

        for (Musica musica : controller.listarMusicas()) {
            System.out.println(musica.getTitulo() +" - "+musica.getArtista() + " (" + formatarDuracao(musica.getDuracao()) + ")");
        }

        System.out.println("TOTAL DE MÚSICAS: " +controller.getTotalMusicas());
    }

    public static String formatarDuracao(int segundos){
        int minutos= segundos/60;
        int segundosRestantes= segundos % 60;
        return String.format("%d:%02d", minutos, segundosRestantes);
    }
}