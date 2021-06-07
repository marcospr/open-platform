package br.com.via.api;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.via.api.client.ApiException;
import br.com.via.api.model.request.ConfirmacaoReqDTO;
import br.com.via.api.model.request.CriarPedidoRequest;
import br.com.via.api.model.request.DestinatarioDto;
import br.com.via.api.model.request.EnderecoEntregaDto;
import br.com.via.api.model.request.EntregaDadosDto;
import br.com.via.api.model.request.PedidoCarrinho;
import br.com.via.api.model.request.PedidoProdutoDto;
import br.com.via.api.model.request.Produtos;
import br.com.via.api.model.response.CalculoCarrinho;
import br.com.via.api.model.response.ConfirmacaoDTO;
import br.com.via.api.model.response.CriacaoPedidoDTO;
import br.com.via.api.model.response.PedidoParceiroData;

public class PedidoApiTest {

	private PedidoApi pedidoApi;

	@Before
	public void init() {
		pedidoApi = new PedidoApi("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
	}

	@Test
	public void testPostPedidoCarrinhoWithSucess() {
		final long ID_SKU = 8935731L;
		Produtos produto = new Produtos();
		produto.setCodigo(8935731);
		produto.setQuantidade(1);
		produto.setIdLojista(15);

		PedidoCarrinho pedidoCarrinho = new PedidoCarrinho();

		pedidoCarrinho.setIdCampanha(5940);
		pedidoCarrinho.setCnpj("57.822.975/0001-12");
		pedidoCarrinho.setCep("01525000");
		pedidoCarrinho.setProdutos(Arrays.asList(produto));

		CalculoCarrinho calculoCarrinho;
		try {
			calculoCarrinho = pedidoApi.postPedidosCarrinho(pedidoCarrinho);
			Assert.assertEquals(ID_SKU, calculoCarrinho.getData().getProdutos().get(0).getIdSku().longValue());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedido"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	public void testGetDadosPedidoParceiroWithSucess() {
		final int CODIGO_PEDIDO = 270273478;
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("idCompra", String.valueOf(CODIGO_PEDIDO));

		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("request.idCompra", "270273478");
		queryParams.put("request.cnpj", "57.822.975/0001-12");
		queryParams.put("request.idCampanha", "5940");
		queryParams.put("request.idPedidoParceiro", "3222981219");

		PedidoParceiroData pedido;
		try {
			pedido = pedidoApi.getDadosPedidoParceiro(pathParams, queryParams);
			Assert.assertEquals(CODIGO_PEDIDO, pedido.getData().getPedido().getCodigoPedido());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedido"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	public void testPatchPedidosCancelamento() {
		Map<String, String> variableParams = new HashMap<>();
		variableParams.put("idCompra", "228682072");

		ConfirmacaoReqDTO dto = new ConfirmacaoReqDTO();
		dto.setIdCampanha(5940);
		dto.setIdPedidoParceiro(551226);
		dto.setCancelado(true);
//		dto.setIdPedidoMktplc("1-01");
//		dto.setCancelado(true);
//		dto.setMotivoCancelamento("teste");
//		dto.setParceiro("BANCO INTER");

		ConfirmacaoDTO confirmacaoDto;
		try {
			confirmacaoDto = pedidoApi.patchPedidosCancelamentoOrConfirmacao(dto, variableParams);
			Assert.assertTrue(confirmacaoDto.getData().getPedidoCancelado());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedido"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	public void testPatchPedidosConfirmacao() {
		Map<String, String> variableParams = new HashMap<>();
		variableParams.put("idCompra", "228682072");

		ConfirmacaoReqDTO dto = new ConfirmacaoReqDTO();
		dto.setIdCampanha(5940);
		dto.setIdPedidoParceiro(551226);
		dto.setConfirmado(true);
//		dto.setIdPedidoMktplc("1-01");
//		dto.setCancelado(true);
//		dto.setMotivoCancelamento("teste");
//		dto.setParceiro("BANCO INTER");

		ConfirmacaoDTO confirmacaoDto;
		try {
			confirmacaoDto = pedidoApi.patchPedidosCancelamentoOrConfirmacao(dto, variableParams);
			Assert.assertTrue(confirmacaoDto.getData().getPedidoConfirmado());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedido"));
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
			fail(printErrorApi(e, "testPostCriarPedido"));
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

		CriacaoPedidoDTO criacaoPedidoDTO;
		try {
			criacaoPedidoDTO = pedidoApi.postCriarPedido(pedido);
			Assert.assertNotNull(criacaoPedidoDTO);
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedido"));
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

		CriacaoPedidoDTO criacaoPedidoDTO;
		try {
			criacaoPedidoDTO = pedidoApi.postCriarPedido(pedido);
			Assert.assertNotNull(criacaoPedidoDTO);
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedido"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	private String printErrorApi(ApiException e, String method) {
		return String.format(
				"Falha. Uma exceção ApiException não deveria ser lançada!\nApiException %s \nCode: %s \nMessage: %s \nBody: %s \nHeaders: %s",
				method, e.getCode(),
				e.getMessage(), e.getResponseBody(), e.getResponseHeaders());
	}
}
