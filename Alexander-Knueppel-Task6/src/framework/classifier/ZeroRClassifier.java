package framework.classifier;

import framework.DataSet;
import framework.exceptions.UntrainedModelException;

public class ZeroRClassifier implements Classifier {
	private CategoricalResults result;
	private int catIndex;
	public ZeroRClassifier(int index) {
		catIndex = index;
	}
	public ZeroRClassifier(CategoricalResults c, int index) {
        result = c;
        catIndex = index;
    }
	
	public CategoricalResults classify(DataPoint data) throws UntrainedModelException {
		if(result == null)
            throw new UntrainedModelException("ZeroR Classifier has not been trained");
        return result;
	}
	
	// seems to be wrong,... change later
	public void train(DataSet dataSet) {
		result = new CategoricalResults(dataSet.getCategories()[catIndex].getNumOfCategories());
        for(int i = 0; i < dataSet.getSampleSize(); i++)
            result.incProb(dataSet.getDataPoint(i).getCategoricalValue(catIndex), dataSet.getDataPoint(i).getWeight());
        result.normalize();
	}
	
    @Override
    public Classifier clone() {
        ZeroRClassifier clone = new ZeroRClassifier(catIndex);
        if(result != null)
            clone.result = result.clone();
        return clone;
    }
	public void clear() {
	}
	public String getName() {
		return "ZeroR";
	}
	public String getDescription() {
		return "Dummy classifier without actual value";
	}
}
