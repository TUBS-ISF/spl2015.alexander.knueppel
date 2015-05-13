package application;

import java.util.HashSet;
import java.util.Set;

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
	//enable logging
	private boolean logging;
	//use command line interface instead of gui
	private boolean cli;
	//development or production
	private boolean development;
	//list of allowed data formats
	private Set<DataFormats> dataFormats; 
	
	public Configuration(boolean development) {
		this.development = development;
		//defaults
		logging = false;
		cli = false;
		dataFormats = new HashSet<DataFormats>();
		dataFormats.add(DataFormats.DF_DAT);
		dataFormats.add(DataFormats.DF_CSV);
		dataFormats.add(DataFormats.DF_ARFF);
	}
	
	//getter
	public boolean logging() {
		return logging;
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
	
	public void parseArguments(String[] args) throws IllegalArgumentException {
		for(String arg : args) {
			if(arg.equalsIgnoreCase("--cli"))
				cli = true;
			else if(arg.equalsIgnoreCase("--logging"))
				logging = true;
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
