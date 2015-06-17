package framework.classifier;

import java.util.Arrays;
import java.util.Vector;

import framework.CategoricalData;

public class DataPoint implements Cloneable {	
	private double weight;
    protected Vector<Double> numericalVals;
    protected int[] categoricalVals;
    protected CategoricalData[] categoricalData;
    private static final int[] emptyInt = new int[0];
    private static final CategoricalData[] emptyData = new CategoricalData[0];
	
    public DataPoint(Vector<Double> numericalVals, int[] categoricalVals, CategoricalData[] categoricalData) {
        this(numericalVals, categoricalVals, categoricalData, 1);
    }
    public DataPoint(Vector<Double> numericalVals, int[] categoricalVals, CategoricalData[] categoricalData, double weight) {
        this.numericalVals = numericalVals;
        this.categoricalVals = categoricalVals;
        this.categoricalData = categoricalData;
        this.weight = weight;
    }	
    public DataPoint(Vector<Double> numericalVals, double weight) {
        this(numericalVals, emptyInt, emptyData, weight);
    }	
    public DataPoint(Vector<Double> numericalVals) {
        this(numericalVals, emptyInt, emptyData);
    }
    public double getWeight()  {
        return weight;
    }
    public void setWeight(double weight) {
        if(Double.isNaN(weight) || Double.isInfinite(weight) || weight <= 0)
            throw new ArithmeticException("Invalid weight assignment of  " + weight);
        this.weight = weight;
    }
    public boolean containsCategoricalData() {
        return categoricalVals.length > 0;
    }
    public Vector<Double> getNumericalValues() {
        return numericalVals;
    }
    public boolean containsNumericalData()  {
        return numericalVals != null && numericalVals.size() > 0;
    }
    public int numNumericalValues()  {
        return numericalVals == null ? 0 : numericalVals.size();
    }
    public int[] getCategoricalValues() {
        return categoricalVals;
    }
    public int numCategoricalValues() {
        return categoricalVals.length;
    }
    public int getCategoricalValue(int i) {
        return categoricalVals[i];
    }
    public CategoricalData[] getCategoricalData() {
        return categoricalData;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(containsNumericalData()) {
            sb.append("Numerical: ");
            sb.append(numericalVals.toString());
        }
        
        if(containsCategoricalData()) {
            sb.append(" Categorical: ");
            for(int i  = 0; i < categoricalVals.length; i++) {
                sb.append(categoricalData[i].getOptionName(categoricalVals[i]));
                sb.append(",");
            }
        }
            
        return sb.toString();
    }
    
    public DataPoint clone() {
        return new DataPoint(numericalVals, 
                Arrays.copyOf(categoricalVals, categoricalVals.length),
                CategoricalData.copyOf(categoricalData),
                weight);
    }
}
