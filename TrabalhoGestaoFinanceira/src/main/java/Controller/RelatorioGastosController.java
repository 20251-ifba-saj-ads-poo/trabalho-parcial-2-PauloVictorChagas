package Controller;

import DAO.GastoDAO;
import DAO.ReceitaDAO;
import Model.Gasto;
import Model.Receita;
import Model.RelatorioMensal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RelatorioGastosController {

    @FXML
    private TableView<LancamentoRow> tabelaView;

    @FXML
    private TableColumn<LancamentoRow, String> tabelaTipo;

    @FXML
    private TableColumn<LancamentoRow, BigDecimal> tabelaValor;

    @FXML
    private TableColumn<LancamentoRow, LocalDate> tabelaData;

    @FXML
    private TableColumn<LancamentoRow, String> tabelaDespesaGanho;

    @FXML
    private PieChart Diagrama;

    @FXML
    private Button ButtonCadGasto;

    @FXML
    private Button ButtonGrafico;

    @FXML
    private Button ButtonBackLlogin;

    private UUID usuarioId;
    private GastoDAO gastoDAO = new GastoDAO();
    private ReceitaDAO receitaDAO = new ReceitaDAO();

    // Chamar depois de logar, com ID do usuário
    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
        carregarRelatorio();
    }

    private void carregarRelatorio() {
        LocalDate hoje = LocalDate.now();
        int mes = hoje.minusMonths(1).getMonthValue(); // último mês
        int ano = hoje.minusMonths(1).getYear();

        List<Gasto> gastos = gastoDAO.buscarPorMesAno(usuarioId, mes, ano);
        List<Receita> receitas = receitaDAO.buscarPorMesAno(usuarioId, mes, ano);

        // Montar tabela
        ObservableList<LancamentoRow> rows = FXCollections.observableArrayList();
        for (Gasto g : gastos) {
            String tipo = g.isGastoFixo() ? "Gasto Fixo" : "Gasto";
            rows.add(new LancamentoRow(tipo, g.getValorGasto(), g.getData(), g.getNomeGasto()));
        }
        for (Receita r : receitas) {
            rows.add(new LancamentoRow("Receita", r.getValor(), r.getData(), r.getNome()));
        }

        tabelaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tabelaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tabelaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tabelaDespesaGanho.setCellValueFactory(new PropertyValueFactory<>("nome"));

        tabelaView.setItems(rows);

        // Montar PieChart
        RelatorioMensal relatorio = new RelatorioMensal(usuarioId, mes, ano);
        relatorio.calcularRelatorio(gastos, receitas);

        Diagrama.getData().clear();
        Diagrama.getData().add(new PieChart.Data("Gasto Fixo", relatorio.getPercentualGastosFixos().doubleValue()));
        Diagrama.getData().add(new PieChart.Data("Gasto", relatorio.getPercentualGastosNormais().doubleValue()));
        Diagrama.getData().add(new PieChart.Data("Saldo Restante", relatorio.getPercentualReceitas().doubleValue()));
    }

    @FXML
    private void handleButtonCadGasto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CadGastos.fxml"));
            Stage stage = (Stage) ButtonCadGasto.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonGrafico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GraficoMensal.fxml"));
            Stage stage = (Stage) ButtonGrafico.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonBackLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
            Stage stage = (Stage) ButtonBackLlogin.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe interna para exibir dados na TableView
    public static class LancamentoRow {
        private final String tipo;
        private final BigDecimal valor;
        private final LocalDate data;
        private final String nome;

        public LancamentoRow(String tipo, BigDecimal valor, LocalDate data, String nome) {
            this.tipo = tipo;
            this.valor = valor;
            this.data = data;
            this.nome = nome;
        }

        public String getTipo() { return tipo; }
        public BigDecimal getValor() { return valor; }
        public LocalDate getData() { return data; }
        public String getNome() { return nome; }
    }
}
