package application.features.classifier;

import application.features.Feature;
import application.features.Feature.FeatureType;
import framework.DataSet;

public abstract class AbstractClassifierFeature implements Feature {

	public AbstractClassifierFeature() {}

	public FeatureType getFeatureType() {
		// TODO Auto-generated method stub
		return Feature.FeatureType.FT_CLASSIFIER;
	}
}

