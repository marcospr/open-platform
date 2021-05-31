package br.com.viavarejo.api.client;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;

public class RequestUtil<T extends Serializable> implements Serializable {

	private transient Client client;
	private JsonConverter<T> tratarRetorno;

	public RequestUtil(Class<T> clazz) {
		this.tratarRetorno = new JsonConverter<>(clazz);
	}

	public T get(String path) throws ApiException {
		return this.get(path, null);
	}

	public T get(String path, String accessToken) throws ApiException {
		try {
			Response response = this.doGet(path, accessToken, null, null);
			T retorno = this.tratarRetorno.convertToObject(response);
			return retorno;
		} finally {
			this.closeClient();
		}
	}


	public Response post(String path, T entity) {
		try {
			Response response = this.doPost(path, null, entity);
			return response;
		} finally {
			this.closeClient();
		}
	}

	public Response post(String path, String accessToken, T entity) {
		try {
			Response response = this.doPost(path, accessToken, entity);
			return response;
		} finally {
			this.closeClient();
		}
	}

	private WebTarget createWebTarget(String path) {
		this.client = ClientBuilder.newClient();
		return this.client.target(path);
	}

	private void closeClient() {
		if (this.client != null) {
			this.client.close();
			this.client = null;
		}
	}

	private Response doPost(String path, String accessToken, T entity) {
		Response response = null;
		WebTarget webTarget = this.createWebTarget(path);
		if (accessToken != null) {
			response = webTarget.request().header("Authorization", accessToken).post(Entity.json(entity));
		} else {
			response = webTarget.request().post(Entity.json(entity));
		}
		return response;
	}

	private Response doGet(String path, String accessToken, String parameter, List<String> parameterValues) {
		Response response = null;
		WebTarget webTarget = this.createWebTarget(path);
		if (parameter != null) {
			webTarget = webTarget.queryParam(parameter, parameterValues.toArray());
		}
		if (accessToken != null) {
			String bearerAccessToken = String.format(BEARER_ACCESS_TOKEN, accessToken);
			response = webTarget.request().header(AUTHORIZATION_HEADER, bearerAccessToken).get();
		} else {
			response = webTarget.request().get();
		}
		return response;
	}
}
