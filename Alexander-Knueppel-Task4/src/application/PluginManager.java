package application;

import java.util.ArrayList;
import java.util.HashMap;

import application.features.Feature;

public class PluginManager {
	private HashMap<Class<?>, ArrayList<Feature>> plugins = new HashMap<Class<?>,  ArrayList<Feature>>();
	
	// registers a set of plugins p of a specific plugin-type c
	public void register(final Class<?> c, final Feature f) {
		if (!plugins.containsKey(c))
			plugins.put(c, new ArrayList<Feature>());
		plugins.get(c).add(f);
	}
	
	public ArrayList<Feature> getPlugins(final Class<?> c) {
		return plugins.containsKey(c) ? plugins.get(c) : new ArrayList<Feature>();
	}
	
}
