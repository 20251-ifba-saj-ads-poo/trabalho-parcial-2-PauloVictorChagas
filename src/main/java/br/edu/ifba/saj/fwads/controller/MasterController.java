package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MasterController {

    @FXML
    private BorderPane masterPane;

    @FXML
    private VBox menu;

    @FXML
    private Label userEmail;


    @FXML
    private void initialize() {
        Usuario usuario = App.getUsuarioLogado();
        if (usuario != null) {
            userEmail.setText(usuario.getEmail());
        }
    }

    @FXML
    void logOff(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente sair?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    App.logout();
                    App.setRoot("Login.fxml");
                });
    }

    @FXML
    void showHome(ActionEvent event) {
        limparBotaoSelecionado(event.getSource());
        masterPane.setCenter(new Pane());
    }
    
    @FXML
    void showCadastroGasto(ActionEvent event) {
        limparBotaoSelecionado(event.getSource());
        carregarTela("CadGasto.fxml");
    }

    @FXML
    void showCadastroReceita(ActionEvent event) {
        limparBotaoSelecionado(event.getSource());
        carregarTela("CadReceita.fxml");
    }

    private void carregarTela(String nomeArquivoFxml) {
        try {
            Pane fxmlCarregado = FXMLLoader.load(App.class.getResource("/com/exemplo/financeiro/" + nomeArquivoFxml));
            masterPane.setCenter(fxmlCarregado);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao carregar o arquivo " + nomeArquivoFxml).showAndWait();
            e.printStackTrace();
        }
    }

    private void limparBotaoSelecionado(Object source) {
        menu.getChildren().forEach(node -> {
            if (node instanceof Button btn) {
                btn.getStyleClass().remove("btn-menu-selected");
                if (!btn.getStyleClass().contains("btn-menu")) {
                    btn.getStyleClass().add("btn-menu");
                }
            }
        });

        if (source instanceof Button btn) {
            btn.getStyleClass().add("btn-menu-selected");
        }
    }
}