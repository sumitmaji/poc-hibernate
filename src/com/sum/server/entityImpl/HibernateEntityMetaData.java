package com.sum.server.entityImpl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.tuple.StandardProperty;
import org.hibernate.tuple.entity.EntityMetamodel;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HibernateEntityMetaData {
	
	@XmlAttribute
	private String entityName;
	@XmlAttribute
	private String packageName;
	private Set<HibernateEntityAttributeMetaData> attribute = new HashSet<HibernateEntityAttributeMetaData>();
	
	public HibernateEntityMetaData(){
		super();
	}
	
	public HibernateEntityMetaData(EntityMetamodel model){
		entityName = model.getName().substring(model.getName().lastIndexOf(".")+1);
		packageName = model.getName().substring(0,model.getName().lastIndexOf("."));
		
		StandardProperty[] attributes = model.getProperties();
		for(StandardProperty attributetemp: attributes){
			attribute.add(new HibernateEntityAttributeMetaData(attributetemp.getName(),attributetemp.getType().getName()));
		}
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Set<HibernateEntityAttributeMetaData> getAttribute() {
		return attribute;
	}

	public void setAttribute(Set<HibernateEntityAttributeMetaData> attribute) {
		this.attribute = attribute;
	}
}
