//#ifdef arff

package application.features.fileloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import framework.DataSet;
import framework.fileloader.ArffFileLoader;

public class ArffFileLoaderFeature extends AbstractFileLoaderFeature {

	public ArffFileLoaderFeature() {}

	@Override
	public DataSet loadFile(String[] args) throws FileNotFoundException, IOException {
		//we only need arg0
		String filename = args[0];
		return new ArffFileLoader().loadFile(new File(filename));
	}

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "arff-fileloader-feature";
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Implemented feature for loading *.arff-datafiles";
	}
}
//#endif
