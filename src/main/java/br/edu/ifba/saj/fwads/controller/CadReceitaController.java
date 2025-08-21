package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Receita;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.DAO.ReceitaDAO;
import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadReceitaController {

    @FXML private TextField txNomeReceita;
    @FXML private TextField txValorReceita;
    @FXML private DatePicker dpDataReceita;

    // O controller cria a instância do DAO que ele precisa
    private final ReceitaDAO receitaDAO = new ReceitaDAO();

    @FXML
    private void initialize() {
        dpDataReceita.setValue(LocalDate.now());
    }

    @FXML
    void salvarReceita() {
        Usuario usuarioLogado = App.getUsuarioLogado();
        if (usuarioLogado == null) {
            new Alert(AlertType.ERROR, "Nenhum usuário logado. Faça o login novamente.").show();
            return;
        }

        try {
            String nome = txNomeReceita.getText();
            BigDecimal valor = new BigDecimal(txValorReceita.getText());
            LocalDate data = dpDataReceita.getValue();

            if (nome.isBlank() || data == null) {
                 new Alert(AlertType.WARNING, "Nome e data são obrigatórios.").show();
                return;
            }

            Receita novaReceita = new Receita(nome, valor, data, usuarioLogado.getId());
            
            receitaDAO.salvar(novaReceita);
            
            new Alert(AlertType.INFORMATION, "Receita '" + nome + "' salva com sucesso!").showAndWait();
            limparTela();

        } catch (NumberFormatException e) {
            new Alert(AlertType.ERROR, "O valor da receita deve ser um número válido (use '.' para centavos).").show();
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Ocorreu um erro ao salvar a receita.").show();
            e.printStackTrace();
        }
    }

    @FXML
    private void limparTela() {
        txNomeReceita.clear();
        txValorReceita.clear();
        dpDataReceita.setValue(LocalDate.now());
    }
}