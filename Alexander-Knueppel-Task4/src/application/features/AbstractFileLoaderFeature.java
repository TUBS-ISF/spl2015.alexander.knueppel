package application.features;

import java.io.FileNotFoundException;
import java.io.IOException;

import framework.DataSet;

public abstract class AbstractFileLoaderFeature implements Feature {

	public AbstractFileLoaderFeature() {}

	public abstract DataSet loadFile(String[] args) throws FileNotFoundException, IOException;
	
	public FeatureType getFeatureType() {
		// TODO Auto-generated method stub
		return Feature.FeatureType.FT_FILE_LOADING;
	}
	
	public abstract String getFileExtension();
}
