//#ifdef Graphical

package application.iohandler.gui;

import application.Configuration;
import application.iohandler.Handler;



public class GUIHandler extends Handler {
	public GUIHandler(Configuration config) {
		super(config);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		MainFrame gui = new MainFrame();
		gui.setVisible(true);
	}

	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}
}
//#endif
