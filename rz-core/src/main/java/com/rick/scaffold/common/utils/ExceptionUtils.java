package com.rick.scaffold.common.utils;

import java.util.UUID;

public class ExceptionUtils {

	public static String getTrackID() {
		return UUID.randomUUID().toString();
	}
}
