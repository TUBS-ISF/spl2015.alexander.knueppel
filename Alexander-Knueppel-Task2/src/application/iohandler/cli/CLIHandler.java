package application.iohandler.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	private void processHelp() {
		// TODO help informations
		System.out.println("This is help");
	}
	
	private void processClassify(String identifier) {
		// TODO classify data
		System.out.println("Classify " + identifier);
	}
	
	private void processLoadFile(String filename) {
		// TODO load data from a file
		System.out.println("Load " + filename);
	}
	
}
