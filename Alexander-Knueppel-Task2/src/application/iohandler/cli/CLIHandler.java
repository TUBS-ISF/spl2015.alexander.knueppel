package application.iohandler.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.Configuration;
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
			processHelp();
		else if(input.trim().toLowerCase().startsWith("load")) {
			String[] args = getArguments(input.trim().toLowerCase().substring(4));
			if(args == null) {
				System.out.println("Please specify filename");
			} else if(args.length > 1) {
				System.out.println("Too many arguments");
			} else
				processLoadFile(args[0]);
		} else if(input.trim().toLowerCase().startsWith("classify")) {
			String[] args = getArguments(input.trim().toLowerCase().substring(8));
			if(args == null) {
				System.out.println("Please specify algorithm");
			} else if(args.length > 1) {
				System.out.println("Too many arguments");
			} else
				processClassify(args[0]);
		} else if(!input.trim().toLowerCase().equals("quit")) {
			// TODO error
			System.out.println("Unknown command: " + input);
		}
			
	}

	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		return false;
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