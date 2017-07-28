package com.java.io.objectSerializable;

import java.io.Serializable;

public class Data implements Serializable{
	private int n;

	public Data(int n) {
		super();
		this.n = n;
	}

	@Override
	public String toString() {
		return "Data [n=" + n + "]";
	}
	
}
