package com.draglantix.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileLoader {
	
	private String path;
	
	public FileLoader(String path) {
		this.path = path;
	}

	public BufferedReader getReader() throws Exception {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			return reader;
		} catch (Exception e) {
			System.err.println("Couldn't get reader for " + path);
			throw e;
		}
	}
	
}
