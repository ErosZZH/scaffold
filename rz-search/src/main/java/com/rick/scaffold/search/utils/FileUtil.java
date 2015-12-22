package com.rick.scaffold.search.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.Logger;

public class FileUtil {

	private static Logger log = Logger.getLogger(FileUtil.class);
	
	public static String readFileAsString(String fileName)
			throws java.io.IOException {
		InputStream is = null;
		Reader r = null;
		try {
			is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new java.io.IOException("File " + fileName + " not found");
			}
			InputStreamReader in = new InputStreamReader(is);
			StringBuffer buffer = new StringBuffer();
			r = new BufferedReader(in);
			int ch;
			while ((ch = r.read()) > -1) {
				buffer.append((char) ch);
			}
			r.close();
			return buffer.toString();
		} finally {
			if (r != null) {
				try {
					r.close();
				} catch (Exception ignore) {
					log.debug("Close reader fail.");
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
					log.debug("Close inputStream fail.");
				}
			}
		}
	}

}
