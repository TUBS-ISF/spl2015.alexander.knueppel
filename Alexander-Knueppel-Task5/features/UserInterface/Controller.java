package application.iohandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import application.Configuration;
import application.PluginManager;
import application.features.AbstractFileLoaderFeature;
import application.features.Feature;
//#ifdef arff
import application.features.fileloader.ArffFileLoaderFeature;
//#endif
import framework.DataSet;

public class Controller {
	
	public static DataSet currDataSet;
	public static boolean debug;
	public static PluginManager pluginmanager = new PluginManager();
	
	public static void loadFile(String filename) throws FeatureNotFoundException, IOException {		
		try {
//			if(filename.trim().endsWith(".dat")) {
//				//TODO dat
//				throw new FeatureNotFoundException("Dat-Loader");
//			} else if(filename.trim().endsWith(".csv")) {
//				//TODO csv
//				throw new FeatureNotFoundException("CSV-Loader");
//			} else if(filename.trim().endsWith(".arff")) {
//				//#ifdef arff
//
//				Controller.checkFeature(ArffFileLoaderFeature.class.getName(), "Arff-Loader");
//				ArffFileLoaderFeature arff = new ArffFileLoaderFeature();
//				currDataSet = arff.loadFile(new String[]{filename});
//				//#endif
//			} else {
//				throw new FeatureNotFoundException("Unknown fileformat");
//			}
			for(Feature f : Controller.pluginmanager.getPlugins(AbstractFileLoaderFeature.class)) {
				if(filename.trim().endsWith(((AbstractFileLoaderFeature)f).getFileExtension())) {
					currDataSet = ((AbstractFileLoaderFeature)f).loadFile(new String[]{filename});
					return;
				}
			}
			throw new FeatureNotFoundException("Can't load file. No algorithm found");
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
	
//	public static void checkFeature(String feature, String readableName) throws FeatureNotFoundException {
//		if(!Configuration.featureSet.contains(feature)) {
//			throw new FeatureNotFoundException(readableName);
//		}
//	}
}
