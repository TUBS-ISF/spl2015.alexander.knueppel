package application.iohandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import application.Configuration;
import application.features.fileloader.ArffFileLoaderFeature;
import framework.DataSet;

public class Controller {
	
	public static DataSet currDataSet;
	public static boolean debug;
	public static void loadFile(String filename) throws FeatureNotFoundException, IOException {		
		try {
			if(filename.trim().endsWith(".dat")) {
				//TODO dat
				throw new FeatureNotFoundException("Dat-Loader");
			} else if(filename.trim().endsWith(".csv")) {
				//TODO csv
				throw new FeatureNotFoundException("CSV-Loader");
			} else if(filename.trim().endsWith(".arff")) {
				Controller.checkFeature(ArffFileLoaderFeature.class.getName(), "Arff-Loader");
				ArffFileLoaderFeature arff = new ArffFileLoaderFeature();
				currDataSet = arff.loadFile(new String[]{filename});
			} else {
				throw new FeatureNotFoundException("Unknown fileformat");
			}
		} catch (FileNotFoundException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(debug)
				e.printStackTrace();
			else
				throw e;
		} catch (IOException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(debug)
				e.printStackTrace();
			else 
				throw e;
		} catch (FeatureNotFoundException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(debug)
				e.printStackTrace();	
			else
				throw e;
		}
	}
	
	public static void checkFeature(String feature, String readableName) throws FeatureNotFoundException {
		if(!Configuration.featureSet.contains(feature)) {
			throw new FeatureNotFoundException(readableName);
		}
	}
}
