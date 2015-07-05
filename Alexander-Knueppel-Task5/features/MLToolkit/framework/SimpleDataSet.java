package framework;

import java.util.ArrayList;
import java.util.List;

import framework.classifier.DataPoint;

public class SimpleDataSet extends DataSet {

	protected List<DataPoint> dataPoints;
    public SimpleDataSet(List<DataPoint> dataPoints) {
        if(dataPoints.isEmpty())
            throw new RuntimeException("Can not create empty data set");
        this.dataPoints = dataPoints;
        this.categories =  dataPoints.get(0).getCategoricalData();
        this.numberNumericalAttrs = dataPoints.get(0).numNumericalValues();
        this.numericalAttrsNames = new ArrayList<String>(this.numberNumericalAttrs);
        for(int i = 0; i < getNumNumericalAttrs(); i++)
            this.numericalAttrsNames.add("Numeric Input " + (i+1));
    }
    public SimpleDataSet(CategoricalData[] categories, int numNumericalAttrs) {
        this.categories = categories;
        this.numberNumericalAttrs = numNumericalAttrs;
        this.dataPoints = new ArrayList<DataPoint>();
    }
	@Override
	public DataPoint getDataPoint(int i) {
		// TODO Auto-generated method stub
		return dataPoints.get(i);
	}

	@Override
	public void setDataPoint(int i, DataPoint dp) {
		// TODO Auto-generated method stub
		dataPoints.set(i, dp);
	}

	@Override
	public int getSampleSize() {
		// TODO Auto-generated method stub
		return dataPoints.size();
	}
	
	public void add(DataPoint dp) {
        dataPoints.add(dp);
    }

}
