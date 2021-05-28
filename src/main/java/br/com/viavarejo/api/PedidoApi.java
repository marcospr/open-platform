package br.com.viavarejo.api;

import java.util.HashMap;
import java.util.Map;

import br.com.viavarejo.api.client.ApiClient;
import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.JsonUtil;
import br.com.viavarejo.api.model.PedidoCarrinho;

public class PedidoApi {
	private ApiClient apiClient;

	public PedidoApi(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public String postPedidosCarrinho(PedidoCarrinho pedidosCarrinho) throws ApiException {
		Object postBody = pedidosCarrinho;

		// verify the required parameter 'ordersTrackings' is set
		if (pedidosCarrinho == null) {
			throw new ApiException(400, "Missing the required parameter 'ordersCart' when calling postOrdersCart");
		}

		// create path and map variables
		String path = "/pedidos/carrinho".replaceAll("\\{format\\}", "json");

		// query params
		Map<String, String> queryParams = new HashMap<>();
		Map<String, String> headerParams = new HashMap<>();
		Map<String, String> formParams = new HashMap<>();

		final String[] accepts = {

		};
		final String accept = apiClient.selectHeaderAccept(accepts);

		final String[] contentTypes = { "application/json" };
		final String contentType = apiClient.selectHeaderContentType(contentTypes);

		String[] authNames = new String[] {};
		String response = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept,
				contentType, authNames);
		if (response != null) {
			return (String) JsonUtil.deserialize(response, "", String.class);
		} else {
			return null;
		}
	}

	public String getPedido(Map<String, String> queryParams) throws ApiException {
		// create path and map variables
		String path = "/pedidos/{idCompra}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "idCompra" + "\\}",
				queryParams.get("request.idCompra"));

		// params
		// Map<String, String> queryParams = new HashMap<>();
		Map<String, String> headerParams = new HashMap<>();
		Map<String, String> formParams = new HashMap<>();

		final String[] accepts = {};
		final String accept = apiClient.selectHeaderAccept(accepts);

		final String[] contentTypes = { "application/json" };
		final String contentType = apiClient.selectHeaderContentType(contentTypes);

		String[] authNames = new String[] {};
		Object getBody = null;
		String response = apiClient.invokeAPI(path, "GET", queryParams, getBody, headerParams, formParams, accept,
				contentType, authNames);
		if (response != null) {
			return (String) JsonUtil.deserialize(response, "", String.class);
		} else {
			return null;
		}
	}
}
