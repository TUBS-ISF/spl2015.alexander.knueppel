package application.features.classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import framework.DataSet;
import framework.SimpleDataSet;
import framework.classifier.CategoricalResults;
import framework.classifier.DataPoint;
import framework.classifier.ZeroRClassifier;
import framework.exceptions.UntrainedModelException;

public class ZeroRClassifierFeature extends AbstractClassifierFeature {

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "zeror-classifier-feature";
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Implements ZeroR classification (actually a dummy...)";
	}

	@Override
	public String evaluate(DataSet ds) {
		//Only for cli, 10 fold cross validation, returning result string
		
		int folds = 10;
		int currTestFold = 0;
		int numberValidationElements = ds.getSampleSize() / folds;
		
		int n = ds.getCategories()[0].getNumOfCategories();
		
		Vector<Double> overallProb = new  Vector<Double>();
		for(int i=0; i < n; ++i)
			overallProb.addElement(new Double(0.0));

		String str = "";
		
		for(int i = 0; i < folds; ++i) {
			//prepare new DataSet
			List<DataPoint> validationList = ds.getDataPoints();
			for(int j = 0; 
					j < numberValidationElements; 
					++j) {
				validationList.remove(i*numberValidationElements);
			}
			
			ZeroRClassifier c = new ZeroRClassifier();
			c.train(new SimpleDataSet(validationList));
			
			try {
				CategoricalResults cr = c.classify(null);
				str += "Fold " + (i+1) + ":\n";
				for(int k = 0; k < n; ++k) {
					str += "   " + ds.getCategories()[0].getOptionName(k) + ": " + cr.getProbability(k) + "\n";
					overallProb.set(k, overallProb.get(k) + cr.getProbability(k));
				}
				
			} catch (UntrainedModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currTestFold++;
		}
		str += "Overall probability: \n";
		for(int k = 0; k < n; ++k) {
			str += "   " + ds.getCategories()[0].getOptionName(k) + ": " + (overallProb.get(k) / folds)  + "\n";
		}
		
		return str;
	}

//	@Override
//	public void train(DataSet ds) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void classify(DataPoint dp) {
//		// TODO Auto-generated method stub
//		
//	}

}
