package br.com.viavarejo.api.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.ClientResponse.Status;

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

	public String convertToList(List<T> entitys) {

		StringBuilder finalResult = new StringBuilder("");
		StringBuilder partialResult = new StringBuilder("");

		for (T entity : entitys) {
			partialResult.append(convertToString(entity));
			partialResult.append(",");
		}

		if (partialResult.length() > 0) {
			// remove a última virgula para nao gerar um objeto a mais
			partialResult.setLength(partialResult.length() - 1);
		}

		finalResult.append("[").append(partialResult).append("]");
		return finalResult.toString();

	}

	private String getStringJson(Response response) {
		return response.readEntity(String.class);
	}


}
