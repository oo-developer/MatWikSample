package net.oodeveloper.sample.matwik.widget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoogleChartModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public static class Column implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public enum Type {
			
			number,
			string
		}
		
		private String name;
		private Type type;
		
		public Column(String name, Type type) {
			
			this.name = name;
			this.type = type;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Type getType() {
			return type;
		}
		
		public void setType(Type type) {
			this.type = type;
		}
	}
	
	public static class Row implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String name;
		private double value1;
		private double value2;
		private String color;
	
		public Row(String name, double value) {
			
			this.name = name;
			this.value1 = value;
		}
		
		public Row(String name, double value, String color) {
			
			this.name = name;
			this.value1 = value;
			this.color = color;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getValue1() {
			return value1;
		}
		public void setValue1(double value) {
			this.value1 = value;
		}
		public double getValue2() {
			return value2;
		}
		public void setValue2(double value2) {
			this.value2 = value2;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
	}
	
	private List<Column> columns = new ArrayList<>();
	private List<Row> rows = new ArrayList<>();
	
	public void addColumn(Column column) {
		
		columns.add(column);
	}
	
	public void removeColumn(Column column) {
		
		columns.remove(column);
	}
	
	public List<Column> getColumns() {
		
		return new ArrayList<>(columns);
	}
	
	public void addRow(Row item) {
		
		rows.add(item);
	}
	
	public void removeRow(Row item) {
		
		rows.remove(item);
	}
	
	public List<Row> getRows() {
		
		return new ArrayList<>(rows);
	}
}
