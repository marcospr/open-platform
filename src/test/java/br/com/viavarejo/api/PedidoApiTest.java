package br.com.viavarejo.api;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.request.ConfirmacaoReqDTO;
import br.com.viavarejo.api.model.request.CriarPedidoRequest;
import br.com.viavarejo.api.model.request.DestinatarioDto;
import br.com.viavarejo.api.model.request.EnderecoEntregaDto;
import br.com.viavarejo.api.model.request.EntregaDadosDto;
import br.com.viavarejo.api.model.request.PedidoCarrinho;
import br.com.viavarejo.api.model.request.PedidoProdutoDto;
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
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			printErrorApi(e, "testPostPedidoCarrinhoWithSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
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
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			printErrorApi(e, "testGetDadosPedidoParceiroWithSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	public void testPatchPedidosCancelamentoOrConfirmacao() {
		Map<String, String> variableParams = new HashMap<>();
		variableParams.put("idCompra", "229559524");

		ConfirmacaoReqDTO dto = new ConfirmacaoReqDTO();
		dto.setIdCampanha(5984);
		dto.setIdPedidoParceiro(123123);
		dto.setConfirmado(false);
		dto.setIdPedidoMktplc("1-01");
		dto.setCancelado(true);
		dto.setMotivoCancelamento("teste");
		dto.setParceiro("BANCO INTER");

		Response response;
		try {
			response = pedidoApi.patchPedidosCancelamentoOrConfirmacao(dto, variableParams);
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			printErrorApi(e, "patchPedidosCancelamentoOrConfirmacao");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	public void testGetNotaFiscalPedidoWithSucess() {
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("idCompra", "247473612");
		pathParams.put("idCompraEntrega", "91712686");
		pathParams.put("formato", "PDF");

		String response;
		try {
			response = pedidoApi.getNotaFiscalPedido(pathParams);
			Assert.assertNotNull("Response nulo", response);
		} catch (ApiException e) {
			printErrorApi(e, "testGetNotaFiscalPedidoWithSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	public void testPostCriarPedido() {
		// Produto
		PedidoProdutoDto produto = new PedidoProdutoDto();
		produto.setIdLojista(10037);
		produto.setCodigo(8935731);
		produto.setQuantidade(1);
		produto.setPremio(0);
		produto.setPrecoVenda(29.90);
		List<PedidoProdutoDto> produtos = Arrays.asList(produto);

		// endereco Entrega
		EnderecoEntregaDto enderecoEntrega = new EnderecoEntregaDto();
		enderecoEntrega.setCep("01525-000");
		enderecoEntrega.setEstado("SP");
		enderecoEntrega.setLogradouro("rua da se");
		enderecoEntrega.setCidade("São Paulo");
		enderecoEntrega.setNumero(63);
		enderecoEntrega.setReferencia("teste");
		enderecoEntrega.setBairro("bairro se");
		enderecoEntrega.setComplemento("teste");
		enderecoEntrega.setTelefone("22333333");
		enderecoEntrega.setTelefone("22333335");
		enderecoEntrega.setTelefone("22333336");

		// destinatario
		DestinatarioDto destinatario = new DestinatarioDto();
		destinatario.setNome("teste");
		destinatario.setCpfCnpj("435.375.660-50");
		destinatario.setEmail("teste@teste.com");

		// dados entrega
		EntregaDadosDto dadosEntrega = new EntregaDadosDto();
		dadosEntrega.setValorFrete(4.53);

		// pedido
		CriarPedidoRequest pedido = new CriarPedidoRequest();
		pedido.setProdutos(Arrays.asList(produto));
		pedido.setEnderecoEntrega(enderecoEntrega);
		pedido.setDestinatario(destinatario);
		pedido.setDadosEntrega(dadosEntrega);
		pedido.setCampanha(3139);
		pedido.setCnpj("57.822.975/0001-12");
		pedido.setPedidoParceiro(122266121);
		pedido.setValorFrete(4.53);
		pedido.setAguardarConfirmacao(true);
		pedido.setOptantePeloSimples(true);

		Response response;
		try {
			response = pedidoApi.postCriarPedido(pedido);
			Assert.assertNotNull(response);
		} catch (ApiException e) {
			printErrorApi(e, "testPostCriarPedido");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	private void printErrorApi(ApiException e, String method) {
		System.out.println(String.format("ApiException %s \nCode: %s \nMessage: %s \nBody: %s \nHeaders: %s", method,
				e.getCode(), e.getMessage(), e.getResponseBody(), e.getResponseHeaders()));
	}

}
