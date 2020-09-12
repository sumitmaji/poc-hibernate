package com.sum.server.entityImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HibernateEntityAttributeMetaData {
	@XmlAttribute
	private String attributeName;
	@XmlAttribute
	private String attributeDisplayName;
	@XmlAttribute
	private String attributeType;
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeDisplayName() {
		return attributeDisplayName;
	}

	public void setAttributeDisplayName(String attributeDisplayName) {
		this.attributeDisplayName = attributeDisplayName;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public HibernateEntityAttributeMetaData(){
		super();
	}
	
	public HibernateEntityAttributeMetaData(String entityName, String entityType){
		this.attributeName = entityName;
		this.attributeType = entityType;
		this.attributeDisplayName = entityName.toUpperCase();
	}

}
