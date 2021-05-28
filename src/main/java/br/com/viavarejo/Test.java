package br.com.viavarejo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jersey.spi.inject.Errors;

import br.com.viavarejo.api.PedidoApi;
import br.com.viavarejo.api.client.ApiClient;
import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.ErrorUtil;
import br.com.viavarejo.api.model.PedidoCarrinho;
import br.com.viavarejo.api.model.Produtos;

public class Test {
	private static final Logger logger = Logger.getLogger(Test.class.getName());

	public static void main(String[] args) {
		// postPedidoCarrinho();
		getPedido();

	}

	private static void getPedido() {
		ApiClient apiClient = new ApiClient("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
		PedidoApi pedidoApi = new PedidoApi(apiClient);
		try {

			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("request.idCompra", "247473612");
			queryParams.put("request.cnpj", "57.822.975/0001-12");
			queryParams.put("request.idCampanha", "5646");
			queryParams.put("request.idPedidoParceiro", "2020224509");

			String response = pedidoApi.getPedido(queryParams);
			logger.log(Level.INFO, response);

		} catch (ApiException e) {

			Errors errors = ErrorUtil.deserializeErrors(e.getMessage(), apiClient);

			if (errors == null) {
				logger.log(Level.SEVERE, "Error calling LOADS resource.", e);
			} else {
				logger.log(Level.SEVERE, errors.toString(), e);
			}

		}
	}

	private static void postPedidoCarrinho() {
		ApiClient apiClient = new ApiClient("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");


		PedidoApi pedidoApi = new PedidoApi(apiClient);

		Produtos produto = new Produtos(8935731, 1, 15);
		PedidoCarrinho pedidoCarrinho = new PedidoCarrinho(5940, "57.822.975/0001-12", "01525000",
				Arrays.asList(produto));

		try {

			String response = pedidoApi.postPedidosCarrinho(pedidoCarrinho);
			logger.log(Level.INFO, response);

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
