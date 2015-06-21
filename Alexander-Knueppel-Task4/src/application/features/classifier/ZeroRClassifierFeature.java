package application.features.classifier;

import application.features.AbstractClassifierFeature;
import framework.DataSet;
import framework.classifier.Classifier;
import framework.classifier.ZeroRClassifier;

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
