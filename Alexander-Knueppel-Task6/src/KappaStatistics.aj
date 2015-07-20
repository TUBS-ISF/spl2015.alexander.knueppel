import framework.CategoricalData;
import framework.evaluation.SimpleEvaluation;
import framework.scores.Score;


public aspect KappaStatistics {
	pointcut addScore(CategoricalData cd, SimpleEvaluation ev) 
	: call(private static void addScores(CategoricalData, SimpleEvaluation)) && args(cd, ev);
	after(CategoricalData cd, SimpleEvaluation ev) : addScore(cd, ev) {
		Score s = new framework.scores.Kappa();
		s.prepare(cd);
		ev.addScore(s);
	}
}