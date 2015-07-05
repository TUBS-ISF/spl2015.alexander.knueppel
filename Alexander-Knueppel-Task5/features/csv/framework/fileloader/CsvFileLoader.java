package framework.fileloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import framework.CategoricalData;
import framework.DataSet;
import framework.classifier.DataPoint;

public class CsvFileLoader {
	private String delim;
	
	public CsvFileLoader(String d) {
		delim = d;
	}

	public DataSet loadFile(File file) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return loadFile(new FileReader(file));
	}

	public DataSet loadFile(Reader input) {
        
		return null;
	}

}
