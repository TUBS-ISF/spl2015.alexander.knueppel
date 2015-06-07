//#ifdef Graphical
//#ifdef Histogram
//@package application.iohandler.gui;
//@
//@import java.awt.Color;
//@import java.awt.Dimension;
//@import java.awt.Graphics;
//@import java.text.NumberFormat;
//@import java.util.ArrayList;
//@import java.util.List;
//@
//@import javax.swing.JPanel;
//@
//@import application.iohandler.Controller;
//@import framework.classifier.DataPoint;
//@
//@public class PreprocessHistogram extends JPanel {
//@	private boolean real;
//@	private double Minimum = Double.MAX_VALUE;
//@	private double Maximum = 0.0;
//@	private int[] count;
//@	private List<String> categorieNames;
//@	
//@	public PreprocessHistogram(/*MainFrame f*/) {
//@		update(0);
//@	}
//@	
//@	private void clearArray() {
//@		for(int i=0;i<count.length;++i) {
//@			count[i] = 0;
//@		}
//@	}
//@	
//@	public void update(int n) { //attribute number
//@		if(Controller.currDataSet != null) {
//@			
//@			if(n < Controller.currDataSet.getNumNumericalAttrs()) { //real value
//@				Minimum = Double.MAX_VALUE;
//@				Maximum = 0.0;
//@				
//@				int scale = 7;
//@				count = new int[scale];
//@				clearArray();
//@				
//@				for(DataPoint d : Controller.currDataSet.getDataPoints()) {
//@					Minimum = Math.min(d.getNumericalValues().get(n), Minimum);
//@					Maximum = Math.max(d.getNumericalValues().get(n), Maximum);
//@				}
//@				
//@				double step = (Maximum-Minimum)/count.length;
//@				
//@				for(DataPoint d : Controller.currDataSet.getDataPoints()) {
//@					
//@					int index = (int)((d.getNumericalValues().get(n)-Minimum) / step);
//@					if(index>= count.length) //check borders
//@						index--;
//@					else if(index < 0)
//@						index = 0;
//@					
//@					count[index]++;
//@				}
//@				
//@				real = true;
//@				
//@				
//@						
//@			} else {
//@				n -= Controller.currDataSet.getNumNumericalAttrs();
//@				int nCats = Controller.currDataSet.getCategories()[n].getNumOfCategories();
//@
//@				categorieNames = new ArrayList<String>();
//@				count = new int[nCats];
//@				
//@				for(int i = 0; i < nCats; ++i) {
//@					String s = Controller.currDataSet.getCategories()[n].getOptionName(i);
//@					categorieNames.add(s);
//@				}
//@				
//@				for(DataPoint d : Controller.currDataSet.getDataPoints()) {
//@					if(d.containsCategoricalData()) {
//@						for(int cv :  d.getCategoricalValues()) {
//@							String s = Controller.currDataSet.getCategories()[n].getOptionName(cv);
//@							count[categorieNames.indexOf(s)]++;
//@						}
//@					}
//@				}
//@				
//@				real = false;
//@			}
//@
//@		}
//@		
//@		repaint();
//@	}
//@	
//@	protected void paintComponent(Graphics g) {
//@		if (count == null) return; // No display if count is null
//@		
//@        super.paintComponent(g);       
//@        int width = getWidth();
//@        int height = getHeight();
//@        int interval = (width - 40) / count.length;
//@        int individualWidth = (int)(((width - 40) / count.length) * 0.60);
//@        
//@        // Find the maximum count. The maximum count has the highest bar
//@        int maxCount = 0;
//@        for (int i = 0; i < count.length; i++) {
//@          if (maxCount < count[i])
//@            maxCount = count[i];
//@        }
//@        
//@     // x is the start position for the first bar in the histogram
//@        int x = 30;
//@
//@        // Draw a horizontal base line
//@        g.drawLine(10, height - 45, width - 10, height - 45);
//@        for (int i = 0; i < count.length; i++) {
//@          // Find the bar height
//@          int barHeight =
//@            (int)(((double)count[i] / (double)maxCount) * (height - 85));
//@
//@          // Display a bar (i.e. rectangle)
//@          g.setColor(Color.blue);
//@          g.fillRect(x, height - 45 - barHeight, individualWidth,
//@            barHeight);
//@
//@
//@          g.setColor(Color.red);
//@          // Display a letter under the base line
//@          if(real) {
//@        	  double val = (Minimum + i*((Maximum-Minimum)/count.length));
//@        	  NumberFormat nf = NumberFormat.getInstance(); // get instance
//@        	  nf.setMaximumFractionDigits(2); // set decimal places
//@        	  String s = nf.format(val);
//@        	  g.drawString(s, x, height - 30);
//@          } else {
//@        	  g.drawString(categorieNames.get(i), x, height - 30);
//@          }
//@          // Display count (turned into a string) over each bar
//@          g.drawString(""+count[i], x, height - barHeight - 55 );
//@
//@          // Move x for displaying the next character
//@          x += interval;
//@        }
//@	} 
//@	
//@	  /** Override getPreferredSize */
//@	  public Dimension getPreferredSize() {
//@	    return new Dimension(380, 150);
//@	  }
//@}
//#endif
//#endif
