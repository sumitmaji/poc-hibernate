package com.sum.poc.server.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message implements Serializable{

/**
*
*/
private static final long serialVersionUID = 1941094133541235881L;
@XmlElement
private String text;
public Message() {
super();
}
public String getText() {
return text;
}
public void setText(String text) {
this.text = text;
}
}
