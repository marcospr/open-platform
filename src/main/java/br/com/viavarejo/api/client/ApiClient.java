package br.com.viavarejo.api.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.ws.rs.core.Response.Status.Family;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.multipart.FormDataMultiPart;

public class ApiClient {
	private Client client;
	private Map<String, String> defaultHeaderMap = new HashMap<>();
	private String basePath;
	private boolean debugging = false;
	private DateFormat dateFormat;

	public ApiClient(String basePath, String token) {

		this.basePath = basePath;
		// Use ISO 8601 format for date and datetime.
		// See https://en.wikipedia.org/wiki/ISO_8601
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		// Use UTC as the default time zone.
		this.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		// Set default User-Agent.
		defaultHeaderMap.put("User-Agent", "Java-Client");

		defaultHeaderMap.put("Authorization", token);
	}

	public String getBasePath() {
		return basePath;
	}

	public ApiClient setBasePath(String basePath) {
		this.basePath = basePath;
		return this;
	}

	public void setUserAgent(String userAgent) {
		defaultHeaderMap.put("User-Agent", userAgent);
	}

	/**
	 * Check that whether debugging is enabled for this API client.
	 */
	public boolean isDebugging() {
		return debugging;
	}

	/**
	 * Enable/disable debugging for this API client.
	 *
	 * @param debugging To enable (true) or disable (false) debugging
	 */
	public ApiClient setDebugging(boolean debugging) {
		this.debugging = debugging;
		return this;
	}

