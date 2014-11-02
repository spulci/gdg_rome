package org.gdgrome.lab.gdgcultfest.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageKeyDispatcher {
	private static final String BUNDLE_NAME = "org.gdgrome.lab.gdgcultfest.services.sparql_query.properties"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private MessageKeyDispatcher() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}

