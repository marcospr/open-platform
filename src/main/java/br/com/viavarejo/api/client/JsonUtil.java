package br.com.viavarejo.api.client;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JsonUtil {
  public static ObjectMapper mapper;

  static {
  	mapper = new ObjectMapper();
	  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	  mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	  mapper.registerModule(new JodaModule());
	}

	public static ObjectMapper getJsonMapper() {
		return mapper;
	}
	
	public static final String indent(String json) {
		JSONObject jo= new JSONObject(json );		
		return jo.toString(4);
	}
	
	/**
	 * Deserialize the given JSON string to Java object.
	 *
	 * @param json          The JSON string
	 * @param containerType The container type, one of "list", "array" or ""
	 * @param cls           The type of the Java object
	 * @return The deserialized Java object
	 */
	public static final Object deserialize(String json, String containerType, Class cls) throws ApiException {
		if (null != containerType) {
			containerType = containerType.toLowerCase();
		}
		try {
			if ("list".equals(containerType) || "array".equals(containerType)) {
				JavaType typeInfo = JsonUtil.getJsonMapper().getTypeFactory().constructCollectionType(List.class, cls);
				List response = (List<?>) JsonUtil.getJsonMapper().readValue(json, typeInfo);
				return response;
			} else if (String.class.equals(cls)) {
				if (json != null && json.startsWith("\"") && json.endsWith("\"") && json.length() > 1)
					return json.substring(1, json.length() - 2);
				else
					return json;
			} else {
				return JsonUtil.getJsonMapper().readValue(json, cls);
			}
		} catch (IOException e) {
			throw new ApiException(500, e.getMessage(), null, json);
		}
	}

	/**
	 * Serialize the given Java object into JSON string.
	 */
	public static final String serialize(Object obj) throws ApiException {
		try {
			if (obj != null)
				return JsonUtil.getJsonMapper().writeValueAsString(obj);
			else
				return null;
		} catch (Exception e) {
			throw new ApiException(500, e.getMessage());
		}
	}

}
