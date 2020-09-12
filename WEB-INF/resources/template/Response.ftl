package ${package_name}.server.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.sum.server.entityImpl.HibernateEntityMetaData;


@XmlRootElement
@XmlSeeAlso({Message.class,Errors.class,Entity.class,ListData.class,Entity.class,HibernateEntityMetaData.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Response<T extends Object> implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = -2584485191028298543L;
    @XmlAttribute
    protected boolean success;
    @XmlElement
    protected Message message;
    @XmlAnyElement(lax=true)
    private T t;

    public Response() {
    super();
    }
    public boolean isSuccess() {
    return success;
    }
    public void setSuccess(boolean success) {
    this.success = success;
    }
    public Message getMessage() {
    return message;
    }
    public void setMessage(Message message) {
    this.message = message;
    }
    public T getT() {
    return t;
    }
    public void setT(T t) {
    this.t = t;
    }
    }
