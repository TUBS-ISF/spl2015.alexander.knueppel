package application.features.classifier;

import java.util.ArrayList;
import java.util.List;

import application.features.Feature;
import framework.CategoricalData;
import framework.DataSet;
import framework.classifier.Classifier;
import framework.classifier.eval.Accuracy;
//#ifdef KappaStatistics
import framework.classifier.eval.Kappa;
//#endif
import framework.classifier.eval.Score;
//#ifdef IB1
import framework.classifier.knn.IB1;
//#endif

public abstract class AbstractClassifierFeature implements Feature {

	public AbstractClassifierFeature() {}
	
	public DataSet dataSet;
	
	public static List<AbstractClassifierFeature> classifiers;
	
	static {
		classifiers = new ArrayList<AbstractClassifierFeature>();
		classifiers.add(new ZeroRClassifierFeature());
//#ifdef IB1
		classifiers.add(new IB1ClassifierFeature());
//#endif
	}
	
	public String evaluate(DataSet ds, int catIndex) { //for ex03
		Evaluation ev = new SimpleEvaluation(10); //10 folds cross validation
		CategoricalData cd = ds.getCategories()[catIndex].clone();
//#ifdef KappaStatistics
		Score kappa = new Kappa();
		kappa.prepare(cd);
//#endif
		
		Score accuracy = new Accuracy();
		accuracy.prepare(cd);
//#ifdef KappaStatistics
		ev.addScore(kappa);
//#endif
		ev.addScore(accuracy);
		
		ev.evaluate(getClassifier(ds, catIndex), ds, catIndex);
		
		return ev.getResultString();
	}
	
	public abstract Classifier getClassifier(DataSet ds, int catIndex);
	
	public FeatureType getFeatureType() {
		return Feature.FeatureType.FT_CLASSIFIER;
	}
}
