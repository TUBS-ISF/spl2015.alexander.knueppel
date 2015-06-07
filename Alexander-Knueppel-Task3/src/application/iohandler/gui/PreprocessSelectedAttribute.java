//#ifdef Graphical
package application.iohandler.gui;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import framework.CategoricalData;
import framework.classifier.DataPoint;
import application.iohandler.Controller;

public class PreprocessSelectedAttribute extends JPanel {
	private JTable table;
	private String[] columnNamesReal = {"Statistic", "Value"};
	private String[] columnNamesCategory = {"No.", "Label", "Count"};
	//private MainFrame gui;
	
	public PreprocessSelectedAttribute(/*MainFrame f*/) {
		//gui = f;
//		Object[][] data = { {null, null, null} };
//		table = new JTable(data, columnNames);
//		add(table);
		update(4);
	}
	
	public void update(int n) { //attribute number
		if(Controller.currDataSet != null) {
			this.removeAll();
			if(n < Controller.currDataSet.getNumNumericalAttrs()) { //statistics
				double Minimum = Double.MAX_VALUE;
				double Maximum = 0.0;
				double Mean = 0.0;
				double StdDev = 0.0;
				
				for(DataPoint d : Controller.currDataSet.getDataPoints()) {
					Minimum = Math.min(d.getNumericalValues().get(n), Minimum);
					Maximum = Math.max(d.getNumericalValues().get(n), Maximum);
					Mean += d.getNumericalValues().get(n);
					
				}
				Mean /= Controller.currDataSet.getSampleSize();
				
				for(DataPoint d : Controller.currDataSet.getDataPoints()) {
					StdDev += (d.getNumericalValues().get(n)-Mean)*(d.getNumericalValues().get(n)-Mean);
				}
				StdDev = Math.sqrt(StdDev / (Controller.currDataSet.getSampleSize()-1));
				
				Object[][] data = {{"Min.:", new Double(Minimum)},
								   {"Max.:", new Double(Maximum)},
								   {"Mean:", new Double(Mean)},
								   {"Std. Dev.:", new Double(StdDev)}};
	
				table = new JTable(data, columnNamesReal);
				table.setPreferredScrollableViewportSize(new Dimension(300, 150));
		        table.setFillsViewportHeight(true);
				add(new JScrollPane(table));			
			} else {
				n -= Controller.currDataSet.getNumNumericalAttrs();
				int nCats = Controller.currDataSet.getCategories()[n].getNumOfCategories();
				Map<String, Integer> statistics = new HashMap<String, Integer>();
				
				for(int i = 0; i < nCats; ++i) {
					String s = Controller.currDataSet.getCategories()[n].getOptionName(i);
					statistics.put(s, new Integer(0));
				}
				
				for(DataPoint d : Controller.currDataSet.getDataPoints()) {
					if(d.containsCategoricalData()) {
						for(int cv :  d.getCategoricalValues()) {
							String s = Controller.currDataSet.getCategories()[n].getOptionName(cv);
							statistics.put(s, statistics.get(s) + 1);
						}
					}
				}
				
				Object[][] data = new Object[nCats][3];
				int i = 1;
				for(Entry<String, Integer> e : statistics.entrySet()) {
					Object[] o = {new Integer(i), e.getKey(), e.getValue()};
					data[i-1] = o;
					i++;
				}
				
				table = new JTable(data, columnNamesCategory);
				table.setPreferredScrollableViewportSize(new Dimension(300, 150));
		        table.setFillsViewportHeight(true);
				add(new JScrollPane(table));
			}
			this.updateUI();
		}
	}

}
//#endif
