package application.features.fileloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import framework.DataSet;
import framework.fileloader.ArffFileLoader;
import framework.fileloader.CsvFileLoader;
import application.features.AbstractFileLoaderFeature;

public class CsvFileLoaderFeature extends AbstractFileLoaderFeature {

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "csv-fileloader-feature";
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Implemented feature for loading *.csv-datafiles";
	}

	@Override
	public DataSet loadFile(String[] args) throws FileNotFoundException,
			IOException {
		String filename = args[0];
		//use ; as delimiter
		return new CsvFileLoader(";").loadFile(new File(filename));
	}

	@Override
	public String getFileExtension() {
		// TODO Auto-generated method stub
		return "csv";
	}

}
