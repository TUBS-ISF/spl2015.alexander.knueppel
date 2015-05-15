package application.iohandler.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.Configuration;
import application.iohandler.Handler;

public class CLIHandler extends Handler {
	private class Command {
		public String command;
		public String featureName;
		public int numberArguments;
		
	}
	private List<Command> commands;

	public CLIHandler(Configuration config) {
		super(config);
		commands = new ArrayList<Command>();
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		do {
			System.out.print(">>> ");
			input = scanner.next();
			
			processInput(input);
			
		} while(!input.equals("quit"));
		scanner.close();
	}
	
	public void processInput(String input) {
		// TODO Parse input and choose right algorithms etc.
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
	
}
