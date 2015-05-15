package application.iohandler;

import application.Configuration;

public abstract class Handler {
	protected Configuration config;
	
	public Handler(Configuration config) {
		//only primitves and collections; shallow copy should be enough
		this.config = config;
	}
	public abstract void run();
	public abstract boolean initialize();
	public abstract void shutdown();
}
