//#ifdef IB1
package application.features.classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import framework.CategoricalData;
import framework.DataSet;
import framework.SimpleDataSet;
import framework.classifier.CategoricalResults;
import framework.classifier.Classifier;
import framework.classifier.DataPoint;
import framework.classifier.ZeroRClassifier;
import framework.classifier.eval.Accuracy;
import framework.classifier.eval.Score;
import framework.classifier.knn.IB1;
import framework.exceptions.UntrainedModelException;

public class IB1ClassifierFeature extends AbstractClassifierFeature {

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "ib1-classifier-feature";
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Implements instance based classification";
	}

	@Override
	public Classifier getClassifier(DataSet ds, int catIndex) {
		if(ds == null)
			return new IB1(null,catIndex);
		return new IB1(ds.getCategories()[catIndex],catIndex);
	}
}
//#endif
