package com.sum.poc.server.response;
import com.sum.poc.server.model.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
POCEntity.class
})
public abstract class Entity {

public Entity(){
super();
}
}
