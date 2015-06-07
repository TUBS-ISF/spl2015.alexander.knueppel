package application.iohandler.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import application.features.classifier.IB1ClassifierFeature;
import application.iohandler.Controller;
import application.iohandler.FeatureNotFoundException;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1838370145680367098L;
	
	private PreprocessData preDataPanel;
	private PreprocessAttributesList preAttributesPanel;
	private PreprocessSelectedAttribute preSelAttributePanel;
	private PreprocessHistogram preHistogram;
	
	public MainFrame() {
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
                    
                    //update
                    preDataPanel.update();
                    preAttributesPanel.update();
                    preSelAttributePanel.update(0);
                    preHistogram.update(0);
                    
                    //test
                    IB1ClassifierFeature f = new IB1ClassifierFeature();
                   
                    System.out.println( f.evaluate(Controller.currDataSet, 0));
                    
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
		// TODO
		return makeTextPanel("Classify");
	}
	
	private void createTabs() {
		JTabbedPane tabbedPane = new JTabbedPane();
		//ImageIcon icon = createImageIcon("images/middle.gif");

		JComponent panel1 = makePreprocessPanel();
		tabbedPane.addTab("Preprocess", null, panel1,"");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeClassifyPanel();
		tabbedPane.addTab("Classify", null, panel2,"");
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
