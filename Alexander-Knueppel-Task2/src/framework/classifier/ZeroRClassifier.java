package framework.classifier;

import framework.DataSet;
import framework.exceptions.UntrainedModelException;

public class ZeroRClassifier implements Classifier {
	private CategoricalResults result;
	
	public ZeroRClassifier() {
		
	}
	public ZeroRClassifier(CategoricalResults c) {
        result = c;
    }
	
	public CategoricalResults classify(DataPoint data) throws UntrainedModelException {
		if(result == null)
            throw new UntrainedModelException("ZeroR Classifier has not been trained");
        return result;
	}
	
	// seems to be wrong,... change later
	public void train(DataSet dataSet) {
		result = new CategoricalResults(dataSet.getCategories()[0].getNumOfCategories());
        for(int i = 0; i < dataSet.getSampleSize(); i++)
            result.incProb(dataSet.getDataPoint(i).getCategoricalValue(0), dataSet.getDataPoint(i).getWeight());
        result.normalize();
	}
	
    @Override
    public Classifier clone() {
        ZeroRClassifier clone = new ZeroRClassifier();
        if(result != null)
            clone.result = result.clone();
        return clone;
    }
}
