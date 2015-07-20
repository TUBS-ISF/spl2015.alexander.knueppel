package application.iohandler;

import java.io.IOException;
import framework.DataSet;

public class Controller {
	
	public static DataSet currDataSet;
	public static boolean debug;
	
	private static void load(String filename) {
		
	}
	public static void loadFile(String filename) throws FeatureNotFoundException, IOException {		
		currDataSet = null;
		try {
			load(filename);
			if(currDataSet == null)
				throw new FeatureNotFoundException("Can't load file. No algorithm found");
		} catch (FeatureNotFoundException e) {
			if(debug)
				e.printStackTrace();	
			else
				throw e;
		}
	}
}
