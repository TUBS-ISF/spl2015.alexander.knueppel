package application;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configuration class for second exercise
 * @author Alexander
 *
 */
public class Configuration {
	public enum DataFormats {
		DF_DAT, DF_CSV, DF_ARFF
	}
	
	/*
	 *  status variables, only a few for now for the second exercise
	 */
	//enable debugging for enhanced informations
	private boolean debug;
	//use command line interface instead of gui
	private boolean cli;
	//development or production
	private boolean development;
	//list of allowed data formats
	private Set<DataFormats> dataFormats; 
	//global logging unit
	static Logger logger = Logger.getLogger("theLogger");
	
	public Configuration(boolean development) {
		this.development = development;
		clear();
	}
	
	public void clear() {
		//defaults
		debug = false;
		cli = false;
		dataFormats = new HashSet<DataFormats>();
		dataFormats.add(DataFormats.DF_DAT);
		dataFormats.add(DataFormats.DF_CSV);
		dataFormats.add(DataFormats.DF_ARFF);
		logger.setLevel(Level.OFF); 
	}
	
	//getter
	public boolean dbg() {
		return debug;
	}
	
	public boolean cli() {
		return cli;
	}
	
	public boolean dev() {
		return development;
	}
	
	public Set<DataFormats> dataFormats() {
		return dataFormats;
	}
	public void printUsage() {
		//TODO
	}
	public void parseArguments(String[] args) throws IllegalArgumentException, SecurityException, IOException {
		clear();
		
		for(String arg : args) {
			if(arg.equalsIgnoreCase("--cli"))
				cli = true;
			else if(arg.equalsIgnoreCase("--debug")) {
				debug = true;
				logger.setLevel(Level.FINEST);
			}
			else if(arg.toLowerCase().startsWith("--logging")) {
				if(arg.equalsIgnoreCase("--logging=console"))
					logger.addHandler(new ConsoleHandler());
				else if(arg.equalsIgnoreCase("--logging=file"))
					logger.addHandler(new FileHandler());
				else if(arg.equalsIgnoreCase("--logging")) {
					logger.addHandler(new FileHandler());
					logger.addHandler(new ConsoleHandler());
				} else {
					throw new IllegalArgumentException("Wrong usage of '--logging'. '" + arg + "' is not a valid argument. Execution abborted.");
				}
				if(!logger.getLevel().equals(Level.FINEST))
					logger.setLevel(Level.INFO);
			}
			else if(arg.equalsIgnoreCase("--nodat"))
				dataFormats.remove(DataFormats.DF_DAT);
			else if(arg.equalsIgnoreCase("--nocsv"))
				dataFormats.remove(DataFormats.DF_CSV);
			else if(arg.equalsIgnoreCase("--noarff"))
				dataFormats.remove(DataFormats.DF_ARFF);
			else
				throw new IllegalArgumentException("'" + arg + "' is not a valid argument. Execution abborted.");
		}
	}
	
}