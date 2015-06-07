//#ifdef Graphical
package application.iohandler.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import application.iohandler.Controller;

public class PreprocessAttributesList extends JPanel {
	private JTable table;
	private String[] columnNames = {"No.", "Name", "Type"};
	//private MainFrame gui;
	
	public PreprocessAttributesList(/*MainFrame f*/) {
		//gui = f;
//		Object[][] data = { {null, null, null} };
//		table = new JTable(data, columnNames);
//		add(table);
		update();
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void update() {
		if(Controller.currDataSet != null) {
			Object[][] data = new Object[Controller.currDataSet.getNumNumericalAttrs()+Controller.currDataSet.getNumCategoricalAttrs()][3];
			for(int i = 0; i < Controller.currDataSet.getNumNumericalAttrs(); ++i) {
				Object[] o = {new Integer(i+1), new String(Controller.currDataSet.getNumericName(i)), "real"};
				data[i] = o;
			}
			
			for(int i = 0; i < Controller.currDataSet.getNumCategoricalAttrs(); ++i) {
				Object[] o = {new Integer(Controller.currDataSet.getNumNumericalAttrs()+i+1), 
						new String(Controller.currDataSet.getCategoryName(i)), "category"};
				data[Controller.currDataSet.getNumNumericalAttrs()+i] = o;
			}
			if(table != null)
				remove(table);
			table = new JTable(data, columnNames);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setPreferredScrollableViewportSize(new Dimension(300, 150));
	        table.setFillsViewportHeight(true);
			add(new JScrollPane(table));
			//add(table);
		}
	}

}
//#endif
