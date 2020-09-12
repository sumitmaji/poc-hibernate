package com.sum.server.entityImpl;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.commons.lang.WordUtils;

import com.sum.server.entity.EntityTab;
import com.sum.server.entity.EntityTabEntry;
import com.sum.server.entity.EntityTabEntryKey;

//Entity Attribute vs it value
public class EntityTabEntryImpl extends HashMap<String, HashMap<EntityTabEntryKey, Object>>
		implements EntityTabEntry {

	private static final long serialVersionUID = -6766314084596886034L;
	private Class clazz;
	private EntityTab entityTab;

	public EntityTabEntryImpl(Class clazz, EntityTab entityTab) {
		this.clazz = clazz;
		this.entityTab = entityTab;
		init();
	}

	private void init() {
		Field[] fields = clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			Field field = fields[i];
			HashMap<EntityTabEntryKey, Object> map;
			
			if(get(field.getName()) == null){
				map = new HashMap<EntityTabEntryKey, Object>();
				put(field.getName(),map);
			}
			else{
				map = get(field.toString().toLowerCase());
			}
			
			map.put(EntityTabEntryKeyImpl.DATA_TYPE, field.getType());
			if(field.getType() != Boolean.class )
				map.put(EntityTabEntryKeyImpl.GETMETHOD, "get"+WordUtils.capitalize(field.getName()));
			else
				map.put(EntityTabEntryKeyImpl.GETMETHOD, "is"+WordUtils.capitalize(field.getName()));
			
			map.put(EntityTabEntryKeyImpl.SETMETHOD, "set"+WordUtils.capitalize(field.getName()));
		}
	}

	public EntityTab getEntityTab() {
		return entityTab;
	}

	public Class getEntityName() {
		return clazz;
	}
}
