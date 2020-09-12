package com.sum.server.entity;

import com.sum.server.entityImpl.EntityTabEntryImpl;
import com.sum.server.entityImpl.EntityTabImpl;
import com.sum.server.entityImpl.EntityTabStackImpl;
import com.sum.server.entityImpl.HibernateEntityTab;

public class EntityTabFactory {


	public EntityTabStack createEntityTabStack(){
		return new EntityTabStackImpl();
	}
	
	public static EntityTab createEntityTab(String moduleName){
		return new EntityTabImpl(moduleName);
	}
	
	public static EntityTabEntry createEntityTabEntry(Class clazz, EntityTab tab){
		return new EntityTabEntryImpl(clazz, tab);
	}
	
	public static HibernateEntityTab createHibernateEntityTab(){
		return new HibernateEntityTab();
	}
	
	public static class HibernateEntityTabHolder{
		public static HibernateEntityTab instance = new HibernateEntityTab();
	}
	
	public static HibernateEntityTab getHibernateEntityTabInstance(){
		return HibernateEntityTabHolder.instance;
	}
	
	public static class EntityTabStackHolder{
		public static EntityTabStack instance = new EntityTabStackImpl(); 
	}
	
	public static EntityTabStack getInstance(){
		return EntityTabStackHolder.instance;
	}
}
