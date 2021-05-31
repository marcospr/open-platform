package br.com.viavarejo.api;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.viavarejo.api.client.ApiClient;
import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.PedidoCarrinho;
import br.com.viavarejo.api.model.Produtos;

public class PedidoApiTest {
	private ApiClient apiClient;
	private PedidoApi pedidoApi;

	@Before
	public void init() {
		apiClient = new ApiClient("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
		pedidoApi = new PedidoApi(apiClient);
	}

	@Test
	public void testGetPedidoWithSucess() {

		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("request.idCompra", "247473612");
		queryParams.put("request.cnpj", "57.822.975/0001-12");
		queryParams.put("request.idCampanha", "5646");
		queryParams.put("request.idPedidoParceiro", "2020224509");

		String response;
		try {
			response = pedidoApi.getPedido(queryParams);
			System.out.println(response);
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			fail("Falha. Uma exceção não deveria ser lançada!");
		}
	}

	@Test
	public void testPostPedidoCarrinhoWithSucess() {

		Produtos produto = new Produtos(8935731, 1, 15);
		PedidoCarrinho pedidoCarrinho = new PedidoCarrinho(5940, "57.822.975/0001-12", "01525000",
				Arrays.asList(produto));

		String response;
		try {
			response = pedidoApi.postPedidosCarrinho(pedidoCarrinho);
			System.out.println(response);
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			fail("Falha. Uma exceção não deveria ser lançada!");
		}

	}

}
