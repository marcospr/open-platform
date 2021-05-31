package br.com.viavarejo.api;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.request.ConfirmacaoReqDTO;
import br.com.viavarejo.api.model.request.PedidoCarrinho;
import br.com.viavarejo.api.model.request.Produtos;
import br.com.viavarejo.api.model.response.Pedido;

public class PedidoApiTest {
	private PedidoApi pedidoApi;

	@Before
	public void init() {
		pedidoApi = new PedidoApi("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
	}

	@Test
	public void testPostPedidoCarrinhoWithSucess() {
		Produtos produto = new Produtos();
		produto.setCodigo(8935731);
		produto.setQuantidade(1);
		produto.setIdLojista(15);

		PedidoCarrinho pedidoCarrinho = new PedidoCarrinho();

		pedidoCarrinho.setIdCampanha(5940);
		pedidoCarrinho.setCnpj("57.822.975/0001-12");
		pedidoCarrinho.setCep("01525000");
		pedidoCarrinho.setProdutos(Arrays.asList(produto));

		Response response;
		try {
			response = pedidoApi.postPedidosCarrinho(pedidoCarrinho);
			System.out.println(response);
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			fail("Falha. Uma exceção não deveria ser lançada!");
		}
	}

	@Test
	public void testGetDadosPedidoParceiroWithSucess() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("request.idCompra", "247473612");
		queryParams.put("request.cnpj", "57.822.975/0001-12");
		queryParams.put("request.idCampanha", "5646");
		queryParams.put("request.idPedidoParceiro", "2020224509");

		Pedido response;
		try {
			response = pedidoApi.getDadosPedidoParceiro(queryParams);
			System.out.println(response);
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			fail("Falha. Uma exceção não deveria ser lançada!");
		}
	}

	@Test
	public void patchPedidosCancelamentoOrConfirmacao() {
		Map<String, String> variableParams = new HashMap<>();
		variableParams.put("idCompra", "229559524");

		ConfirmacaoReqDTO dto = new ConfirmacaoReqDTO();
		dto.setIdCampanha(5984);
		dto.setIdPedidoParceiro(123123);
		dto.setConfirmado(false);
		dto.setIdPedidoMktplc("1-01");
		dto.setCancelado(true);
		dto.setParceiro("BANCO INTER");

		Response response;
		try {
			response = pedidoApi.patchPedidosCancelamentoOrConfirmacao(dto, variableParams);
			System.out.println(response);
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			e.printStackTrace();
			fail("Falha. Uma exceção não deveria ser lançada!");
		}
	}

}
