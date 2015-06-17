package framework.classifier.eval;

import framework.CategoricalData;
import framework.classifier.CategoricalResults;

public interface Score {
	public double getScore();
	public void prepare(CategoricalData cd);
	public void addResult(CategoricalResults cr, int groundTruth, double weight);
	public void addResults(Score other);
	public String getName();
}
