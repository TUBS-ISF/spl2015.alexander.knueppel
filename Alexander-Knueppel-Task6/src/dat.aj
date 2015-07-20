import application.iohandler.cli.CLIHandler;

public privileged aspect dat {
	// TODO Auto-generated aspect
	pointcut FileExtension(CLIHandler c) : call(public void CLIHandler.addFileFormats()) && this(c);
	after(CLIHandler c) : FileExtension(c) {
		if(!c.fileFormats.contains("dat"))
			c.fileFormats.add("dat");
	}
	
	pointcut LoadFile(String filename) : execution(private static void Controller.load(String)) && args(filename);
	after(String filename) : LoadFile(filename) {
		if(filename.trim().endsWith("dat")) {
			try {
				System.err.println("Unfortunately, CSV is not supported right now. Try using arff instead.");
			} catch(Exception e) {
				//...
			}
		}
	}
}