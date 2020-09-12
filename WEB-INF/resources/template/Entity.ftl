package ${package_name};

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import ${package_name}.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
${entityList}
})
public abstract class ${module}Entity {

	public ${module}Entity(){
		super();
	}
}
