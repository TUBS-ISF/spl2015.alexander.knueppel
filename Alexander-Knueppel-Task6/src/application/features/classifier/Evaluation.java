package application.features.classifier;

import framework.DataSet;
import framework.classifier.Classifier;
import framework.scores.Score;

public interface Evaluation {
	public void evaluate(Classifier c, DataSet ds, int catIndex);
	public void addScore(Score sc);
	public Score[] getScores();
	public int[][] getConfusionMatrix();
	public int correctClassified();
	public int totalClassified();
	public String getResultString();
	public String getName();
}
