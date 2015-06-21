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

public class CsvFileLoader implements FileLoader {
	private String delim;
	
	public CsvFileLoader(String d) {
		delim = d;
	}

	public DataSet loadFile(File file) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return loadFile(new FileReader(file));
	}

	public DataSet loadFile(Reader input) {
//		ArrayList<DataPoint> list = new ArrayList<DataPoint>();
//		
//		BufferedReader br = new BufferedReader(input);
//		int numOfVars = 0;
//        int numReal = 0;
//        List<Boolean> isReal = new ArrayList<Boolean>();
//        List<String> variableNames = new ArrayList<String>();
//        List<HashMap<String, Integer>> catVals = new  ArrayList<HashMap<String, Integer>>();
//        String line = null;
//        CategoricalData[] categoricalData = null;
//        
//        //first line
//        line = br.readLine();
//        
//        //others
//        while ((line = br.readLine()) != null) {
//
//        }
        
		return null;
	}

}
