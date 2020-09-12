package com.sum.server.entity;

public interface EntityTabStack {
	public EntityTab enter(String moduleName);
	public EntityTab getModule(String moduleName);
}
