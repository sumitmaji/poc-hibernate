<?xml version="1.0" encoding="utf-8" ?>
<project name="ImageViewer" default="all" basedir="../../../../">
	<target name="all"/>

	<path id="toolslib">
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/hibernate-tools-3.2.4.GA.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/freemarker-2.3.8.jar" />
		<path location="D:/Software/gwt-2.4.0/gwt-2.4.0/gwt-user.jar" />
		<path location="D:/Software/gwt-2.4.0/gwt-2.4.0/gwt-dev.jar" />
		<path location="D:/Software/gwt-2.4.0/gwt-2.4.0/validation-api-1.0.0.GA-sources.jar" />
		<path location="D:/Software/gwt-2.4.0/gwt-2.4.0/validation-api-1.0.0.GA.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/gwtext.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/resources/hibernate" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/resources/properties" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/resources/spring/beans" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/resources/spring/config" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/resources/spring/database" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/log4j-1.2.15.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/properties" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-aop-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-beans-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-context-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-core-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-expression-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-jdbc-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-ldap-core-1.3.1.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-orm-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-security-config-3.0.1.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-security-core-3.0.1.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-security-ldap-3.0.1.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-security-web-3.0.1.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-tx-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-web-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/spring-webmvc-3.2.0.RELEASE.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/ojdbc6.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/hibernate-3.2.3.ga.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/ejb3-persistence.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/hibernate-annotations.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/hibernate-commons-annotations.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/jta-1.1.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/dom4j-1.6.1.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/cglib-2.2.2.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/ehcache-1.2.3.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/antlr-2.7.7.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/asm-3.1.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/asm-attrs-2.2.3.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/aopalliance-1.0.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/aspectj-1.6.9.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/aspectjrt.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/aspectjweaver-1.6.9.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/hibernate-validator-5.0.1.final.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/jboss-logging-3.1.0.GA.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/classmate-0.8.0.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/validation-api-1.1.0.final.jar" />
		<!--	<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/jsp-api-2.0.jar" /> -->
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/jstl-1.2.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/standard-1.0.2.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/commons-cli-1.0.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/commons-collections-2.1.1.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/commons-lang-2.1.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/commons-logging-1.1.1.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/commons-logging-api-1.1.jar" />
		<path location="E:/Work/JavaWorkSpace/POC/war/WEB-INF/lib/commons-net-3.3.jar" />
		<path location="D:/Software/gwt-2.4.0/gwt-2.4.0/gwt-dev.jar" />

	</path>


	<taskdef name="hibernatetool" 
			         classname="org.hibernate.tool.ant.HibernateToolTask" 
			         classpathref="toolslib" />

	<hibernatetool>
		<jdbcconfiguration configurationfile="${hibernateConfig}" packagename="${rootPackage}.server.model" detectmanytomany="true" detectoptimisticlock="true" />
		<hbm2java destdir="${sourceDirectory}" ejb3="true" jdk5="true"/>
		<!--<hbm2dao destdir="E:/Work/JavaWorkSpace/POC/src" ejb3="true" jdk5="true"/>-->
	</hibernatetool>


</project>
