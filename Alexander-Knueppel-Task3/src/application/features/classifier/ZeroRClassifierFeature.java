package application.features.classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import framework.DataSet;
import framework.SimpleDataSet;
import framework.classifier.CategoricalResults;
import framework.classifier.Classifier;
import framework.classifier.DataPoint;
import framework.classifier.ZeroRClassifier;
import framework.exceptions.UntrainedModelException;

public class ZeroRClassifierFeature extends AbstractClassifierFeature {

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "zeror-classifier-feature";
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Implements ZeroR classification (actually a dummy...)";
	}

	@Override
	public Classifier getClassifier(DataSet ds, int catIndex) {
		// TODO Auto-generated method stub
		return new ZeroRClassifier(catIndex);
	}

}
