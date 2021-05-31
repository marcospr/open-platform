package br.com.viavarejo.api.client;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


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

	private Response doGet(String path, String accessToken, Map<String, String> queryParams) {
		Response response = null;
		WebTarget webTarget = this.createWebTarget(path + queryParamStringBuilder(queryParams));
		if(queryParams != null) {
			//webTarget = webTarget.path();	
		}	
		if (accessToken != null) {
			response = webTarget.request().header("Authorization", accessToken).get();
		} else {
			response = webTarget.request().get();
		}
		
//		if(response.getStatus() != 200) {
//			throw new ApiException(response.getStatus(), response.);
//		}
		
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
	
//	private String escapeString(String str) {
//		try {
//			return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
//		} catch (UnsupportedEncodingException e) {
//			return str;
//		}
//	}
}
