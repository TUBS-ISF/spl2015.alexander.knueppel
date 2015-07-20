import application.iohandler.Controller;
import application.iohandler.cli.CLIHandler;
import application.iohandler.gui.MainFrame;
import framework.classifier.Classifier;


public privileged aspect ZeroR {
	// TODO Auto-generated aspect
	pointcut Classifier(CLIHandler c) : call(public void CLIHandler.addClassifierAlgorithms()) && this(c);
	after(CLIHandler c) : Classifier(c) {
		if(!c.classifierAlgorithms.contains("ZeroR"))
			c.classifierAlgorithms.add("ZeroR");
	}
	
	pointcut trigger(CLIHandler cli, String id, int index) 
	: execution(private void CLIHandler.triggerAlgorithm(String, int)) && args(id, index) && this(cli);
	after(CLIHandler cli, String id, int index) : trigger(cli, id, index) {
		if(id.equals("zeror")) {
			Classifier c = new framework.classifier.ZeroRClassifier(index);
			System.out.println(framework.evaluation.Evaluation.evaluate(c, Controller.currDataSet, index));
			cli.algorithmTriggered = true;
		}
	}
	
	//gui
	pointcut ClassifierGUI(MainFrame mf) : call(public void MainFrame.addClassifierAlgorithms()) && this(mf);
	after(MainFrame mf) : ClassifierGUI(mf) {
		if(!mf.classifierAlgorithms.contains("ZeroR"))
			mf.classifierAlgorithms.add("ZeroR");
	}
	
	pointcut triggerGUI(MainFrame mf, String id, int index) 
	: execution(private void MainFrame.triggerAlgorithm(String, int)) && args(id, index) && this(mf);
	
	after(MainFrame mf, String id, int index) : triggerGUI(mf, id, index) {
		if(id.equals("zeror")) {
			Classifier c = new framework.classifier.ZeroRClassifier(index);
        	mf.consoleOutput.setText(framework.evaluation.Evaluation.evaluate(c, Controller.currDataSet, index));
		}
	}
}