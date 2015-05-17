package application.features.classifier;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.features.Feature;
import application.features.Feature.FeatureType;
import framework.DataSet;
import framework.classifier.DataPoint;

public abstract class AbstractClassifierFeature implements Feature {

	public AbstractClassifierFeature() {}
	
	public DataSet dataSet;
//	public abstract void train(DataSet ds);
//	public abstract void classify(DataPoint dp);
	
	public abstract String evaluate(DataSet ds); //<!!! only for ex2,... not enough time...
	
	public FeatureType getFeatureType() {
		// TODO Auto-generated method stub
		return Feature.FeatureType.FT_CLASSIFIER;
	}
}

