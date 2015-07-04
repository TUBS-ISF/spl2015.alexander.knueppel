package application;
import java.io.IOException;
import java.util.logging.Level;



public class App {
	
	public static void go(Configuration c) {
		//to be implemented by features
	}
	
	public static void main(String[] args) {
		Configuration config = new Configuration(true, 0, 1, 0); // development = true
		try {
			config.parseArguments(args);
			Configuration.logger.log(Level.INFO, "Arguments parsed successfully!");
			
			//Controller.debug = config.dbg(); 
			
			App.go(config);
			
		} catch(IllegalArgumentException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(config.dbg())
				e.printStackTrace();
		} catch(IOException e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(config.dbg())
				e.printStackTrace();
		} catch(Exception e) {
			Configuration.logger.log(Level.SEVERE, e.getMessage());
			if(config.dbg())
				e.printStackTrace();
		}
	}

}
