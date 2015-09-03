package net.oodeveloper.sample.matwik.widget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.oodeveloper.sample.matwik.widget.GoogleChartModel.Column;
import net.oodeveloper.sample.matwik.widget.GoogleChartModel.Row;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.util.string.StringValue;

public class GoogleChart extends Panel {
	private static final long serialVersionUID = 1L;
	
	private Chart chart = Chart.PieChart;
	private GoogleChartModel model;
	private Label labelDummyForMarkupId;

	private AbstractDefaultAjaxBehavior ajaxSelectionBehavior;
	private OnSelectionListener onSelectionListener;
	
	private List<Option> options = new ArrayList<>();
	
	public enum Chart {
		
		PieChart,
		ColumnChart,
	}
	
	public static interface OnSelectionListener extends Serializable {
		
		void onSelection(AjaxRequestTarget target, GoogleChartModel.Row row);
	}
	
	public static interface Option extends Serializable {
		
	}
	
	public static class OptionTitle implements Option {
		private static final long serialVersionUID = 1L;
		
		private String title;
		
		public OptionTitle(String title) {
			this.title = title;
		}
		
		@Override
		public String toString() {
			return String.format("'title':'%s', ", title);
		}
	}
	
	public static class OptionSize implements Option {
		private static final long serialVersionUID = 1L;
		
		private int width;
		private int height;
		
		public OptionSize(int width, int height) {
			
			this.width = width;
			this.height = height;
		}
		
		@Override
		public String toString() {
			return String.format("'width':%s, 'height':%s, ", width, height);
		}
	}
	
	public static class OptionPieHole implements Option {
		private static final long serialVersionUID = 1L;
		
		private double ratio;
		
		public OptionPieHole(double ratio) {
			
			this.ratio = ratio;
		}
		
		@Override
		public String toString() {
			return String.format("'pieHole': %s, ", ratio);
		}
	}
	 
	public GoogleChart(String id, GoogleChartModel model) {
		super(id);
		
		this.model = model;
		
		labelDummyForMarkupId = new Label("internalChart");
		labelDummyForMarkupId.setOutputMarkupId(true);
		add(labelDummyForMarkupId);
		
		ajaxSelectionBehavior = new AbstractDefaultAjaxBehavior() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void respond(AjaxRequestTarget target) {
				
				RequestCycle cycle = RequestCycle.get();
				WebRequest webRequest = (WebRequest) cycle.getRequest();
				StringValue stringRow = webRequest.getQueryParameters().getParameterValue("row");
				
				try {
					
					int rowIndex = Integer.parseInt(stringRow.toString());
					Row row =  GoogleChart.this.model.getRows().get(rowIndex);
					
					if (onSelectionListener != null) {
						
						onSelectionListener.onSelection(target, row);
					}
					
				} catch (Exception e) {
					// noop
				}
			}
		};	
		labelDummyForMarkupId.add(ajaxSelectionBehavior);
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(new StringHeaderItem("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>"));
		response.render(new JavaScriptContentHeaderItem(createDrawScript(), "", null));
	}

	private String createDrawScript() {
		
		StringBuffer retval =  new StringBuffer();
		
		retval.append("google.load('visualization', '1.0', {'packages':['corechart']}); google.setOnLoadCallback(drawChart);");

		retval.append("function drawChart() {\n");
		
		retval.append("var data = new google.visualization.DataTable();\n");
		
		for (Column column : model.getColumns()) {
			
			retval.append(String.format("data.addColumn('%s', '%s');\n", column.getType(), column.getName()));
		}
		
		retval.append("data.addRows([\n");

		for (Row item : model.getRows()) {
			
			if (model.getColumns().size() == 2) {
			
				if (model.getColumns().get(0).getType().equals(GoogleChartModel.Column.Type.string) &&
					model.getColumns().get(1).getType().equals(GoogleChartModel.Column.Type.number)) {
				
					retval.append(String.format("['%s', %s],\n", item.getName(), item.getValue1()));
				}
			}
		}
		
		retval.append("]);\n");
		
		
		retval.append(String.format("var options = {"));
		
		for (Option option : options) {
			
			retval.append(option.toString());
		}
		
		retval.append("}\n"); 
		
		 
		retval.append(String.format("var chart = new google.visualization.%s(document.getElementById('%s'));\n",
				chart, labelDummyForMarkupId.getMarkupId())); 
		
		
		retval.append("chart.draw(data, options);\n");
		
		retval.append("google.visualization.events.addListener(chart, 'select', function() {");
		retval.append("if (chart.getSelection().length == 0) return; var row = (chart.getSelection()[0].row);");
		
		retval.append(String.format("if (row != null) { Wicket.Ajax.ajax({'u':'%s', 'ep':[{'name':'row','value': row}]}) } ", ajaxSelectionBehavior.getCallbackUrl()));
		retval.append("});\n");
		
		retval.append("}");
		
		return retval.toString();
	}
	
	public void draw(AjaxRequestTarget target) {
	
		createDrawScript();
		target.appendJavaScript("drawChart();");
	}

	public GoogleChartModel getModel() {
		return model;
	}

	public void setModel(GoogleChartModel model) {
		this.model = model;
	}

	
	public Chart getChart() {
		return chart;
	}
	
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	
	public void setOnSelectionListener(OnSelectionListener onSelectionListener) {
		this.onSelectionListener = onSelectionListener;
	}
	
	public OnSelectionListener getOnSelectionListener() {
		return onSelectionListener;
	}
	
	public void addOption(Option option) {
		options.add(option);
	}
	
	public void removeOption(Option option) {
		options.remove(option);
	}
}
