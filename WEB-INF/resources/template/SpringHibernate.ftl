<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">
	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">
		<property name="dataSource" ref="dataSource"></property>
		<property name="packagesToScan" value="${rootPackage}.server.model"/>
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">org.hibernate.dialect.OracleDialect</prop>
				<prop key="hibernate.show_sql">true</prop>

			</props>
		</property>

	</bean>
	<bean id="validatorFactory" class="javax.validation.Validation" factory-method="buildDefaultValidatorFactory" />

	<bean id="validator" factory-bean="validatorFactory" factory-method="getValidator" />
	<!-- HibernateTemplate is not recommended by Spring transaction management 
		<bean id="template" class="org.springframework.orm.hibernate3.HibernateTemplate"> 
		<property name="sessionFactory" ref="sessionFactory"></property> </bean> -->


	<!-- It is used for automatic transaction management as transaction begin, 
		commit and rollback is handaled -->
	<bean id="transactionManager"
		class="org.springframework.orm.hibernate3.HibernateTransactionManager">
		<property name="sessionFactory">
			<ref bean="sessionFactory" />
		</property>
	</bean>


</beans>