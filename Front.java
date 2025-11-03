import javax.swing.*;
import java.awt.*;

public class Front {
    public Front(){
        JFrame janela_principal=new JFrame();

        janela_principal.setBounds(400,100,730,460);
        janela_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela_principal.setLayout(null);
        janela_principal.setContentPane(new JLabel(new ImageIcon("#cat #wallpaper #pc.jpg")));

        JLabel tema = new JLabel("Tech Music");
        tema.setBounds(150,32,400,76);
        tema.setFont(new Font("SansSerif", Font.BOLD, 42));
        janela_principal.add(tema);

        JButton botao_usuario=new JButton("Usuario");
        botao_usuario.setBounds(230,150,275,50);
        janela_principal.add(botao_usuario);

        JButton botao_playlist =new JButton("Playlists");
        botao_playlist.setBounds(230,210,275,50);
        janela_principal.add(botao_playlist);


        janela_principal.setVisible(true);
    }
}