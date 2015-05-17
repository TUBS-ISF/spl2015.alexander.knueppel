package framework.classifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class CategoricalResults implements Cloneable {
	/*
	 * Number of categories
	 */
	private int n;
	private double[] probabilities;
	
	public CategoricalResults(int numCategories) {
        n = numCategories;
        probabilities = new double[numCategories];
    }	
	
	public CategoricalResults(double[] probabilities) {
        this.probabilities = probabilities;
        n = probabilities.length;
    }
	
	public int size() {
        return probabilities.length;
    }
	
	public void setProb(int category, double probability) {
        if(category > probabilities.length)
            throw new IndexOutOfBoundsException("There are only " + probabilities.length + " posibilties! " + category + " is invalid");
        else if(probability < 0 || Double.isInfinite(probability) || Double.isNaN(probability))
            throw new ArithmeticException("Only zero and positive values are valid, not " + probability);
        probabilities[category] = probability;
    }
	
	public void incProb(int category, double probability) {
		if(category > probabilities.length)
            throw new IndexOutOfBoundsException("There are only " + probabilities.length + " posibilties. " + category + " is invalid");
        else if(probability < 0 || Double.isInfinite(probability) || Double.isNaN(probability))
            throw new ArithmeticException("Only zero and positive values are valid, not " + probability);
        probabilities[category] += probability;
    }
	
	public int mostLikely() {
		//TODO
		return 0;
    }
	
	public void divideScalar(double s) {
        for(int i = 0; i < probabilities.length; i++)
            probabilities[i]/=s;
    }
	
	public void normalize() {
		//TODO
	}
	
	public Vector<Double> getProbabilitiesAsVector(){
		Vector<Double> v = new Vector<Double>();
		for(double p : probabilities)
			v.add(new Double(p));
		return v;
	}
	
	public double getProbability(int category) {
        return probabilities[category];
    }
	
	@Override
    public CategoricalResults clone()  {
        CategoricalResults copy = new CategoricalResults(n);
        copy.probabilities = Arrays.copyOf(probabilities, probabilities.length);
        return copy;
    }
	
    @Override
    public String toString() {
        return Arrays.toString(probabilities);
    }
}
