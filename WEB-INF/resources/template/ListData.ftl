package ${package_name}.server.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({Entity.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class ListData<T extends Object> implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 3997222912346693896L;
    @XmlElement
    private int count;
    @XmlAnyElement(lax=true)
    private List<T> t;
        public ListData() {
        super();
        }
        public int getCount() {
        return count;
        }
        public void setCount(int count) {
        this.count = count;
        }
        public List<T> getT() {
            return t;
            }
            public void setT(List<T> t) {
                this.t = t;
                }
                }
