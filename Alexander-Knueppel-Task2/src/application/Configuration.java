package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.features.Feature;
import application.features.classifier.ZeroRClassifierFeature;
import application.features.fileloader.ArffFileLoaderFeature;

/**
 * Configuration class for second exercise
 * @author Alexander
 *
 */
public class Configuration {	
	/*
	 *  status variables, only a few for now for the second exercise
	 */
	//Versioning
	private int major, minor, release;
	//enable debugging for enhanced informations
	private boolean debug;
	//use command line interface instead of gui
	private boolean cli;
	//development or production
	private boolean development;
	//global logging unit
	public static Logger logger = Logger.getLogger("theLogger");
	//global feature list
	public static Set<String> featureSet = new HashSet<String>();
	
	public Configuration(boolean development, int major, int minor, int release) {
		this.development = development;
		this.major = major;
		this.minor = minor;
		this.release = release;
		clear();
	}
	
	public void clear() {
		//defaults
		debug = false;
		cli = false;
		logger.setLevel(Level.OFF); 
		
		featureSet.clear();
		// default feature list
		//featureSet.add(ArffFileLoaderFeature.class.getName());
		featureSet.add(ZeroRClassifierFeature.class.getName());
	}
	
	//getter
	public boolean dbg() {
		return debug || development; //if development mode is true, turn on debug as well
	}
	
	public boolean cli() {
		return cli;
	}
	
	public boolean dev() {
		return development;
	}
	
	public int[] version() {
		int[] version = new int[3];
		version[0] = major;
		version[1] = minor;
		version[2] = release;
		return version;
	}
	
	public String versionString(boolean complete) {
		int[] version = version();
		String str = version[0] + "." + version[1] + "." +version[2];
		if(complete)
			str += (dev() ? " (development)" : " (stable)");
		return str;
	}
	
	public String versionString() {
		return versionString(true);
	}
	
	public void printUsage() {
		String[] lines = ("Simple machine learning toolbox (SPL edition) v"+ versionString() +".\n"
						+ "Usage: app [parameter1] [parameter2] ...\n\n"
						+ "Parameter:\n\n"
						+ "--cli    Start the command line interface. Otherwise a GUI will be represented.\n\n"
						+ "--logging[=file|=console]    Turn on logging. Specify wether a persistend file or the console will"
						+ " be used (or both)\n\n"
						+ "--debug    Turn on debug mode. If development mode is activated, debug mode will be automatically activated. \n\n"
						+ "--surpress-debug    Turns development- and debug-mode off. I.e. emulates production release. \n\n"
						+ "--arff    Add *.arff file loading support.").split("\n");
		for (String line : lines) {
		    String[] parts = line.split("    ");
		    if(parts.length > 1)
		    	System.out.printf("%-27s %s%n", parts[0], parts[1]);
		    else
		    	System.out.printf("%s%n", line);
		}
	}
	public void parseArguments(String[] args) throws IllegalArgumentException, SecurityException, IOException {
		clear();
		
		for(String arg : args) {
			if(arg.equalsIgnoreCase("--cli"))
				cli = true;
			else if(arg.equalsIgnoreCase("--help")) {
				printUsage();
				System.exit(0); //close program normally
			}
			else if(arg.equalsIgnoreCase("--debug")) {
				debug = true;
				logger.setLevel(Level.FINEST);
			}
			else if(arg.equalsIgnoreCase("--surpress-debug")) {
				debug = development = false;
				logger.setLevel(Level.OFF);
			}
			else if(arg.toLowerCase().startsWith("--logging")) { //logging file created under /user/
				if(arg.equalsIgnoreCase("--logging=console"))
					logger.addHandler(new ConsoleHandler());
				else if(arg.equalsIgnoreCase("--logging=file"))
					logger.addHandler(new FileHandler());
				else if(arg.equalsIgnoreCase("--logging")) {
					logger.addHandler(new FileHandler());
					//logger.addHandler(new ConsoleHandler()); //?? ConsoleHandler already added?
				} else {
					throw new IllegalArgumentException("Wrong usage of '--logging'. '" + arg + "' is not a valid argument. Execution abborted.");
				}
				if(!logger.getLevel().equals(Level.FINEST))
					logger.setLevel(Level.INFO);
			}
//			else if(arg.equalsIgnoreCase("--nodat"))
//				dataFormats.remove(DataFormats.DF_DAT);
//			else if(arg.equalsIgnoreCase("--nocsv"))
//				dataFormats.remove(DataFormats.DF_CSV);
			else if(arg.equalsIgnoreCase("--arff")) //turn off arff support
				featureSet.add(ArffFileLoaderFeature.class.getName());
			else
				throw new IllegalArgumentException("'" + arg + "' is not a valid argument. Execution abborted.");
		}
	}
	
}
