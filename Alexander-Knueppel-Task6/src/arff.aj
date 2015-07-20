import java.io.File;
import java.util.ArrayList;

import framework.fileloader.ArffFileLoader;
import application.iohandler.Controller;
import application.iohandler.cli.CLIHandler;;


public privileged aspect arff {
	// TODO Auto-generated aspect
	pointcut FileExtension(CLIHandler c) : call(public void CLIHandler.addFileFormats()) && this(c);
	after(CLIHandler c) : FileExtension(c) {
		if(!c.fileFormats.contains("arff"))
			c.fileFormats.add("arff");
	}
	
	pointcut LoadFile(String filename) : execution(private static void Controller.load(String)) && args(filename);
	after(String filename) : LoadFile(filename) {
		if(filename.trim().endsWith("arff")) {
			try {
				Controller.currDataSet = (new ArffFileLoader()).loadFile(new File(filename)); 
			} catch(Exception e) {
				//...
			}
		}
	}
}