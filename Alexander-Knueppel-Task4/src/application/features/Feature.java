package application.features;

public interface Feature {
	public enum FeatureType {
		FT_FILE_LOADING,
		FT_CLASSIFIER,
		FT_SCORE
	}
	
	public String getIdentifier();
	public String getDescription();
	public FeatureType getFeatureType();
}