	/**
	 * Get the date format used to parse/format date parameters.
	 */
	public DateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * Set the date format used to parse/format date parameters.
	 */
	public ApiClient getDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
		return this;
	}

	/**
	 * Parse the given string into Date object.
	 */
	public Date parseDate(String str) {
		try {
			return dateFormat.parse(str);
		} catch (java.text.ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Format the given Date object into string.
	 */
	public String formatDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * Format the given parameter object into string.
	 */
	public String parameterToString(Object param) {
		if (param == null) {
			return "";
		} else if (param instanceof Date) {
			return formatDate((Date) param);
		} else if (param instanceof Collection) {
			StringBuilder b = new StringBuilder();
			for (Object o : (Collection) param) {
				if (b.length() > 0) {
					b.append(",");
				}
				b.append(String.valueOf(o));
			}
			return b.toString();
		} else {
			return String.valueOf(param);
		}
	}

	/**
	 * Select the Accept header's value from the given accepts array: if JSON exists
	 * in the given array, use it; otherwise use all of them (joining into a string)
	 *
	 * @param accepts The accepts array to select from
	 * @return The Accept header to use. If the given array is empty, null will be
	 *         returned (not to set the Accept header explicitly).
	 */
	public String selectHeaderAccept(String[] accepts) {
		if (accepts.length == 0)
			return null;
		if (StringUtil.containsIgnoreCase(accepts, "application/json"))
			return "application/json";
		return StringUtil.join(accepts, ",");
	}

	/**
	 * Select the Content-Type header's value from the given array: if JSON exists
	 * in the given array, use it; otherwise use the first one of the array.
	 *
	 * @param contentTypes The Content-Type array to select from
	 * @return The Content-Type header to use. If the given array is empty, JSON
	 *         will be used.
	 */
	public String selectHeaderContentType(String[] contentTypes) {
		if (contentTypes.length == 0)
			return "application/json";
		if (StringUtil.containsIgnoreCase(contentTypes, "application/json"))
			return "application/json";
		return contentTypes[0];
	}

	/**
	 * Escape the given string to be used as URL query value.
	 */
	public String escapeString(String str) {
		try {
			return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	/**
	 * Invoke API by sending HTTP request with the given options.
	 *
	 * @param path         The sub-path of the HTTP URL
	 * @param method       The request method, one of "GET", "POST", "PUT", and
	 *                     "DELETE"
	 * @param queryParams  The query parameters
	 * @param body         The request body object
	 * @param headerParams The header parameters
	 * @param formParams   The form parameters
	 * @param accept       The request's Accept header
	 * @param contentType  The request's Content-Type header
	 * @param authNames    The authentications to apply
	 * @return The response body in type of string
	 */
	public String invokeAPI(String path, String method, Map<String, String> queryParams, Object body,
			Map<String, String> headerParams, Map<String, String> formParams, String accept, String contentType,
			String[] authNames) throws ApiException {

		Client client = getClient();

		StringBuilder b = new StringBuilder();
		for (String key : queryParams.keySet()) {
			String value = queryParams.get(key);
			if (value != null) {
				if (b.toString().length() == 0)
					b.append("?");
				else
					b.append("&");
				b.append(escapeString(key)).append("=").append(escapeString(value));
			}
		}
		String querystring = b.toString();

		Builder builder;
		if (accept == null)
			builder = client.resource(basePath + path + querystring).getRequestBuilder();
		else
			builder = client.resource(basePath + path + querystring).accept(accept);

		for (String key : headerParams.keySet()) {
			builder = builder.header(key, headerParams.get(key));
		}

		for (String key : defaultHeaderMap.keySet()) {
			if (!headerParams.containsKey(key)) {
				builder = builder.header(key, defaultHeaderMap.get(key));
			}
		}

		ClientResponse response = null;

		if ("GET".equals(method)) {
			response = builder.get(ClientResponse.class);
		} else if ("POST".equals(method)) {
			if (contentType.startsWith("application/x-www-form-urlencoded")) {
				String encodedFormParams = this.getXWWWFormUrlencodedParams(formParams);
				response = builder.type(contentType).post(ClientResponse.class, encodedFormParams);
			} else if (body == null) {
				response = builder.post(ClientResponse.class, null);
			} else if (body instanceof FormDataMultiPart) {
				response = builder.type(contentType).post(ClientResponse.class, body);
			} else
				response = builder.type(contentType).post(ClientResponse.class, JsonUtil.serialize(body));
		} else if ("PUT".equals(method)) {
			if ("application/x-www-form-urlencoded".equals(contentType)) {
				String encodedFormParams = this.getXWWWFormUrlencodedParams(formParams);
				response = builder.type(contentType).put(ClientResponse.class, encodedFormParams);
			} else if (body == null) {
				response = builder.put(ClientResponse.class, JsonUtil.serialize(body));
			} else {
				response = builder.type(contentType).put(ClientResponse.class, JsonUtil.serialize(body));
			}
		} else if ("DELETE".equals(method)) {
			if ("application/x-www-form-urlencoded".equals(contentType)) {
				String encodedFormParams = this.getXWWWFormUrlencodedParams(formParams);
				response = builder.type(contentType).delete(ClientResponse.class, encodedFormParams);
			} else if (body == null) {
				response = builder.delete(ClientResponse.class);
			} else {
				response = builder.type(contentType).delete(ClientResponse.class, JsonUtil.serialize(body));
			}
		} else {
			throw new ApiException(500, "unknown method type " + method);
		}

		String messageStatus = String.format("Status: %d - %s", response.getStatus(),
				response.getStatusInfo().toString());

		if (ClientResponse.Status.fromStatusCode(response.getStatus()) == ClientResponse.Status.NO_CONTENT) {
			return messageStatus;
		} else if (ClientResponse.Status.getFamilyByStatusCode(response.getStatus()) == Family.SUCCESSFUL) {
			if (response.hasEntity()) {
				return messageStatus + "\n" + JsonUtil.indent(response.getEntity(String.class));
			} else {
				return messageStatus;
			}
		} else {
			String respBody = null;
			if (response.hasEntity()) {
				respBody = String.valueOf(response.getEntity(String.class));
			}
			throw new ApiException(response.getStatus(), messageStatus, response.getHeaders(), respBody);
		}
	}

	/**
	 * Encode the given form parameters as request body.
	 */
	private String getXWWWFormUrlencodedParams(Map<String, String> formParams) {
		StringBuilder formParamBuilder = new StringBuilder();

		for (Entry<String, String> param : formParams.entrySet()) {
			String keyStr = parameterToString(param.getKey());
			String valueStr = parameterToString(param.getValue());

			try {
				formParamBuilder.append(URLEncoder.encode(keyStr, "utf8")).append("=")
						.append(URLEncoder.encode(valueStr, "utf8"));
				formParamBuilder.append("&");
			} catch (UnsupportedEncodingException e) {
				// move on to next
			}
		}
		String encodedFormParams = formParamBuilder.toString();
		if (encodedFormParams.endsWith("&")) {
			encodedFormParams = encodedFormParams.substring(0, encodedFormParams.length() - 1);
		}
		return encodedFormParams;
	}

	/**
	 * Get an existing client or create a new client to handle HTTP request.
	 */
	private Client getClient() {
		if (client == null) {
			this.client = Client.create();
			if (debugging) {
				this.client.addFilter(new LoggingFilter());
			}
		}
		return this.client;
	}
}
