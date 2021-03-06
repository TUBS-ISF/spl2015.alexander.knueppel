package application.features;

import java.util.ArrayList;
import java.util.List;

import application.features.classifier.Evaluation;
import application.features.classifier.SimpleEvaluation;
import application.iohandler.Controller;
import framework.CategoricalData;
import framework.DataSet;
import framework.classifier.Classifier;
import framework.classifier.eval.Accuracy;
import framework.classifier.eval.Kappa;
import framework.classifier.eval.Score;
import framework.classifier.knn.IB1;


public abstract class AbstractClassifierFeature implements Feature {

	public AbstractClassifierFeature() {}
	
	public DataSet dataSet;
	
//	public static List<AbstractClassifierFeature> classifiers;
//	
//	static {
//		classifiers = new ArrayList<AbstractClassifierFeature>();
//		classifiers.add(new ZeroRClassifierFeature());
//		classifiers.add(new IB1ClassifierFeature());
//	}
	
	public String evaluate(DataSet ds, int catIndex) { //for ex03
		Evaluation ev = new SimpleEvaluation(10); //10 folds cross validation
		CategoricalData cd = ds.getCategories()[catIndex].clone();
		
		for(Feature f : Controller.pluginmanager.getPlugins(AbstractEvalScoreFeature.class)) {
			Score s = ((AbstractEvalScoreFeature)f).getScore();
			s.prepare(cd);
			ev.addScore(s);
		}
//		Score kappa = new Kappa();
//		kappa.prepare(cd);
//		
//		Score accuracy = new Accuracy();
//		accuracy.prepare(cd);
//		
//		ev.addScore(kappa);
//
//		ev.addScore(accuracy);
		
		ev.evaluate(getClassifier(ds, catIndex), ds, catIndex);
		
		return ev.getResultString();
	}
	
	public abstract Classifier getClassifier(DataSet ds, int catIndex);
	
	public FeatureType getFeatureType() {
		return Feature.FeatureType.FT_CLASSIFIER;
	}
}
