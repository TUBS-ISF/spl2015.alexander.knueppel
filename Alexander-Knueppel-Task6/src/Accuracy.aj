import framework.CategoricalData;
import framework.evaluation.SimpleEvaluation;
import framework.scores.Score;
import application.iohandler.cli.CLIHandler;


public aspect Accuracy {
	pointcut addScore(CategoricalData cd, SimpleEvaluation ev) 
	: call(private static void addScores(CategoricalData, SimpleEvaluation)) && args(cd, ev);
	after(CategoricalData cd, SimpleEvaluation ev) : addScore(cd, ev) {
		Score s = new framework.scores.Accuracy();
		s.prepare(cd);
		ev.addScore(s);
	}
}