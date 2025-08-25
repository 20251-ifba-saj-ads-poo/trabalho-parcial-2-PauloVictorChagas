package Controller;

import DAO.GastoDAO;
import DAO.ReceitaDAO;
import Model.Gasto;
import Model.Receita;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

public class CadGastoController implements Initializable {

    @FXML
    private TextField cadDespesaGanho;
    @FXML
    private TextField cadValor;
    @FXML
    private ChoiceBox<String> choiceBoxTipo;
    @FXML
    private DatePicker cadData;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button cadBack2;


    private GastoDAO gastoDAO;
    private ReceitaDAO receitaDAO;


    public CadGastoController() {
        this.gastoDAO = new GastoDAO();
        this.receitaDAO = new ReceitaDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxTipo.getItems().addAll("Receita", "Gasto", "Gasto Fixo");
        choiceBoxTipo.setValue("Gasto");
        buttonCadastrar.setOnAction(event -> salvar());
    }


    private void salvar() {
        String tipoSelecionado = choiceBoxTipo.getValue();
        String descricao = cadDespesaGanho.getText();
        String valorStr = cadValor.getText();
        LocalDate data = cadData.getValue();

        if (descricao.isEmpty() || valorStr.isEmpty() || data == null) {
            showAlert(AlertType.ERROR, "Erro de Validação", "Todos os campos são obrigatórios.");
            return;
        }

        BigDecimal valor;
        try {
            valor = new BigDecimal(valorStr.replace(",", "."));
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erro de Formato", "O valor inserido é inválido. Use apenas números (ex: 150.75).");
            return;
        }

        UUID usuarioId = getUsuarioLogadoId();

        try {
            if ("Receita".equals(tipoSelecionado)) {
                Receita novaReceita = new Receita(descricao, valor, data, usuarioId);
                receitaDAO.salvar(novaReceita);

            } else if ("Gasto".equals(tipoSelecionado)) {
                Gasto novoGasto = new Gasto(descricao, valor, data, false, usuarioId); // false para gastoFixo
                gastoDAO.salvar(novoGasto);

            } else if ("Gasto Fixo".equals(tipoSelecionado)) {
                Gasto novoGastoFixo = new Gasto(descricao, valor, data, true, usuarioId); // true para gastoFixo
                gastoDAO.salvar(novoGastoFixo);
            }

            showAlert(AlertType.INFORMATION, "Sucesso", tipoSelecionado + " cadastrado com sucesso!");
            limparCampos();

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Erro ao Salvar", "Ocorreu um erro inesperado: " + e.getMessage());
        }
    }


    private void limparCampos() {
        cadDespesaGanho.clear();
        cadValor.clear();
        cadData.setValue(null);
        choiceBoxTipo.setValue("Gasto"); // Reseta para o padrão
    }


    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
