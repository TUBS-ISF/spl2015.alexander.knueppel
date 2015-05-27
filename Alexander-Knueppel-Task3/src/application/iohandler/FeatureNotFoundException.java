package application.iohandler;

public class FeatureNotFoundException extends Exception {
	private static final long serialVersionUID = 3445877739192591345L;

	FeatureNotFoundException(String str) {
        super("Sorry, but feature is not available: " + str);
    }
}
