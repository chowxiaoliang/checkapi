package com.zl.checkapi.elasticsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Log {

	private Log() {
	}

	private static Map<String, Logger> loggers = new HashMap<>();
	private static Logger log =  LoggerFactory.getLogger(Log.class);

	public static Logger getLogger(Class<?> cls) {

		if(cls == null){
			return log;
		}
		Logger logger = loggers.get(cls.getCanonicalName());

		if (logger == null) {
			logger = LoggerFactory.getLogger(cls);
			loggers.put(cls.getCanonicalName(), logger);
		}

		return logger;
	}

	public static void debug(Class<?> cls, String msg) {

		getLogger(cls).debug(msg);
	}

	public static void debug(Class<?> cls, String msg, Object... params) {

		getLogger(cls).debug(msg, params);
	}

	public static void info(Class<?> cls, String msg) {

		getLogger(cls).info(msg);
	}

	public static void info(Class<?> cls, String msg, Object... params) {

		getLogger(cls).info(msg, params);
	}

	public static void warn(Class<?> cls, String msg) {

		getLogger(cls).warn(msg);
	}

	public static void warn(Class<?> cls, String msg, Object... params) {

		getLogger(cls).warn(msg, params);
	}

	public static void warn(Class<?> cls, String msg, Throwable t) {

		getLogger(cls).warn(msg, t);
	}

	public static void error(Class<?> cls, String msg) {

		getLogger(cls).error(msg);
	}

	public static void error(Class<?> cls, String msg, Object... params) {

		getLogger(cls).error(msg, params);
	}

	public static void error(Class<?> cls, String msg, Throwable t) {

		getLogger(cls).error(msg, t);
	}

}
