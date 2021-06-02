package br.com.viavarejo.api.client;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;


public class RequestUtil<T extends Serializable> implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private transient Client client;
	private JsonConverter<T> tratarRetorno;

	public RequestUtil(Class<T> clazz) {
		this.tratarRetorno = new JsonConverter<>(clazz);
	}

	public T get(String path, String accessToken) throws ApiException {
		return this.get(path, accessToken, null);
	}

	public T get(String path, String accessToken, Map<String, String> queryParams) throws ApiException {
		try {
			Response response = this.doGet(path, accessToken, queryParams);
			T retorno = this.tratarRetorno.convertToObject(response);
			return retorno;
		} finally {
			this.closeClient();
		}
	}
	
	public T get(String path, String accessToken, String parameter, List<String> parameterValues) throws ApiException {
		try {
		Response response = this.doGet(path, accessToken, parameter, parameterValues);
		T retorno = tratarRetorno.convertToObject(response);
		return retorno;
		} finally {
		this.closeClient();
		}
		}


	public Response post(String path, T entity) throws ApiException{
		try {
			Response response = this.doPost(path, null, entity);
			return response;
		} finally {
			this.closeClient();
		}
	}

	public Response post(String path, String accessToken, T entity) throws ApiException {
		try {
			Response response = this.doPost(path, accessToken, entity);
			return response;
		} finally {
			this.closeClient();
		}
	}

	public Response patch(String path, String accessToken, T entity) throws ApiException {
		try {
			Response response = this.doPath(path, accessToken, entity);
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

	private Response doPost(String path, String accessToken, T entity) throws ApiException {
		Response response = null;
		WebTarget webTarget = this.createWebTarget(path);
		if (accessToken != null) {
			response = webTarget.request().header("Authorization", accessToken)
					.post(Entity.json(entity));
		} else {
			response = webTarget.request().post(Entity.json(entity));
		}
		validarResponse(response);
		return response;
	}

	private Response doPath(String path, String accessToken, T entity) throws ApiException {
		Response response = null;
		WebTarget webTarget = this.createWebTarget(path);
		webTarget.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
		if (accessToken != null) {

			response = webTarget.request().header("Authorization", accessToken).method("PATCH", Entity.json(entity));
		} else {
			response = webTarget.request().method("PATCH", Entity.json(entity));
		}
		validarResponse(response);
		return response;
	}

	private Response doGet(String path, String accessToken, Map<String, String> queryParams) throws ApiException {
		Response response = null;
		String fullPath = path;
		if(queryParams != null) {
			fullPath = fullPath + queryParamStringBuilder(queryParams);
		}	
		
		WebTarget webTarget = this.createWebTarget(fullPath);
		if (accessToken != null) {
			response = webTarget.request().header("Authorization", accessToken).get();
		} else {
			response = webTarget.request().get();
		}
		validarResponse(response);
		return response;
	}
	
	private String queryParamStringBuilder(Map<String, String> queryParams) {
		StringBuilder b = new StringBuilder();
		for (String key : queryParams.keySet()) {
			String value = queryParams.get(key);
			if (value != null) {
				if (b.toString().length() == 0)
					b.append("?");
				else
					b.append("&");
				b.append(key).append("=").append(value);
			}
		}
		return b.toString();
	}
	
	private Response doGet(String path, String accessToken, String parameter, List<String> parameterValues) {
		Response response = null;
		WebTarget webTarget = this.createWebTarget(path);
		if (parameter != null) {
		webTarget = webTarget.queryParam(parameter, parameterValues.toArray());
		}
		if (accessToken != null) {
			response = webTarget.request().header("Authorization", accessToken).get();
		} else {
			response = webTarget.request().get();
		}
		return response;
		}
	
	private void validarResponse(Response response) throws ApiException {
		if (response != null && response.getStatusInfo().getFamily() == Family.SERVER_ERROR
				|| response.getStatusInfo().getFamily() == Family.CLIENT_ERROR) {
			String message = response.getStatusInfo().getReasonPhrase();
			String respBody = null;
			if (response.hasEntity()) {
				try {
					respBody = String.valueOf(response.readEntity(String.class));
				} catch (RuntimeException e) {
					message += "\n" + e.getMessage();
				}
			}
			throw new ApiException(response.getStatus(), message, response.getHeaders(), respBody);
		}
	}

}
