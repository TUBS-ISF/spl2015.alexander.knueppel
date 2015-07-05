package iohandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import framework.CategoricalData;
import framework.classifier.Classifier;
import application.Configuration;
import application.Controller;
import iohandler.Handler;
import framework.exceptions.FeatureNotFoundException;

import framework.evaluation.Evaluation;
import framework.classifier.knn.IB1;

public class CLIHandler extends Handler {
	
	public static String CLASSIFIER_IB1_COMMAND = "IB1";
	
	private void addClassifiers(ArrayList<String> a) {
		original(a);
		a.add(CLIHandler.CLASSIFIER_IB1_COMMAND);
	}
	
	private boolean classify(String identifier, int catIndex) {
		boolean res = original(identifier, catIndex);
		if(identifier.trim().toLowerCase().equals(CLIHandler.CLASSIFIER_IB1_COMMAND)) {
			Classifier c = new IB1(Controller.currDataSet.getCategories()[catIndex].clone(), catIndex);
			System.out.println(Evaluation.evaluate(c, Controller.currDataSet, catIndex));
			res = true;
		}
		return res;
	}
}
