package application;
import java.io.IOException;
import java.util.logging.Level;

import application.Configuration;
import iohandler.Handler;
import iohandler.CLIHandler;

public class App {
	
	public static void go(Configuration c) {
		Handler viewHandler;
		Configuration.logger.log(Level.INFO, "Starting GUI interface...");
		viewHandler = new GUIHandler(c);
		viewHandler.go();
	}

}