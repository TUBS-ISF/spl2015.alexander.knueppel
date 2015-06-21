package application.features;

import framework.DataSet;
import framework.classifier.Classifier;
import framework.classifier.eval.Score;

public abstract class AbstractEvalScoreFeature implements Feature {

	public FeatureType getFeatureType() {
		// TODO Auto-generated method stub
		return Feature.FeatureType.FT_SCORE;
	}

	public abstract Score getScore();
}
