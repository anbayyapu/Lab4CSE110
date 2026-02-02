package edu.ucsd.spendingtracker.presenter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.ucsd.spendingtracker.model.Category;
import edu.ucsd.spendingtracker.model.Expense;
import edu.ucsd.spendingtracker.model.Model;
import edu.ucsd.spendingtracker.view.SummaryView;
import edu.ucsd.spendingtracker.view.charts.IChartProvider;
import javafx.scene.Node;

public class SummaryPresenter extends AbstractPresenter<SummaryView> {
    private Runnable onBack;
    private final List<IChartProvider> chartProviders;

    public SummaryPresenter(Model model, SummaryView view,
            List<IChartProvider> chartProviders) {
        super(model, view);
        this.chartProviders = chartProviders;
        this.view.getChartSelector().getItems().addAll(chartProviders);

        if (!this.chartProviders.isEmpty()) {
            this.view.getChartSelector().setValue(this.chartProviders.get(0));
        }

        this.view.getBackButton().setOnAction(e -> {
            if (onBack != null)
                onBack.run();
        });

        this.view.getChartSelector().setOnAction(e -> {
            refresh();
        });
    }

    public void setOnBack(Runnable action) {
        this.onBack = action;
    }

    @Override
    public void updateView() {
        refresh();
        view.setTotal(model.getTotalSpending());
    }

    @Override
    public String getViewTitle() {
        return "Summary";
    }

    public void refresh() {
        List<Expense> expenses = model.getExpenses();
        Map<Category, Double> totals = new TreeMap<>();
        for (Category category : Category.values()) {
            totals.put(category, 0.0);
        }
        for (Expense expense : expenses) {
            Category category = expense.getCategory();
            totals.put(category, totals.get(category) + expense.getAmount());
        }
        view.setTotal(model.getTotalSpending());

        IChartProvider selectedProvider = view.getChartSelector().getValue();

        if (selectedProvider != null) {
            Node chartDisplay = selectedProvider.createChart(totals);
            view.setChartDisplay(chartDisplay);
        }
    }
}
