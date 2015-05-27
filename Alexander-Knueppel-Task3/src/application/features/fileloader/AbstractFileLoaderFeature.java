package application.features.fileloader;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.features.Feature;
import application.features.Feature.FeatureType;
import framework.DataSet;

public abstract class AbstractFileLoaderFeature implements Feature {

	public AbstractFileLoaderFeature() {}

	public abstract DataSet loadFile(String[] args) throws FileNotFoundException, IOException;
	
	public FeatureType getFeatureType() {
		// TODO Auto-generated method stub
		return Feature.FeatureType.FT_FILE_LOADING;
	}
}
