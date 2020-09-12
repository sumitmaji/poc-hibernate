package com.sum.server.entity;

import java.util.HashMap;

public interface EntityTab {
	public EntityTabEntry enter(Class clazz);
	public EntityTabEntry lookup(Class className);
	public HashMap<Class, EntityTabEntry> getMap();
}
