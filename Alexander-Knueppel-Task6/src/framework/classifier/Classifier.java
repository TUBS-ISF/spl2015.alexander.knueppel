package framework.classifier;

import framework.DataSet;
import framework.exceptions.UntrainedModelException;

public interface Classifier extends Cloneable {
	public CategoricalResults classify(DataPoint data) throws UntrainedModelException;
	public void train(DataSet dataSet);
	public void clear();
	public String getName();
	public String getDescription();
}
