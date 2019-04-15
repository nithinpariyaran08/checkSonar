package com.projectmanagment.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
public class DatabaseSequence {
	private String name;

	private long sequence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

}
