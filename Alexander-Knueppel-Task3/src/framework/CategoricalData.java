package framework;

import java.util.ArrayList;
import java.util.List;

public class CategoricalData implements Cloneable {
	private List<String> catNames;
    private String categoryName;
    
    private int n;
    
    public CategoricalData(int n) {
        this.n = n;
        catNames = new ArrayList<String>(n);
        for(int i = 0; i < n; i++)
            catNames.add("Option " + (i+1));
        categoryName = "No Name";
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public int getNumOfCategories() {
        return n;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public boolean setOptionName(String name, int i) {
        name = name.toLowerCase();
        if(i < 0 || i >= n)
            return false;
        else if(catNames.contains(name))
            return false;
        catNames.set(i, name);
        
        return true;
    }
    public boolean isValidCategory(int i) {
        if (i < 0 || i >= n)
            return false;
        
        return true;
    }
    
    public String getOptionName(int i)  {
        if(catNames != null)
            return catNames.get(i);
        else
            return Integer.toString(i);
    }
    
    public CategoricalData clone() {
        CategoricalData copy = new CategoricalData(n);
        
        if(this.catNames != null)
            copy.catNames = new ArrayList<String>(this.catNames);
        
        return copy;
    }
    
    public static CategoricalData[] copyOf(CategoricalData[] orig) {
        CategoricalData[] copy = new CategoricalData[orig.length];
        for(int i = 0; i < copy.length; i++)
            copy[i] = orig[i].clone();
        return copy;
    }

}
