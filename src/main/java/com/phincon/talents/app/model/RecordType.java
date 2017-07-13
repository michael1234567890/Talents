package com.phincon.talents.app.model;

public class RecordType {
	private String Id;
	private String Name;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public RecordType(String id, String name) {
		Id = id;
		Name = name;
	}

	public RecordType() {
	}
	
	

	
}
