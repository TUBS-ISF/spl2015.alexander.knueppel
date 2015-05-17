package application.iohandler.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1838370145680367098L;

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
		// TODO
		JPanel fileLoading = new JPanel();
		fileLoading.setBorder(BorderFactory.createTitledBorder(
                "File"));
		//layeredPane.setPreferredSize(panel.getSize());
		fileLoading.add(new JButton("Load file"));
		
		panel.setLayout(new GridLayout(2, 2));
		panel.add(fileLoading);
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
