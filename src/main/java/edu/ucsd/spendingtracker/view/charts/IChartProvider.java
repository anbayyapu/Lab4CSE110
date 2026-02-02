package edu.ucsd.spendingtracker.view.charts;

import java.util.Map;

import edu.ucsd.spendingtracker.model.Category;
import javafx.scene.Node;

public interface IChartProvider {
    Node createChart(Map<Category, Double> data);

    String getDisplayName();
}
