
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import application.Configuration;
import framework.fileloader.CsvFileLoader;
import framework.DataSet;

public class Controller {
	
	public static void loadFile(String filename) {		
		original(filename);
		
		if(filename.trim().endsWith(".csv")) {
			try {
				currDataSet = (new CsvFileLoader(";")).loadFile(new File(filename));
			} catch(FileNotFoundException e) {
				Configuration.logger.log(Level.SEVERE, e.getMessage());
				if(debug)
					e.printStackTrace();
			}
		}
	}
}

