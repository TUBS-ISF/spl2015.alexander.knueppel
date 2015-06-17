package framework.linear.metrics;

import java.util.Vector;

public class EuclidianDistance implements DistanceMetric {

	public double distance(Vector<Double> a, Vector<Double> b) {
		// TODO Auto-generated method stub
		if(a.size() != b.size())
			throw new RuntimeException("...");
		
		double result = 0.0;
		for(int i=0; i<a.size(); ++i) {
			result += (a.get(i) - b.get(i))*(a.get(i) - b.get(i));
		}
		return Math.sqrt(result);
	}

}
