package application.iohandler.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import framework.CategoricalData;
import application.Configuration;
import application.features.fileloader.ArffFileLoaderFeature;
import application.iohandler.Controller;
import application.iohandler.FeatureNotFoundException;
import application.iohandler.Handler;

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
		// TODO Parse input and choose right algorithms etc.
		// * load data.dat
		// * classify *algorithm* [parameter?]
		
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
				} catch (FileNotFoundException e) {
					System.out.println("File is not found!");
				} catch (FeatureNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Fileformat is not supported!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("Interrupted I/O Application. Please Debug application.");
				}
			}
		} else if(input.trim().toLowerCase().startsWith("classify")) {
			String[] args = getArguments(input.trim().toLowerCase().substring(8));
			if(args == null) {
				System.out.println("Please specify algorithm");
			} else if(args.length > 1) {
				System.out.println("Too many arguments");
			} else
				processClassify(args[0]);
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
	
	private void printHelpMessage() {
		String complete = "\nSimple machine learning toolbox (SPL edition).\n"
						+ "Use the following commands:\n\n"
						+ "help    Prints the message you already see ;).\n\n"
						+ "quit    Exit the program.\n\n"
						+ "status    Gets current status of loaded files.\n\n";
		ArrayList<String> fileformats = new ArrayList<String>();
		
		if(Configuration.featureSet.contains(ArffFileLoaderFeature.class.getName()))
			fileformats.add("arff");
		
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
		
		//if classify... no algorithms yet...
		complete += "classify [algorithm]    Possible algorithms are [...]. Be aware that you need to load data first.\n\n";
		
		String[] lines = complete.split("\n");
		
		for (String line : lines) {
		    String[] parts = line.split("    ");
		    if(parts.length > 1)
		    	System.out.printf("%-20s %s%n", parts[0], parts[1]);
		    else
		    	System.out.printf("%s%n", line);
		}
	}
	
	private void processClassify(String identifier) {
		// TODO classify data
		if(Controller.currDataSet == null) {
			System.out.println("Please load some data first!");
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
