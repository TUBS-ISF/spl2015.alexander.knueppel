package application.features;

public interface Feature {
	public enum FeatureType {
		FT_FILE_LOADING,
		FT_CLASSIFIER
	}
	
	public String getIdentifier();
	public String getDescription();
	public FeatureType getFeatureType();
}
