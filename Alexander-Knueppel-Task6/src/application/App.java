package application;

import java.io.IOException;


public class App {
	public static Configuration config;
	
	public static void go() {}

	public static void main(String[] args) {
		App.config = new Configuration(true, 0, 1, 0); // development = true
		try {
			config.parseArguments(args);
			System.out.println("start");
			App.go();
			System.out.println("end");
		} catch(IllegalArgumentException e) {
			if(config.dbg())
				e.printStackTrace();
		} catch(IOException e) {
			if(config.dbg())
				e.printStackTrace();
		} catch(Exception e) {
			if(config.dbg())
				e.printStackTrace();
		}
	}

}
