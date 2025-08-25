package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GraficoMensalController {

    @FXML
    private BarChart<String, Number> barChartRelatorio;

    @FXML
    private Button ButtonExit;

    @FXML
    public void initialize() {
        carregarGrafico();
    }

    @SuppressWarnings("unchecked")
    private void carregarGrafico() {

        XYChart.Series<String, Number> receitaSeries = new XYChart.Series<>();
        receitaSeries.setName("Receita");


        XYChart.Series<String, Number> gastoSeries = new XYChart.Series<>();
        gastoSeries.setName("Gastos");


        XYChart.Series<String, Number> gastoFixoSeries = new XYChart.Series<>();
        gastoFixoSeries.setName("Gastos Fixos");


        String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun"};
        double[] receitas = {5000, 4800, 5100, 5300, 5500, 6000};
        double[] gastos = {2000, 2100, 1900, 2500, 2600, 2700};
        double[] gastosFixos = {1000, 1000, 1000, 1000, 1000, 1000};

        for (int i = 0; i < meses.length; i++) {
            receitaSeries.getData().add(new XYChart.Data<>(meses[i], receitas[i]));
            gastoSeries.getData().add(new XYChart.Data<>(meses[i], gastos[i]));
            gastoFixoSeries.getData().add(new XYChart.Data<>(meses[i], gastosFixos[i]));
        }

        barChartRelatorio.getData().addAll(receitaSeries, gastoSeries, gastoFixoSeries);
    }

    @FXML
    private void handleExit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RelatorioGastos.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ButtonExit.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
