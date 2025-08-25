package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CadUsuarioController {


    @FXML
    private TextField textCadastroEmail;
    @FXML
    private TextField textCadastroNome;
    @FXML
    private PasswordField textCadastroSenha;
    @FXML
    private TextFlow cadMsgBox;
    @FXML
    private Text cadMsg;

    
    private final UsuarioDAO usuarioDAO;


    public CadUsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

   
    @FXML
    void handlerCadastroButton(ActionEvent event) {
        String nome = textCadastroNome.getText().trim();
        String email = textCadastroEmail.getText().trim();
        String senha = textCadastroSenha.getText();

        // 1. Validação de campos vazios
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            showMessage("Por favor, preencha todos os campos.", Color.RED);
            return;
        }

        // 2. Validação de e-mail existente
        if (usuarioDAO.buscarPorEmail(email) != null) {
            showMessage("Este e-mail já está em uso. Tente outro.", Color.ORANGERED);
            return;
        }

        // 3. Se todas as validações passarem, cria e salva o usuário
        try {
            Usuario novoUsuario = new Usuario(nome, email, senha);
            usuarioDAO.salvar(novoUsuario);
            showMessage("Cadastro realizado com sucesso!", Color.GREEN);
            limparCampos();
        } catch (Exception e) {
            showMessage("Ocorreu um erro ao tentar cadastrar.", Color.RED);
            e.printStackTrace();
        }
    }

  
    @FXML
    void handleBackToLogin(ActionEvent event) {
        System.out.println("Navegando de volta para a tela de login...");
    }

    private void showMessage(String message, Color color) {
        cadMsg.setText(message);
        cadMsg.setFill(color);
        cadMsgBox.setVisible(true);
    }

   
    private void limparCampos() {
        textCadastroNome.clear();
        textCadastroEmail.clear();
        textCadastroSenha.clear();
    }
}