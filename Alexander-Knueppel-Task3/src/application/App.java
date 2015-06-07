package application;

import java.io.IOException;
import java.util.logging.Level;

import application.iohandler.Controller;
import application.iohandler.Handler;
//#ifdef Console
import application.iohandler.cli.CLIHandler;
//#endif
//#ifdef Graphical
import application.iohandler.gui.GUIHandler;
//#endif

public class App {
	
	public static void main(String[] args) {
		Configuration config = new Configuration(true, 0, 1, 0); // development = true
		try {
			config.parseArguments(args);
			Configuration.logger.log(Level.INFO, "Arguments parsed successfully!");
			
			Controller.debug = config.dbg(); //looks so bad...
			
			Handler viewHandler;
//#ifdef Console 
//#ifdef Graphical
			if(config.cli()) {
				Configuration.logger.log(Level.INFO, "Starting command line interface...");
				viewHandler = new CLIHandler(config);
			} else {
				Configuration.logger.log(Level.INFO, "Starting graphical user interface...");
				viewHandler = new GUIHandler(config);
			}
//#else
//@			Configuration.logger.log(Level.INFO, "Starting command line interface...");
//@			viewHandler = new CLIHandler(config);
//#endif
//#else
//@			Configuration.logger.log(Level.INFO, "Starting graphical user interface...");
//@			viewHandler = new GUIHandler(config);			
//#endif
			//start the engine
			viewHandler.go();
			
		} catch(IllegalArgumentException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(config.dbg())
				e.printStackTrace();
		} catch(IOException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(config.dbg())
				e.printStackTrace();
		} catch(Exception e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(config.dbg())
				e.printStackTrace();
		}
	}

}
