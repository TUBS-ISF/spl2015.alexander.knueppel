package framework;

import java.util.ArrayList;
import java.util.List;

import framework.classifier.DataPoint;

public abstract class DataSet {
	protected int numberNumericalAttrs;
	protected CategoricalData[] categories;
	protected List<String> numericalAttrsNames;
	
	public boolean setNumericName(String name, int i) {
        name = name.toLowerCase();
        
        if(numericalAttrsNames.contains(name))
            return false;
        else if(i < getNumNumericalAttrs() && i >= 0)
        	numericalAttrsNames.set(i, name);
        else
            return false;
        
        return true;
    }
	
	public String getNumericName(int i)  {
        if(i < getNumNumericalAttrs() && i >= 0)
            return numericalAttrsNames == null ? null : numericalAttrsNames.get(i);
        else
            throw new IndexOutOfBoundsException("Can not acces variable for invalid index  " + i );
    }
	
	public String getCategoryName(int i) {
        if(i < getNumCategoricalAttrs() && i >= 0)
            return categories[i].getCategoryName();
        else
            throw new IndexOutOfBoundsException("Can not acces variable for invalid index  " + i );
    }
	
	abstract public DataPoint getDataPoint(int i);
	abstract public void setDataPoint(int i, DataPoint dp);
	abstract public int getSampleSize();
	
	public int getNumCategoricalAttrs() {
        return categories.length;
    }
	 
	public int getNumNumericalAttrs() {
		return numberNumericalAttrs;
	}
	 
	public CategoricalData[] getCategories() {
        return categories;
    }
	 
	public List<DataPoint> getDataPoints() {
        List<DataPoint> list = new ArrayList<DataPoint>(getSampleSize());
        for(int i = 0; i < getSampleSize(); i++)
            list.add(getDataPoint(i));
        return list;
    }

}
