package br.edu.ifba.saj.fwads;

import br.edu.ifba.saj.fwads.model.Usuario;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class App extends Application {
    
    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static void logout() {
        usuarioLogado = null;
    }    

    private static Scene scene;     

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/exemplo/financeiro/Login.fxml")); 
        scene = new Scene(loader.load(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Sistema de Controle Financeiro");
        stage.show();
    }

    public static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao carregar o arquivo " + fxml).show();
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/exemplo/financeiro/" + fxml));
        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}