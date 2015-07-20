import application.App;
import application.iohandler.Handler;
import application.iohandler.cli.CLIHandler;
import application.iohandler.gui.GUIHandler;


public aspect Graphical {
	pointcut startup() : call(public void App.go());
	
	after(): startup() {
		Handler viewHandler;
		viewHandler = new GUIHandler(App.config);
		viewHandler.go();
	}
}