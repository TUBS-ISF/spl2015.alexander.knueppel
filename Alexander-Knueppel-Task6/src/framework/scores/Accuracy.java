package framework.scores;

import framework.CategoricalData;
import framework.classifier.CategoricalResults;

public class Accuracy implements Score {
	private double correct, total;
	public double getScore() {
		return correct/total;
	}

	public void prepare(CategoricalData cd) {
		correct = 0;
        total = 0;
	}

	public void addResult(CategoricalResults cr, int groundTruth, double weight) {
		if(cr.mostLikely() == groundTruth)
            correct += weight;
        total += weight;
	}

	public String getName() {
		return "Accuracy";
	}

	public void addResults(Score other) {
		Accuracy otherObj = (Accuracy) other;
        this.correct += otherObj.correct;
        this.total += otherObj.total;
	}

}
