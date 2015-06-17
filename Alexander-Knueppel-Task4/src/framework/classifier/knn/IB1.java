package framework.classifier.knn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import framework.CategoricalData;
import framework.DataSet;
import framework.classifier.CategoricalResults;
import framework.classifier.Classifier;
import framework.classifier.DataPoint;
import framework.exceptions.UntrainedModelException;
import framework.linear.metrics.DistanceMetric;
import framework.linear.metrics.EuclidianDistance;

/**
 * ID1 is a pretty simple nn-Algorithm.
 * While training, it basicly stores all instances as a training set (storage consuming). 
 * If a new instance is classified, a choosen similarity function (e.g. adapted euclidian distance) is used to find the most 
 * similiar instance in the traning set.
 * ID1 than assumes, that the nominal category of both instances is the same.
 * @author Alexander
 *
 */

public class IB1 implements Classifier {
	private CategoricalData catData; //nominal value of category that we're after
	private int catIndex;
    private Set<DataSet> trainingSet = new HashSet<DataSet>();
	
    
    public IB1(CategoricalData c, int index) {
    	catData = c;
    	catIndex = index;
    }
    //e.g. euclidian distance for numeric values and +1 for equal nominal values
    private double similarity(DataPoint d1, DataPoint d2) {
    	EuclidianDistance ed = new EuclidianDistance();
    	double value = ed.distance(d1.getNumericalValues(), d2.getNumericalValues());
    	value *= value;
//    	for(int i = 0; i < d1.numCategoricalValues(); ++i) {
//    		if(d1.getCategoricalValue(i) == d2.getCategoricalValue(i) && catIndex != i)
//    			value++;
//    	}
		return Math.sqrt(value);
    }
    
	
	public CategoricalResults classify(DataPoint data) throws UntrainedModelException {
		if(trainingSet.isEmpty())
            throw new UntrainedModelException("IB1 Classifier has not been trained");
		
		CategoricalResults result = new CategoricalResults(catData.getNumOfCategories());
		double bestValue = Double.MAX_VALUE;
		DataPoint bestMatch = null;
		
		for(DataSet t : trainingSet) {
			for(DataPoint dp : t.getDataPoints()) {
				double v = similarity(dp, data);
				if(v < bestValue) {
					bestValue = v;
					bestMatch = dp;
				}
			}
		}
		
		result.setProb(bestMatch.getCategoricalValue(catIndex), 1.0);
		int category = bestMatch.getCategoricalValue(catIndex);
		//result.normalize();
        return result;
	}
	
	// seems to be wrong,... change later
	public void train(DataSet dataSet) {
		if(dataSet.getNumCategoricalAttrs() == 0 || dataSet.getNumCategoricalAttrs() < catIndex)
            throw new RuntimeException("IB1 needs a valid nominal category");
		
		trainingSet.add(dataSet);
	}
	
    @Override
    public Classifier clone() {
        IB1 clone = new IB1(catData, catIndex);
        return clone;
    }
	public void clear() {
		trainingSet.clear();
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "IB1";
	}
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Classifies data through 1-Nearest-Neighbour search (instance based classifier)";
	}
}
