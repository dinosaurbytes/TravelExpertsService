package controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
 

public class TravelExpertsRestServiceApplication  extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	 
	public TravelExpertsRestServiceApplication() {
		singletons.add(new TravelExpertsRestService());
	}
 
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return null;
	}

}
