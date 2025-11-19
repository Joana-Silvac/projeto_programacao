import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class LoginTela extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private static Map<String, String> usuariosSenhas = new HashMap<>();
    private static Map<String, String> usuariosNicks = new HashMap<>();
    private static final String ARQUIVO_USUARIOS = "usuarios.dat";

    static {
        carregarUsuarios();
    }

    private static void carregarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_USUARIOS))) {
            usuariosSenhas = (Map<String, String>) ois.readObject();
            usuariosNicks = (Map<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {

            usuariosSenhas.put("admin@teste.com", "1234");
            usuariosNicks.put("admin@teste.com", "Admin");
            salvarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_USUARIOS))) {
            oos.writeObject(usuariosSenhas);
            oos.writeObject(usuariosNicks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoginTela() {
        setTitle("Login - Tech Music");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel() {

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon icon = new ImageIcon("cat.png");
                    Image img = icon.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(new Color(200, 220, 255));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        panel.setLayout(new GridBagLayout());

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginPanel.setBackground(new Color(255, 255, 255, 220));

        loginPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        loginPanel.add(txtEmail);

        loginPanel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        loginPanel.add(txtSenha);

        JButton btnLogin = new JButton("Login");
        JButton btnCadastrar = new JButton("Cadastrar");

        loginPanel.add(btnLogin);
        loginPanel.add(btnCadastrar);

        panel.add(loginPanel);
        add(panel);

        btnLogin.addActionListener(e -> fazerLogin());
        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        txtSenha.addActionListener(e -> fazerLogin());
    }

    private void fazerLogin() {
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        if (usuariosSenhas.containsKey(email) && usuariosSenhas.get(email).equals(senha)) {
            String nickname = usuariosNicks.get(email);
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + nickname + "!");
            this.dispose();

            Usuario usuario = new Usuario(email, nickname, senha);
            abrirDeezerTela(usuario);
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha inválidos!");
            txtSenha.setText("");
        }
    }

    private void cadastrarUsuario() {
        JPanel panelCadastro= new JPanel(new GridLayout(3, 2, 10, 10));
        panelCadastro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtNovoEmail= new JTextField();
        JPasswordField txtNovaSenha= new JPasswordField();
        JTextField txtNickname = new JTextField();

        panelCadastro.add(new JLabel("Email:"));
        panelCadastro.add(txtNovoEmail);
        panelCadastro.add(new JLabel("Senha:"));
        panelCadastro.add(txtNovaSenha);
        panelCadastro.add(new JLabel("Nickname:"));
        panelCadastro.add(txtNickname);

        int result= JOptionPane.showConfirmDialog(this, panelCadastro,
                "Cadastrar Nova Conta", JOptionPane.OK_CANCEL_OPTION);

        if (result== JOptionPane.OK_OPTION) {
            String email= txtNovoEmail.getText().trim();
            String senha= new String(txtNovaSenha.getPassword());
            String nickname= txtNickname.getText().trim();

            if (email.isEmpty() || senha.isEmpty() || nickname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            if (usuariosSenhas.containsKey(email)) {
                JOptionPane.showMessageDialog(this, "Email já cadastrado!");
                return;
            }

            usuariosSenhas.put(email, senha);
            usuariosNicks.put(email, nickname);
            salvarUsuarios();

            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!\nFaça login para entrar.");

            txtEmail.setText(email);
            txtSenha.setText("");
            txtSenha.requestFocus();
        }
    }

    private void abrirDeezerTela(Usuario usuario){
        SwingUtilities.invokeLater(() -> {
            new DeezerTela(usuario).setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new LoginTela().setVisible(true);
        });
    }
}