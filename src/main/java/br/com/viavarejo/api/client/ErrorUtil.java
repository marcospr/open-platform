package br.com.viavarejo.api.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jersey.spi.inject.Errors;

public class ErrorUtil {
	private static final Logger logger = Logger.getLogger(ErrorUtil.class.getName());

	private ErrorUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static Errors deserializeErrors(String errorsJson, ApiClient apiClient) {

		Errors errors = null;
		try {

			errors = (Errors) JsonUtil.deserialize(errorsJson, "", Errors.class);

		} catch (ApiException e) {
			logger.log(Level.WARNING, "Doesnt containt errors structure.");
		}

		return errors;

	}

}
