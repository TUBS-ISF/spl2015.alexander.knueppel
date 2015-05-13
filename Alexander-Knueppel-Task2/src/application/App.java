package application;

public class App {
	
	public static void main(String[] args) {
		Configuration config = new Configuration(true);
		try {
			config.parseArguments(args);
			if(config.cli()) {
				// add cli handler
			} else {
				// add gui handler
			}
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

}
