package edu.ucsd.spendingtracker.view.charts;

import java.util.Map;

import edu.ucsd.spendingtracker.model.Category;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

public class PieChartProvider implements IChartProvider {
    @Override
    public String getDisplayName() {
        return "Pie Chart";
    }

    @Override
    public Node createChart(Map<Category, Double> data) {
        PieChart chart = new PieChart();

        data.forEach((category, amount) -> {
            PieChart.Data slice = new PieChart.Data(category.name(), amount);
            chart.getData().add(slice);

            String color = category.color;
            slice.getNode().setStyle("-fx-pie-color: " + color + ";");
        });

        chart.setLegendVisible(false);
        return chart;
    }

}
