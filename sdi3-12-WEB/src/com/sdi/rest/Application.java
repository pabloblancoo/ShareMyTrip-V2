package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

import com.sdi.rest.impl.ServiceRestImpl;

public class Application extends javax.ws.rs.core.Application {
	@Override
	public Set<Class<?>> getClasses() {
		
		Set< Class<?> > res = new HashSet<>(); 
		res.add( ServiceRestImpl.class ); 
		
		return res;
	}
}