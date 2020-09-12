package com.sum.server.entityImpl;

import java.util.HashMap;

import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;

public class HibernateEntityTab extends HashMap<String, HibernateEntityMetaData>{

	private static final long serialVersionUID = -1926997281165611758L;
	
	public void enter(ClassMetadata classMetadata){
		AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) classMetadata;
		EntityMetamodel model = abstractEntityPersister.getEntityMetamodel();
		String entityName = model.getName().substring(model.getName().lastIndexOf(".")+1);
		if(get(entityName) == null){
			HibernateEntityMetaData metaData = new HibernateEntityMetaData(model);
			put(entityName, metaData);
		}
	}
	
	public HibernateEntityMetaData getEntityMetaData(String entityName){
		return get(entityName);
	}
	
	public HashMap<String, HibernateEntityMetaData> getMap(){
		return this;
	}
}
