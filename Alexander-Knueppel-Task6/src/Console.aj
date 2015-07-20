import application.App;
import application.iohandler.Handler;
import application.iohandler.cli.CLIHandler;

public aspect Console {
	pointcut startup() : call(public void App.go());
	
	after(): startup() {
		Handler viewHandler;
		viewHandler = new CLIHandler(App.config);
		viewHandler.go();
	}
	
}