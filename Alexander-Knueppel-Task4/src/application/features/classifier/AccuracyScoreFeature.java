package application.features.classifier;

import application.features.AbstractEvalScoreFeature;
import framework.classifier.eval.Accuracy;
import framework.classifier.eval.Score;

public class AccuracyScoreFeature extends AbstractEvalScoreFeature {

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "Accuracy-score";
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Calculates quality through accuracy";
	}

	@Override
	public Score getScore() {
		// TODO Auto-generated method stub
		return new Accuracy();
	}

}
