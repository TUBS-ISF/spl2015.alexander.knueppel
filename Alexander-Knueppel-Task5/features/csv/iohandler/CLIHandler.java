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

public class CLIHandler extends Handler {
	
	private void addFileExtensions(ArrayList<String> a) {
		original(a);
		a.add("csv");
	}
}
