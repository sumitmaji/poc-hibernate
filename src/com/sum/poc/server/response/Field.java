package com.sum.poc.server.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Field implements Serializable{

/**
*
*/
private static final long serialVersionUID = -5447795408258590649L;

@XmlElement
private String id;
@XmlElement
private String msg;
public Field() {
super();
}
public String getId() {
return id;
}
public void setId(String id) {
this.id = id;
}
public String getMsg() {
return msg;
}
public void setMsg(String msg) {
this.msg = msg;
}
}
