package application.features.classifier;

import java.util.ArrayList;
import java.util.List;
import framework.DataSet;
import framework.SimpleDataSet;
import framework.classifier.CategoricalResults;
import framework.classifier.Classifier;
import framework.classifier.DataPoint;
import framework.classifier.eval.Score;
import framework.exceptions.UntrainedModelException;

public class SimpleEvaluation implements Evaluation {
	private List<Score> scores = new ArrayList<Score>();
	private int correctClassified;
	private int totalClassified;
	private DataSet currData;
	private Classifier currClassifier;
	private int nFolds;
	private int currCatIndex;
	private int[][] confusionMatrix;
	
	public SimpleEvaluation(int n) {
		nFolds = n;
	}
	
	public void evaluate(Classifier c, DataSet ds, int catIndex) {
		int numberValidationElements = ds.getSampleSize() / nFolds;
		int n = ds.getCategories()[catIndex].getNumOfCategories();
		
		currData = ds;
		currCatIndex = catIndex;
		currClassifier = c;
		correctClassified = totalClassified = 0;
		
		confusionMatrix = new int[n][n];
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				confusionMatrix[i][j] = 0;
		
		for(int i = 0; i < nFolds; ++i) {
			//prepare new DataSet
			List<DataPoint> validationList = ds.getDataPoints();
			List<DataPoint> testList = new ArrayList<DataPoint>();
			
			for(int j = 0; j < numberValidationElements; ++j) {
				testList.add(validationList.get(i*numberValidationElements));
				validationList.remove(i*numberValidationElements);
			}
			
			c.clear();
			c.train(new SimpleDataSet(validationList));
			
			for(DataPoint dp : testList) {
				try {
					CategoricalResults cr = c.classify(dp);
					if(cr.mostLikely() == dp.getCategoricalValue(catIndex))
						correctClassified++;
					totalClassified++;
					
					confusionMatrix[dp.getCategoricalValue(catIndex)][cr.mostLikely()] += 1;
					
					for(Score s : scores) {
						s.addResult(cr, dp.getCategoricalValue(catIndex), 1.0);
					}
					
				} catch (UntrainedModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void addScore(Score sc) {
		scores.add(sc);
	}

	public Score[] getScores() {
		Score[] sc = (Score[]) scores.toArray();
		return sc;
	}

	public int[][] getConfusionMatrix() {
		if(currData == null)
			throw new RuntimeException("Evaluate dataset first");
		// TODO Auto-generated method stub
		return confusionMatrix;
	}

	public int correctClassified() {
		if(currData == null)
			throw new RuntimeException("Evaluate dataset first");
		return correctClassified;
	}

	public String getResultString() {
		if(currData == null)
			throw new RuntimeException("Evaluate dataset first");
		
		String str = "=== Run classification ===\n\n";
		str+= "Instances: " + currData.getSampleSize() + "\n";
		str+= "Attributes: " + (currData.getNumNumericalAttrs() + currData.getNumCategoricalAttrs()) + "\n";
		for(int i = 0; i < currData.getNumNumericalAttrs(); ++i)
			str+="            " + currData.getNumericName(i) + "\n";
		for(int i = 0; i < currData.getNumCategoricalAttrs(); ++i)
			str+="            " + currData.getCategoryName(i) + "\n\n";
		
		str+="Mode: Cross-Validation with (" + nFolds + "-fold)\n\n";
		
		str+="Classifier: " + currClassifier.getName() + "\n";
		str += currClassifier.getDescription() + "\n\n";
		str += "=== Score Summary ===\n";
		str+="Correctly classified: " + correctClassified + "\n";
		str+="Incorrectly classified: " + (totalClassified-correctClassified) + "\n";
		
		for(Score s : scores) {
			str+=s.getName() + ": " + s.getScore()+"\n";
		}
		
		str+="\n";
		str+="=== Confusion Matrix ===\n\n  ";
		
		for(int i = 0; i < confusionMatrix.length; ++i) 
			str += (char)('a'+i) + " "; 
		str += " <-- classified as\n";
		
		for(int i = 0; i < confusionMatrix.length; ++i) {
			str += "  ";
			for(int j = 0; j < confusionMatrix.length; ++j) {
				str += confusionMatrix[i][j] + " ";
			}
			str += "| " + (char)('a'+i) + " = " + currData.getCategories()[currCatIndex].getOptionName(i) + "\n";
		}
		str += "\n";
		
		return str;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "Simple evaluation";
	}

	public int totalClassified() {
		// TODO Auto-generated method stub
		return totalClassified;
	}


}
