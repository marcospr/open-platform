package br.com.viavarejo;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.sun.jersey.spi.inject.Errors;

import br.com.viavarejo.api.PedidoCarrinhoApi;
import br.com.viavarejo.api.client.ApiClient;
import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.ErrorUtil;
import br.com.viavarejo.api.client.JsonUtil;
import br.com.viavarejo.api.client.auth.ApiKeyAuth;
import br.com.viavarejo.api.model.response.PedidoCarrinho;
import br.com.viavarejo.api.model.response.Produtos;

public class Test {
	private static final Logger logger = Logger.getLogger(Test.class.getName());

	public static void main(String[] args) {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://api-integracao-extra.hlg-b2b.net");

		((ApiKeyAuth) apiClient.getAuthentication("access_token")).setApiKey("H9xO4+R8GUy+18nUCgPOlg==");

		PedidoCarrinhoApi pedidoCarrinhoApi = new PedidoCarrinhoApi(apiClient);

		Produtos produto = new Produtos(8935731, 1, 15);
		PedidoCarrinho pedidoCarrinho = new PedidoCarrinho(5940, "57.822.975/0001-12", "01525000",
				Arrays.asList(produto));

		try {

			String response = pedidoCarrinhoApi.postPedidosCarrinho(pedidoCarrinho);
			logger.info("Response: \n" + JsonUtil.indent(response));

		} catch (ApiException e) {

			Errors errors = ErrorUtil.deserializeErrors(e.getMessage(), apiClient);

			if (errors == null) {
				logger.log(Level.SEVERE, "Error calling LOADS resource.", e);
			} else {
				logger.log(Level.SEVERE, errors.toString(), e);
			}

		}

	}

}
