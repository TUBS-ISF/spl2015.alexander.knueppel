package application.iohandler.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import application.iohandler.Controller;

public class PreprocessData extends JPanel {
	private JLabel instances;
	private JLabel cat_attributes;
	private JLabel num_attributes;
	
	public PreprocessData() {
		setLayout(new GridLayout(2,2));
	    cat_attributes = new JLabel();
		instances = new JLabel();
		num_attributes = new JLabel();
		add(cat_attributes);
		add(instances);
		add(num_attributes);
		setPreferredSize(new Dimension(350, 50));
		update();
	}
	
	public void update() {
		cat_attributes.setText("Categorical Attributes: ");
		instances.setText("Instances: ");
		num_attributes.setText("Numerical Attributes: ");
		if(Controller.currDataSet != null) {
			cat_attributes.setText(cat_attributes.getText() + Controller.currDataSet.getNumCategoricalAttrs());
			instances.setText(instances.getText() + Controller.currDataSet.getSampleSize());
			num_attributes.setText(num_attributes.getText() + Controller.currDataSet.getNumNumericalAttrs());
		}
	}
}
