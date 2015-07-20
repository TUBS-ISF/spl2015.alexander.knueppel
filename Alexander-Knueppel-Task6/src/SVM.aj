import application.iohandler.Controller;
import application.iohandler.gui.MainFrame;
import framework.classifier.Classifier;


public privileged aspect SVM {
	// TODO Auto-generated aspect
	pointcut Classifier(CLIHandler c) : call(public void CLIHandler.addClassifierAlgorithms()) && this(c);
	after(CLIHandler c) : Classifier(c) {
		if(!c.classifierAlgorithms.contains("SVM"))
			c.classifierAlgorithms.add("SVM");
	}
	
	pointcut trigger(CLIHandler cli, String id, int index) 
	: execution(private void CLIHandler.triggerAlgorithm(String, int)) && args(id, index) && this(cli);
	after(CLIHandler cli, String id, int index) : trigger(cli, id, index) {
		if(id.equals("svm")) {
			System.err.println("Sorry, but SVM is not supported right now. Please Choose something else.");
			cli.algorithmTriggered = true;
		}
	}
	
	//gui
	pointcut ClassifierGUI(MainFrame mf) : call(public void MainFrame.addClassifierAlgorithms()) && this(mf);
	after(MainFrame mf) : ClassifierGUI(mf) {
		if(!mf.classifierAlgorithms.contains("SVM"))
			mf.classifierAlgorithms.add("SVM");
	}
	
	pointcut triggerGUI(MainFrame mf, String id, int index) 
	: execution(private void MainFrame.triggerAlgorithm(String, int)) && args(id, index) && this(mf);
	after(MainFrame mf, String id, int index) : triggerGUI(mf, id, index) {
		if(id.equals("zeror")) {
        	mf.consoleOutput.setText("Sorry, but SVM is not supported right now. Please Choose something else.");
		}
	}
}