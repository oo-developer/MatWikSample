package net.oodeveloper.sample.matwik;

import net.oodeveloper.sample.matwik.widget.GoogleChart;
import net.oodeveloper.sample.matwik.widget.GoogleChart.Chart;
import net.oodeveloper.sample.matwik.widget.GoogleChartModel;
import net.oodeveloper.sample.matwik.widget.GoogleChartModel.Column.Type;
import net.oodeveloper.sample.matwik.widget.GoogleChartModel.Row;
import net.oodeveloper.sample.matwik.widget.Toast;

import org.apache.wicket.ajax.AjaxRequestTarget;


public class ChartPage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	private GoogleChartModel modelChart = new GoogleChartModel();
	
	public ChartPage() {
		
		modelChart.addColumn(new GoogleChartModel.Column("Name", Type.string));
		modelChart.addColumn(new GoogleChartModel.Column("Rank", Type.number));
		
		modelChart.addRow(new GoogleChartModel.Row("Homer", 2.5));
		modelChart.addRow(new GoogleChartModel.Row("Bart", 15.0));
		modelChart.addRow(new GoogleChartModel.Row("Marge", 11.7));
		modelChart.addRow(new GoogleChartModel.Row("Meggy", 5.23));
		modelChart.addRow(new GoogleChartModel.Row("Lisa", 9.0));
		
		GoogleChart pieChart = new GoogleChart("pieChart", modelChart);
		pieChart.setChart(Chart.PieChart);
		pieChart.addOption(new GoogleChart.OptionPieHole(0.4));
		pieChart.addOption(new GoogleChart.OptionSize(500, 300));
		add(pieChart);
		
		pieChart.setOnSelectionListener(new GoogleChart.OnSelectionListener() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSelection(AjaxRequestTarget target, Row row) {
				
				Toast.makeText("PieChart %s : %s", row.getName(), row.getValue1()).show(target, 5000);
			}
		});
		
		GoogleChart columnChart = new GoogleChart("columnChart", modelChart);
		columnChart.setChart(Chart.ColumnChart);
		columnChart.addOption(new GoogleChart.OptionSize(500, 300));
		add(columnChart);
		
		columnChart.setOnSelectionListener(new GoogleChart.OnSelectionListener() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSelection(AjaxRequestTarget target, Row row) {
				
				Toast.makeText("ColumnChart %s : %s", row.getName(), row.getValue1()).show(target, 5000);
			}
		});
	}
}
