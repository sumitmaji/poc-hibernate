package com.sum.server.entityImpl;

import java.util.HashMap;

import com.sum.server.entity.EntityTab;
import com.sum.server.entity.EntityTabEntry;
import com.sum.server.entity.EntityTabFactory;

//Entity(class) vs EntityTabEntry
public class EntityTabImpl extends HashMap<Class, EntityTabEntry> implements EntityTab{

	private static final long serialVersionUID = -7302095446006349727L;
	private String moduleName;
	
	public EntityTabImpl(String moduleName){
		this.moduleName = moduleName;
	}

	public EntityTabEntry enter(Class clazz){
		EntityTabEntry entry = EntityTabFactory.createEntityTabEntry(clazz, this);
		put(clazz, entry);
		return entry;
	}
	
	public EntityTabEntry lookup(Class className){
		return get(className);
	}

	@Override
	public HashMap<Class, EntityTabEntry> getMap() {
		return this;
	}
}
