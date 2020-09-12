package ${package_name}.server.response;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="errors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Errors implements Serializable{

/**
*
*/
private static final long serialVersionUID = -2207121522380594482L;

@XmlElement
private Set<Field> field;

    public Errors() {
    super();
    }

    public Set<Field> getField() {
        return field;
        }

        public void setField(Set<Field> field) {
            this.field = field;
            }

            }
