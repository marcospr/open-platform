package br.com.viavarejo.api;

import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.multipart.FormDataMultiPart;

import br.com.viavarejo.api.client.ApiClient;
import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.Configuration;
import br.com.viavarejo.api.model.response.PedidoCarrinho;

public class PedidoCarrinhoApi {
	private ApiClient apiClient;

	public PedidoCarrinhoApi() {
		this(Configuration.getDefaultApiClient());
	}

	public PedidoCarrinhoApi(ApiClient apiClient) {
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
			throw new ApiException(400,
					"Missing the required parameter 'ordersCart' when calling postOrdersCart");
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

//		if (contentType.startsWith("multipart/form-data")) {
//			boolean hasFields = false;
//			FormDataMultiPart mp = new FormDataMultiPart();
//
//			if (hasFields)
//				postBody = mp;
//		} else {
//
//		}

		headerParams.put("Authorization", "H9xO4+R8GUy+18nUCgPOlg==");
		
		try {
			String[] authNames = new String[] {  };
			String response = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept,
					contentType, authNames);
			if (response != null) {
				return (String) apiClient.deserialize(response, "", String.class);
			} else {
				return null;
			}
		} catch (ApiException ex) {
			throw ex;
		}
	}
}
