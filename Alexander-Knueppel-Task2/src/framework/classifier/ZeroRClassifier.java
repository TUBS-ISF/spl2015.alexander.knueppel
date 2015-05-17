package framework.classifier;

import framework.DataSet;

public class ZeroRClassifier implements Classifier {
	private CategoricalResults result;
	
	public ZeroRClassifier() {
		
	}
	public ZeroRClassifier(CategoricalResults c) {
        result = c;
    }
	
	public CategoricalResults classify(DataPoint data) {
		// TODO Auto-generated method stub
		return null;
	}

	public void train(DataSet dataSet) {
		// TODO Auto-generated method stub
		
	}
	
    @Override
    public Classifier clone() {
        ZeroRClassifier clone = new ZeroRClassifier();
        if(result != null)
            clone.result = result.clone();
        return clone;
    }
}
