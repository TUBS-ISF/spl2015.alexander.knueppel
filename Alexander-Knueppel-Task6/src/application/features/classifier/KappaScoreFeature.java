package application.features.classifier;

import application.features.AbstractEvalScoreFeature;
import framework.scores.Kappa;
import framework.scores.Score;

public class KappaScoreFeature extends AbstractEvalScoreFeature {

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "kappa-score";
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Calculates quality through kappa statistics";
	}

	@Override
	public Score getScore() {
		// TODO Auto-generated method stub
		return new Kappa();
	}

}
