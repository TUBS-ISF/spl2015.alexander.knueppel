package framework.linear.metrics;

import java.util.Vector;

public interface DistanceMetric {
	public double distance(Vector<Double> a, Vector<Double> b);
	//public boolean isSymmetric();
}
