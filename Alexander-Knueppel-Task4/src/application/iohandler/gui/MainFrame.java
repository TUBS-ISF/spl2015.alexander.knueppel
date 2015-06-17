package application.iohandler.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import framework.classifier.Classifier;
import framework.classifier.ZeroRClassifier;
import application.Configuration;
import application.features.classifier.AbstractClassifierFeature;
//import application.features.classifier.IB1ClassifierFeature;
import application.features.classifier.ZeroRClassifierFeature;
import application.iohandler.Controller;
import application.iohandler.FeatureNotFoundException;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1838370145680367098L;
	
	private JTabbedPane tabbedPane;
	private PreprocessData preDataPanel;
	private PreprocessAttributesList preAttributesPanel;
	private PreprocessSelectedAttribute preSelAttributePanel;
	private PreprocessHistogram preHistogram;
	
	private AbstractClassifierFeature currClassifier;
	
	private JComponent classifierPanel;
	private JTextArea consoleOutput;
	private JComboBox primeAttributeList;
	private JComboBox classifierList;
	public MainFrame() {
		currClassifier = new ZeroRClassifierFeature();
		
		createMenuBar();
		createTabs();
        setTitle("Machine Learning Toolbox - SPL Project");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createMenuBar() {
		JMenuBar menubar = new JMenuBar();
        //ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(eMenuItem);
        menubar.add(file);

        setJMenuBar(menubar);
	}
	
	private JComponent makeTextPanel(String text) { //filler for now
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

	private JComponent makePreprocessPanel() {
		JPanel panel = new JPanel(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		// file loading
		JPanel fileLoading = new JPanel();
		fileLoading.setBorder(BorderFactory.createTitledBorder("File"));
		JButton fileLoaderBtn = new JButton("Load file");
		fileLoaderBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(MainFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
						Controller.loadFile(file.getAbsolutePath());
					} catch (FeatureNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    tabbedPane.setEnabledAt(1, true);
                    
                    //update all
                    preDataPanel.update();
                    preAttributesPanel.update();
                    preSelAttributePanel.update(0);
                    preHistogram.update(0);            
                    
                    //add listeners
        			preAttributesPanel.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        	            public void valueChanged(ListSelectionEvent event) {
        	                int viewRow = preAttributesPanel.getTable().getSelectedRow();
        	                if (viewRow < 0) {
        	                	//??
        	                } else {
        	                	preSelAttributePanel.update(viewRow);              	
        	                    preHistogram.update(viewRow);
        	                }
        	            }
        	        });		
        			
        			//add tabs
        			classifierPanel.removeAll();
        			classifierPanel.add(makeClassifyPanel());
        			classifierPanel.revalidate();
        			classifierPanel.repaint();
                    
                    //This is where a real application would open the file.
                    //log.append("Opening: " + file.getName() + "." + newline);
                } else {
                    //log.append("Open command cancelled by user." + newline);
                }
            }
        });
		fileLoading.add(fileLoaderBtn);
		
		// middle area
		JPanel middleArea = new JPanel();
		middleArea.setLayout(new GridLayout(1,2));
		middleArea.setPreferredSize(new Dimension(800, 450));
		//// fill middle area (left)
		JPanel ma_data = new JPanel();
		ma_data.setBorder(BorderFactory.createTitledBorder("Data"));
		preDataPanel = new PreprocessData();
		ma_data.add(preDataPanel);
		
		JPanel ma_attributes = new JPanel();
		ma_attributes.setBorder(BorderFactory.createTitledBorder("Attributes"));
		preAttributesPanel = new PreprocessAttributesList();
		ma_attributes.add(preAttributesPanel);
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
		left.add(ma_data);
		left.add(ma_attributes);
		
		//// fill middle area (right)
		JPanel ma_selectedAttributes = new JPanel();
		ma_selectedAttributes.setBorder(BorderFactory.createTitledBorder("Selected Attributes"));
		preSelAttributePanel = new PreprocessSelectedAttribute();
		ma_selectedAttributes.add(preSelAttributePanel);

		preHistogram = new PreprocessHistogram();
		JPanel ma_visualize = new JPanel();
		ma_visualize.setBorder(BorderFactory.createTitledBorder("Histogram"));
		ma_visualize.add(preHistogram);
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		right.add(ma_selectedAttributes);

		right.add(ma_visualize);

		
		middleArea.add(left);
		middleArea.add(right);
		
		// Status
		JPanel status = new JPanel();
		status.setBorder(BorderFactory.createTitledBorder("Status"));
		status.add(new JLabel("Welcome to Machine Learning Toolbox"));
		
		// add panels
		panel.add(fileLoading);
		panel.add(middleArea);
		panel.add(status);
		return panel;
	}
	
	private JComponent makeClassifyPanel() {
		JPanel panel = new JPanel(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		// choose classifier
		JPanel classify = new JPanel();
		classify.setBorder(BorderFactory.createTitledBorder("Classifier"));
		
		
		List<String> classifiers = new ArrayList<String>();
		for(AbstractClassifierFeature f : AbstractClassifierFeature.classifiers) {
			if(Configuration.featureSet.contains(f.getClass().getName())) {
				classifiers.add(f.getClassifier(Controller.currDataSet, 0).getName());
			}
		}
		String[] classifiersStr = new String[classifiers.size()];
		for(int i = 0; i < classifiers.size(); ++i) {
			classifiersStr[i] = classifiers.get(i);
		}
		classifierList = new JComboBox(classifiersStr);
		//classifierName.setText(currClassifier.getClassifier(Controller.currDataSet, 0).getName());
		
		
		JLabel classifierLabel = new JLabel("Choose Classifier");
		classify.add(classifierLabel);	
		classify.add(classifierList);
		
		JPanel middleArea = new JPanel();
		middleArea.setLayout(new GridLayout(1,2));
		middleArea.setPreferredSize(new Dimension(800, 450));
		
		JPanel testOptions = new JPanel();
		testOptions.setBorder(BorderFactory.createTitledBorder("Test options"));
		JLabel testOptionsEmpty = new JLabel("Test options are empty for now");
		testOptions.add(testOptionsEmpty);
		
		JPanel classificationSettings = new JPanel();
		classificationSettings.setBorder(BorderFactory.createTitledBorder("Classify"));
		
		String[] nominalData = new String[Controller.currDataSet.getNumCategoricalAttrs()];
		for(int i = 0; i < Controller.currDataSet.getNumCategoricalAttrs(); ++i) {
			nominalData[i] = "(nom)" + Controller.currDataSet.getCategoryName(i);
		}
		primeAttributeList = new JComboBox(nominalData);
		classificationSettings.add(primeAttributeList);
		JButton startBtn = new JButton("Start");
		
		classificationSettings.add(startBtn);
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
		left.add(testOptions);
		left.add(classificationSettings);
		
		consoleOutput = new JTextArea("");
		consoleOutput.setEditable(false);
		//textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		consoleOutput.setLineWrap(true);
		consoleOutput.setWrapStyleWord(true);
		JScrollPane areaScrollPane = new JScrollPane(consoleOutput);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(400, 400));
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		right.setBorder(BorderFactory.createTitledBorder("Output"));
		
		
		right.add(areaScrollPane);
		
		middleArea.add(left);
		middleArea.add(right);
		
		panel.add(classify);
		panel.add(middleArea);
		
		startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	currClassifier = AbstractClassifierFeature.classifiers.get(classifierList.getSelectedIndex());
            	consoleOutput.setText(currClassifier.evaluate(Controller.currDataSet, primeAttributeList.getSelectedIndex()));
            }
        });
		
		
		return panel;
	}
	
	private void createTabs() {
		tabbedPane = new JTabbedPane();
		//ImageIcon icon = createImageIcon("images/middle.gif");

		JComponent panel1 = makePreprocessPanel();
		tabbedPane.addTab("Preprocess", null, panel1,"");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		classifierPanel = makeTextPanel("Panel #1");//makeClassifyPanel();
		tabbedPane.addTab("Classify", null, classifierPanel,"");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Cluster", null, panel3,"");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = makeTextPanel("Panel #4");
		tabbedPane.addTab("Visualize", null, panel4, "");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);		
		
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
		
		//Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
}


