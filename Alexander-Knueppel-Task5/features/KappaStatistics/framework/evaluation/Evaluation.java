package framework.evaluation;

import framework.evaluation.SimpleEvaluation;

import framework.CategoricalData;
import framework.DataSet;
import framework.scores.Score;
import framework.classifier.Classifier;
import framework.scores.Kappa;

public class Evaluation {
	
	private static void addScores(CategoricalData cd, SimpleEvaluation ev) {
		original(cd, ev);
		Score s = new Kappa();
		s.prepare(cd);
		ev.addScore(s);
	}
	
}
