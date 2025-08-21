package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Gasto;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.DAO.GastoDAO;
import java.math.BigDecimal;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadGastoController {

    @FXML private TextField txNomeGasto;
    @FXML private TextField txValorGasto;
    @FXML private DatePicker dpDataGasto;
    @FXML private CheckBox cbGastoFixo;

    private final GastoDAO gastoDAO = new GastoDAO();

    @FXML
    private void initialize() {
        dpDataGasto.setValue(LocalDate.now());
    }

    @FXML
    void salvarGasto() {
        Usuario usuarioLogado = App.getUsuarioLogado();
        if (usuarioLogado == null) {
            new Alert(AlertType.ERROR, "Nenhum usuário logado. Faça o login novamente.").show();
            return;
        }
        
        try {
            String nome = txNomeGasto.getText();
            BigDecimal valor = new BigDecimal(txValorGasto.getText());
            LocalDate data = dpDataGasto.getValue();
            boolean isFixo = cbGastoFixo.isSelected();

            if (nome.isBlank() || data == null) {
                 new Alert(AlertType.WARNING, "Nome e data são obrigatórios.").show();
                return;
            }

            Gasto novoGasto = new Gasto(nome, valor, data, isFixo, usuarioLogado.getId());
            
            gastoDAO.salvar(novoGasto);
            
            new Alert(AlertType.INFORMATION, "Gasto '" + nome + "' salvo com sucesso!").showAndWait();
            limparTela();

        } catch (NumberFormatException e) {
            new Alert(AlertType.ERROR, "O valor do gasto deve ser um número válido (use '.' para centavos).").show();
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Ocorreu um erro ao salvar o gasto.").show();
            e.printStackTrace();
        }
    }

    @FXML
    private void limparTela() {
        txNomeGasto.clear();
        txValorGasto.clear();
        dpDataGasto.setValue(LocalDate.now());
        cbGastoFixo.setSelected(false);
    }
}