package framework.classifier;

public interface Classifier extends Cloneable {
	public CategoricalResults classify(DataPoint data);
}
