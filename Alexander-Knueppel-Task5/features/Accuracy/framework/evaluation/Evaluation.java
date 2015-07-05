package framework.evaluation;

import framework.evaluation.SimpleEvaluation;

import framework.CategoricalData;
import framework.DataSet;
import framework.scores.Score;
import framework.classifier.Classifier;
import framework.scores.Accuracy;

public class Evaluation {
	
	private static void addScores(CategoricalData cd, SimpleEvaluation ev) {
		original(cd, ev);
		Score s = new Accuracy();
		s.prepare(cd);
		ev.addScore(s);
	}
	
}
