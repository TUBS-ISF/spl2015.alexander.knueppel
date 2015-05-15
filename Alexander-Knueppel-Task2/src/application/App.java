package application;

import java.io.IOException;
import java.util.logging.Level;

public class App {
	
	public static void main(String[] args) {
		Configuration config = new Configuration(true);
		try {
			config.parseArguments(args);
			Configuration.logger.log(Level.INFO, "Arguments parsed successfully!");
			if(config.cli()) {
				Configuration.logger.log(Level.INFO, "Starting command line interface...");
				// add cli handler
			} else {
				Configuration.logger.log(Level.INFO, "Starting graphical userinterface...");
				// add gui handler
			}
			// add features
			
		} catch(IllegalArgumentException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
		} catch(IOException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
		} catch(Exception e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
		}
	}

}
