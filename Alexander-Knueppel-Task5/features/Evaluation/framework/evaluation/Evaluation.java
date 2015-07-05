package framework.evaluation;

import framework.evaluation.SimpleEvaluation;
import framework.CategoricalData;
import framework.DataSet;
import framework.scores.Score;
import framework.classifier.Classifier;

public class Evaluation {
	
	private static void addScores(CategoricalData cd, SimpleEvaluation ev) {
		//todo
	}
	
	public static String evaluate(Classifier c, DataSet ds, int catIndex) { //for ex03
		SimpleEvaluation ev = new SimpleEvaluation(10); //10 folds cross validation
		CategoricalData cd = ds.getCategories()[catIndex].clone();
		
		addScores(cd, ev);
		
		ev.evaluate(c, ds, catIndex);
		
		return ev.getResultString();
	}
}
