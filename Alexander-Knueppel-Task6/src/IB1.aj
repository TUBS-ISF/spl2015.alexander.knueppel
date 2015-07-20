import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import framework.classifier.Classifier;
import application.iohandler.Controller;
import application.iohandler.cli.CLIHandler;
import application.iohandler.gui.MainFrame;


public privileged aspect IB1 {
	// TODO Auto-generated aspect
	pointcut Classifier(CLIHandler c) : call(public void CLIHandler.addClassifierAlgorithms()) && this(c);
	after(CLIHandler c) : Classifier(c) {
		if(!c.classifierAlgorithms.contains("IB1"))
			c.classifierAlgorithms.add("IB1");
	}
	
	pointcut trigger(CLIHandler cli, String id, int index) 
	: execution(private void CLIHandler.triggerAlgorithm(String, int)) && args(id, index) && this(cli);
	after(CLIHandler cli, String id, int index) : trigger(cli, id, index) {
		if(id.equals("ib1")) {
			Classifier c = new framework.classifier.knn.IB1(Controller.currDataSet.getCategories()[index].clone(), index);
			System.out.println(framework.evaluation.Evaluation.evaluate(c, Controller.currDataSet, index));
			cli.algorithmTriggered = true;
		}
	}
	
	//gui
	pointcut ClassifierGUI(MainFrame mf) : call(public void MainFrame.addClassifierAlgorithms()) && this(mf);
	after(MainFrame mf) : ClassifierGUI(mf) {
		if(!mf.classifierAlgorithms.contains("IB1"))
			mf.classifierAlgorithms.add("IB1");
	}
	
	pointcut triggerGUI(MainFrame mf, String id, int index) 
	: execution(private void MainFrame.triggerAlgorithm(String, int)) && args(id, index) && this(mf);
	
	after(MainFrame mf, String id, int index) : triggerGUI(mf, id, index) {
		if(id.equals("ib1")) {
			Classifier c = new framework.classifier.knn.IB1(Controller.currDataSet.getCategories()[index].clone(), index);
        	mf.consoleOutput.setText(framework.evaluation.Evaluation.evaluate(c, Controller.currDataSet, index));
		}
	}
}