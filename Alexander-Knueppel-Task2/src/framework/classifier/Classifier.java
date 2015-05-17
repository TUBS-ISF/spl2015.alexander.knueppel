package framework.classifier;

import framework.DataSet;

public interface Classifier extends Cloneable {
	public CategoricalResults classify(DataPoint data);
	public void train(DataSet dataSet);
}
