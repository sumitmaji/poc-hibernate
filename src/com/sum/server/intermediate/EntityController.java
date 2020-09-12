package com.sum.server.intermediate;

import java.util.Iterator;
import java.util.List;

import com.sum.server.entity.EntityTab;
import com.sum.server.entity.EntityTabFactory;
import com.sum.server.entity.EntityTabStack;

public class EntityController extends Executor{

	protected EntityController(String rootPackage) {
		super(rootPackage);
	}

	protected void execute() throws Exception {
		
		EntityTabStack stack = EntityTabFactory.getInstance();
		EntityTab entityTab = stack.enter(moduleName);
		List<Class<?>> classes = ClassFinder.find(rootPackage+".server.model");
		Iterator<Class<?>> it = classes.iterator();
		while(it.hasNext()){
			Class clazz = it.next();
			entityTab.enter(clazz);
		}
	}

}
