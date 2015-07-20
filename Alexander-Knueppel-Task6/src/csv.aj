import java.io.File;

import application.iohandler.Controller;
import application.iohandler.cli.CLIHandler;
import framework.fileloader.ArffFileLoader;


public privileged aspect csv {
	// TODO Auto-generated aspect
	pointcut FileExtension(CLIHandler c) : call(public void CLIHandler.addFileFormats()) && this(c);
	after(CLIHandler c) : FileExtension(c) {
		if(!c.fileFormats.contains("csv"))
			c.fileFormats.add("csv");
	}
	
	pointcut LoadFile(String filename) : execution(private static void Controller.load(String)) && args(filename);
	after(String filename) : LoadFile(filename) {
		if(filename.trim().endsWith("csv")) {
			try {
				System.err.println("Unfortunately, CSV is not supported right now. Try using arff instead.");
			} catch(Exception e) {
				//...
			}
		}
	}
}