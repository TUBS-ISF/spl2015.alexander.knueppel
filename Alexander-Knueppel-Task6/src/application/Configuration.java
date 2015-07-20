package application;

import java.io.IOException;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	//public static Set<String> featureSet = new HashSet<String>();
	
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
		
//		featureSet.clear();
//		// default feature list
//
//		featureSet.add(ArffFileLoaderFeature.class.getName());
//
//		featureSet.add(ZeroRClassifierFeature.class.getName());
//
//		featureSet.add(IB1ClassifierFeature.class.getName());

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
						+ "Usage: app [interface]\n\n"
						+ "Interface:\n\n"
						+ "--cli    Start the command line interface. \n"
						+ "--gui    Start the graphical user interface. \n").split("\n");
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
//		if(args.length == 0) {
//		printUsage();
//		System.exit(0); //close program normally	
//	}
//	for(String arg : args) {
//		if(arg.equalsIgnoreCase("--cli"))
//			cli = true;
//		if(arg.equalsIgnoreCase("--gui"))
//			cli = false;
//		else if(arg.equalsIgnoreCase("--help")) {
//			printUsage();
//			System.exit(0); //close program normally
//		}
//		else
//			

		logger.addHandler(new FileHandler());

		if(!logger.getLevel().equals(Level.FINEST))
			logger.setLevel(Level.INFO);		
		
//if(debug)
//		debug = true;
//		logger.setLevel(Level.FINEST);
//#else
//@		logger.setLevel(Level.OFF);
//#endif
	}
	
}
