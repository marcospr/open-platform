package br.com.viavarejo.api.client;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class JsonConverter<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 2434333182892020955L;

	private transient GsonBuilder gsonBuilder;

	private transient Gson gson;

	private transient JsonParser jsonParser;

	private Class<T> clazz;

	public JsonConverter(Class<T> clazz) {
		this.gsonBuilder = new GsonBuilder();
		this.jsonParser = new JsonParser();
		gson = gsonBuilder.setDateFormat("dd/MM/yyyy HH:mm:ss").create();
		this.clazz = clazz;
	}

	public T convertToObject(String inputAsJson) {
		return gson.fromJson(inputAsJson, clazz);
	}

	public T convertToObject(Response response)  {
		//validarStatus(response);
		String json = getStringJson(response);
		return gson.fromJson(json, clazz);
	}

	public String convertToString(T entity) {
		return gson.toJson(entity);
	}


	private String getStringJson(Response response) {
		return response.readEntity(String.class);
	}


}
