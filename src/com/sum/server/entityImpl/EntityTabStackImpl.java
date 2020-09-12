package com.sum.server.entityImpl;

import java.util.HashMap;

import com.sum.server.entity.EntityTab;
import com.sum.server.entity.EntityTabFactory;
import com.sum.server.entity.EntityTabStack;

//moduleName vs EtityTabEntry
public class EntityTabStackImpl extends HashMap<String, EntityTab> implements
		EntityTabStack {

	private static final long serialVersionUID = -3347507042094095663L;

	public EntityTab enter(String moduleName) {
		if (get(moduleName) == null) {
			EntityTab tab = EntityTabFactory.createEntityTab(moduleName);
			put(moduleName, tab);
			return tab;
		}
		else
			return get(moduleName);
	}

	public EntityTab getModule(String moduleName) {
		return get(moduleName);
	}
}
