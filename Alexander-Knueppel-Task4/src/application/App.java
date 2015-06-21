package application;

import java.io.IOException;
import java.util.logging.Level;

import application.features.AbstractClassifierFeature;
import application.features.AbstractEvalScoreFeature;
import application.features.AbstractFileLoaderFeature;
import application.features.classifier.AccuracyScoreFeature;
import application.features.classifier.IB1ClassifierFeature;
import application.features.classifier.KappaScoreFeature;
import application.features.classifier.ZeroRClassifierFeature;
import application.features.fileloader.ArffFileLoaderFeature;
import application.features.fileloader.CsvFileLoaderFeature;
import application.iohandler.Controller;
import application.iohandler.Handler;
import application.iohandler.cli.CLIHandler;
import application.iohandler.gui.GUIHandler;


public class App {
	
	public static void main(String[] args) {
		Configuration config = new Configuration(true, 0, 1, 0); // development = true
		try {
			config.parseArguments(args);
			Configuration.logger.log(Level.INFO, "Arguments parsed successfully!");
			
			Controller.debug = config.dbg(); 
			
			// Classifier Plugins
			Controller.pluginmanager.register(AbstractClassifierFeature.class, new ZeroRClassifierFeature());
			Controller.pluginmanager.register(AbstractClassifierFeature.class, new IB1ClassifierFeature());
			// File loading Plugins
			Controller.pluginmanager.register(AbstractFileLoaderFeature.class, new ArffFileLoaderFeature());
			Controller.pluginmanager.register(AbstractFileLoaderFeature.class, new CsvFileLoaderFeature());
			//dat
			//Evaluation quality
			Controller.pluginmanager.register(AbstractEvalScoreFeature.class, new KappaScoreFeature());
			Controller.pluginmanager.register(AbstractEvalScoreFeature.class, new AccuracyScoreFeature());
			
			Handler viewHandler;

			if(config.cli()) {
				Configuration.logger.log(Level.INFO, "Starting command line interface...");
				viewHandler = new CLIHandler(config);
			} else {
				Configuration.logger.log(Level.INFO, "Starting graphical user interface...");
				viewHandler = new GUIHandler(config);
			}
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
