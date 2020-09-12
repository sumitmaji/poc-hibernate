<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
		"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory name="sessionFactory">
        <property name="hibernate.connection.driver_class">oracle.jdbc.driver.OracleDriver</property>
        <property name="hibernate.dialect">org.hibernate.dialect.OracleDialect</property>
        <property name="hibernate.connection.password">${password}</property>
        <property name="hibernate.connection.url">${url}</property>
        <property name="hibernate.connection.username">${userName}</property>
        <property name="hibernate.default_schema">${schema}</property>
    </session-factory>
</hibernate-configuration>
