package framework.scores;

import framework.CategoricalData;
import framework.classifier.CategoricalResults;

/**
 * Implements Cohens kappa
 * @author Alexander
 *
 */
public class Kappa implements Score {
	double[][] errMatrix; //row/column
	public double getScore() {
		double[] rowTotals = new double[errMatrix.length];
        double[] colTotals = new double[errMatrix.length];
        for(int i = 0; i < errMatrix.length; i++) {
        	double rowSum = 0.0, colSum = 0.0;
        	for(int j = 0; j < errMatrix.length; j++) {
        		rowSum += errMatrix[i][j];
        		colSum += errMatrix[j][i];
        	}
            rowTotals[i] = rowSum;
            colTotals[i] = colSum;
        }
        
        double chanceAgreement = 0;
        double accuracy = 0;
        double totalCount = 0;
        for(int i = 0; i < rowTotals.length; i++) {
            chanceAgreement += rowTotals[i]*colTotals[i];
            totalCount += rowTotals[i];
            accuracy += errMatrix[i][i];
        }
        chanceAgreement /= totalCount*totalCount;
        accuracy /= totalCount;
        
        return (accuracy-chanceAgreement)/(1-chanceAgreement);  
	}

	public void prepare(CategoricalData cd) {
		int n = cd.getNumOfCategories();
        errMatrix = new double[n][n];
        for(int i = 0; i < n; ++i) {
        	for(int j = 0; j < n; ++j) {
        		errMatrix[i][j] = 0;
        	}
        }
	}

	public void addResult(CategoricalResults cr, int groundTruth, double weight) {
		errMatrix[cr.mostLikely()][groundTruth] += weight;
	}

	public void addResults(Score other) {
		Kappa otherObj = (Kappa) other;
		if(otherObj.errMatrix == null)
            return;
		if(this.errMatrix == null)
            throw new RuntimeException("KappaScore has not been prepared");
		
        for(int i = 0; i < errMatrix.length; ++i) {
        	for(int j = 0; j < errMatrix[i].length; ++j) {
        		errMatrix[i][j] = otherObj.errMatrix[i][j];
        	}
        }
	}

	public String getName() {
		return "Kappa";
	}

}
