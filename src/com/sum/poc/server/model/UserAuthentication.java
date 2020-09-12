package com.sum.poc.server.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.sum.poc.server.adapters.DateAdapter;// Generated Dec 4, 2016 8:08:44 AM by Hibernate Tools 3.2.4.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UserAuthentication generated by hbm2java
 */
@Entity
@Table(name="USER_AUTHENTICATION"
)
@XmlRootElement(name="userauthentication")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAuthentication extends com.sum.poc.server.model.POCEntity   implements java.io.Serializable {


     private BigDecimal userId;
     private String password;
     private Boolean enabled;
     private String username;
@XmlTransient
     private Set<UserAuthorization> userAuthorizations = new HashSet<UserAuthorization>(0);

    public UserAuthentication() {
    }

	
    public UserAuthentication(BigDecimal userId) {
        this.userId = userId;
    }
    public UserAuthentication(BigDecimal userId, String password, Boolean enabled, String username, Set<UserAuthorization> userAuthorizations) {
       this.userId = userId;
       this.password = password;
       this.enabled = enabled;
       this.username = username;
       this.userAuthorizations = userAuthorizations;
    }
   
     @Id 

    
    @Column(name="USER_ID", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getUserId() {
        return this.userId;
    }
    
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    
    @Column(name="PASSWORD", length=100)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="ENABLED", precision=1, scale=0)
    public Boolean getEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    
    @Column(name="USERNAME", length=100)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userAuthentication")
    public Set<UserAuthorization> getUserAuthorizations() {
        return this.userAuthorizations;
    }
    
    public void setUserAuthorizations(Set<UserAuthorization> userAuthorizations) {
        this.userAuthorizations = userAuthorizations;
    }




}

