package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private TextFlow loginMsgBox;
    @FXML
    private Text loginMsg;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void handlerLoginButton(ActionEvent event) {
        String email = textFieldEmail.getText();
        String senha = textFieldPassword.getText();

        if (usuarioDAO.autenticar(email, senha)) {
            Usuario usuarioLogado = usuarioDAO.buscarPorEmail(email);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RelatorioGastos.fxml"));
                Parent root = loader.load();


                RelatorioGastosController relatorioController = loader.getController();

                relatorioController.setUsuarioId(usuarioLogado.getId());

                Stage stage = (Stage) textFieldEmail.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Relatório de Gastos");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loginMsgBox.setVisible(true);
            loginMsg.setText("ATENÇÃO!!! Email ou Senha está incorreto!");
        }
    }

    @FXML
    private void handleCadastro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CadUsuario.fxml"));
            Stage stage = (Stage) textFieldEmail.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Cadastro de Usuário");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}