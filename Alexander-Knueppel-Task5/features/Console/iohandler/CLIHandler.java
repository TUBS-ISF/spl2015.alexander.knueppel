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
	public CLIHandler(Configuration config) {
		super(config);
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		do {
			System.out.print(">>> ");
			input = scanner.nextLine();
			
			processInput(input);
			
		} while(!input.equals("quit"));
		scanner.close();
	}
	
	public void processInput(String input) {
		if(input.trim().toLowerCase().equals("help"))
			printHelpMessage();
		else if(input.trim().toLowerCase().startsWith("load")) {
			String[] args = getArguments(input.trim().toLowerCase().substring(4));
			if(args == null) {
				System.out.println("Please specify filename");
			} else if(args.length > 1) {
				System.out.println("Too many arguments");
			} else {
				try {
					Controller.loadFile(args[0]);
					if(Controller.currDataSet == null)
						throw new FeatureNotFoundException("");
				} catch (FeatureNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Fileformat is not supported!");
				}
			}
		} else if(input.trim().toLowerCase().startsWith("classify")) {
			String[] args = getArguments(input.trim().toLowerCase().substring(8));
			if(args == null) {
				System.out.println("Please specify algorithm");
			} else if(args.length == 1) {
					System.out.println("Too few arguments. You must specify an algorithm and the category that will be classified after!");
			} else if(args.length > 2) {
				System.out.println("Too many arguments");
			} else
				processClassify(args[0], args[1]);
		} else if(input.trim().toLowerCase().equals("status")){
			printStatusInformation();
		} else if(!input.trim().toLowerCase().equals("quit")) {
			// TODO error
			System.out.println("Unknown command: " + input + ". Use 'help' for more informations.");
		}
			
	}

	@Override
	public boolean initialize() {
		System.out.println("Simple machine learning toolbox (SPL edition) v"+ config.versionString() +".\n"
				+ "Starting CLI... type 'help' for more information! have fun!\n");
		return true;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}
	
	// should be moved...
	
	private String[] getArguments(String input) {
		// TODO parse arguments
		if(input.trim().isEmpty())
			return null;
		String[] str = input.trim().split("\\s+");
		return str;
	}
	
	private void addFileExtensions(ArrayList<String> a) {
		//todo
	}
	
	private void addClassifiers(ArrayList<String> a) {
		//todo
	}	
	
	private void printHelpMessage() {
		String complete = "\nSimple machine learning toolbox (SPL edition).\n"
						+ "Use the following commands:\n\n"
						+ "help    Prints the message you already see ;).\n\n"
						+ "quit    Exit the program.\n\n"
						+ "status    Gets current status of loaded files.\n\n";
		
		
		ArrayList<String> fileformats = new ArrayList<String>();
		addFileExtensions(fileformats);
		
		complete += "load [file]    Supported formats:";
		if(fileformats.size() > 0) {
			for(String f : fileformats)
				complete += " *." + f + ",";
			complete = complete.substring(0, complete.length() - 1);
			complete += "\n\n";
		} else {
			complete += " None.\n";
			complete += "info:    No features were added that provide data loading mechanisms. Sry mate, you can't do anything.\n\n";
		}
		
		//ArrayList<String> classifierAlgorithms = new ArrayList<String>();
		ArrayList<String> classifierAlgorithms = new ArrayList<String>();
		addClassifiers(classifierAlgorithms);
//		for(Feature f : Controller.pluginmanager.getPlugins(AbstractClassifierFeature.class)) {
//				classifierAlgorithms.add(((AbstractClassifierFeature)f).getClassifier(Controller.currDataSet, 0).getName());
//		}
		
		//if classify... no algorithms yet...
		
		if(classifierAlgorithms.size()>0) {
			complete += "classify [algorithm] [nominal_category]   Possible algorithms are ";
			for(String c : classifierAlgorithms)
				complete += c +",";
			complete = complete.substring(0, complete.length() - 1);
			
			complete += ". Be aware that you need to load data first.\n\n";			
		}

		String[] lines = complete.split("\n");
		
		for (String line : lines) {
		    String[] parts = line.split("    ");
		    if(parts.length > 1)
		    	System.out.printf("%-20s %s%n", parts[0], parts[1]);
		    else
		    	System.out.printf("%s%n", line);
		}
	}
	
	private boolean classify(String identifier, int catIndex) {
		//to be filled by features
		return false;
	}
	
	private void processClassify(String identifier, String catName) {
		// TODO classify data
		if(Controller.currDataSet == null) {
			System.out.println("Please load some data first!");
			return;
		}
		
		
		if(Controller.currDataSet.getNumCategoricalAttrs() == 0) {
			System.out.println("Data holds no nominal data (needed for classification)");
			return;
		}
		int catIndex = 0;
		for(catIndex = 0; catIndex < Controller.currDataSet.getNumCategoricalAttrs(); ++catIndex) {
			if(catName.trim().toLowerCase().equals( Controller.currDataSet.getCategoryName(catIndex).toLowerCase())) break;
		}
		if(catIndex >= Controller.currDataSet.getNumCategoricalAttrs()) {
			catIndex = Controller.currDataSet.getNumCategoricalAttrs()-1;
		}
		if(!catName.trim().toLowerCase().equals( Controller.currDataSet.getCategoryName(catIndex).toLowerCase())) {
			System.out.println("Category '"+ catName + "' unknown'");
			System.out.println("Possible categories are: ");
			for(int i = 0; i < Controller.currDataSet.getNumCategoricalAttrs(); ++i) {
				System.out.println("  -> " + Controller.currDataSet.getCategoryName(i));
			}
			return;
		}

		boolean algorithmTriggered = classify(identifier, catIndex);
//		for(Feature f : Controller.pluginmanager.getPlugins(AbstractClassifierFeature.class)) {
//			Classifier c = ((AbstractClassifierFeature)f).getClassifier(Controller.currDataSet, catIndex);
//			if(identifier.trim().toLowerCase().equals(c.getName().toLowerCase())) {
//				System.out.println(((AbstractClassifierFeature)f).evaluate(Controller.currDataSet, catIndex));
//				algorithmTriggered = true;
//			}
//		}
		if(!algorithmTriggered) {
			System.out.println("Algorithm unknown!");
			return;
		}
	}
	
	private void printStatusInformation() {
		// TODO classify data
		if(Controller.currDataSet == null) {
			System.out.println("No data loaded!");
			return;
		}
		
		String str = "Categories:\n";
		for(CategoricalData cd : Controller.currDataSet.getCategories())  {
			str += "    '" + cd.getCategoryName() + "' = {";
			for(int i=0; i< cd.getNumOfCategories(); ++i)
				str += cd.getOptionName(i) + ",";
			str = str.substring(0, str.length()-1) + "}\n";
		}
		str += "\n";
		
		str += "Numerical attributes (REAL):\n";
		for(int i=0; i < Controller.currDataSet.getNumNumericalAttrs(); ++i) {
			str += "    " + Controller.currDataSet.getNumericName(i) + "\n";
		}
		str += "\n";
		str += "Instances: " + Controller.currDataSet.getSampleSize();
		
		System.out.println(str);
	}
	
}
