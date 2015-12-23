package com.rick.scaffold.common.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class FileUtils {

	private static Logger log = Logger.getLogger(FileUtils.class);
	
	public static String readFileAsString(String fileName) {
		String path = FileUtils.class.getClassLoader().getResource(fileName).getPath();
		File file = new File(path);
		try {
			String fileReaded = Files.asCharSource(file, Charsets.UTF_8).read();
			return fileReaded;
		} catch (IOException e) {
			log.warn("read file "+ fileName + "error.");
			return null;
		}
	}

}
