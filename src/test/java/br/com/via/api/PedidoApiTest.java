package br.com.via.api;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.via.api.client.ApiException;
import br.com.via.api.model.request.CartaoCreditoDadosDto;
import br.com.via.api.model.request.CartaoCreditoDadosValidacaoDto;
import br.com.via.api.model.request.ConfirmacaoReqDTO;
import br.com.via.api.model.request.CriacaoPedidoRequest;
import br.com.via.api.model.request.DestinatarioDto;
import br.com.via.api.model.request.EnderecoCobrancaDto;
import br.com.via.api.model.request.EnderecoEntregaDto;
import br.com.via.api.model.request.EntregaDadosDto;
import br.com.via.api.model.request.PagamentoComplementarDto;
import br.com.via.api.model.request.PedidoCarrinho;
import br.com.via.api.model.request.PedidoProdutoDto;
import br.com.via.api.model.request.Produtos;
import br.com.via.api.model.response.CalculoCarrinho;
import br.com.via.api.model.response.ConfirmacaoDTO;
import br.com.via.api.model.response.CriacaoPedidoDTO;
import br.com.via.api.model.response.PedidoParceiroData;

public class PedidoApiTest {

	private PedidoApi pedidoApi;
	private static final String HOST_EXTRA = "http://api-integracao-extra.hlg-b2b.net";
	private static final String HOST_CASAS_BAHIA = "";
	private static final String HOST_CASAS_PONTO = "";

	@Before
	public void init() {
		pedidoApi = new PedidoApi(HOST_EXTRA, "H9xO4+R8GUy+18nUCgPOlg==");
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
		CriacaoPedidoRequest pedido = new CriacaoPedidoRequest();
		pedido.setProdutos(produtos);
		pedido.setEnderecoEntrega(enderecoEntrega);
		pedido.setDestinatario(destinatario);
		pedido.setDadosEntrega(dadosEntrega);
		pedido.setCampanha(3139);
		pedido.setCnpj("57.822.975/0001-12");
		int idPedidoParceiro = new Random().nextInt(65536) - 32768;
		pedido.setPedidoParceiro(idPedidoParceiro);
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
	public void testPostCriarPedidoPagCartao() {
		// Produto
		PedidoProdutoDto produto = new PedidoProdutoDto();
		produto.setIdLojista(15);
		produto.setCodigo(9342200);
		produto.setQuantidade(1);
		produto.setPrecoVenda(119.90);
		List<PedidoProdutoDto> produtos = Arrays.asList(produto);

		// endereco Entrega
		EnderecoEntregaDto enderecoEntrega = new EnderecoEntregaDto();
		enderecoEntrega.setCep("01525000");
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

		// pedido
		CriacaoPedidoRequest pedido = new CriacaoPedidoRequest();
		int idPedidoParceiro = new Random().nextInt(65536) - 32768;
		pedido.setCampanha(5940);
		pedido.setCnpj("57.822.975/0001-12");
		pedido.setPedidoParceiro(idPedidoParceiro);
		pedido.setValorFrete(15.90);
		pedido.setAguardarConfirmacao(true);
		pedido.setOptantePeloSimples(true);
		pedido.setPossuiPagtoComplementar(true);

		// pagamentos complementares
		PagamentoComplementarDto pagamentoComplementarDto = new PagamentoComplementarDto();
		pagamentoComplementarDto.setIdFormaPagamento(2);

		// dados cartao credito
		CartaoCreditoDadosDto cartaoCreditoDadosDto = new CartaoCreditoDadosDto();
		cartaoCreditoDadosDto.setNome(
				"qrTvioqjqt2lLiAkzp8ERtKAIglq9PI9FyDh8eVtuoWZvtVFRHKBzidIxCCELAluS+65QpyO1u4kTruGMyzyj++QtJT9MI0si0gFy9euz4GvCz6sl8+yx3GnJ5u6H9D3O3DugfWM4Gl3Lq6CPjT+qSjrYKul7qKkAR395rXlKLgxv3ENtSqMe+ttE60/yDmGVboP6d/wG7Wr5IDwzWUz6VD6nw3aU584rJ4B6jUwnAENA12xKPGOpI/XlcrbkpJw5Gz4XBveWkB/C+m4fY1cLT6LRJXXAJx9h2flvtSA8J1XPvDdbsKX9IhIYs7H88Z/t3LTTmTjEVcxeE56kA1feg==");
		cartaoCreditoDadosDto.setNumero(
				"bsDiir5JMhpB3RCGu5IVF2FvvBMq+eoyva/T71B5HGff7/x0Wz9y3Q98UBWVJ9z8jm/y7TnqxrP7PFGbBwlVEg09aQbSpOCMGpe7TBgjPUH6mteioKVA3bDeBhOURtoqRnzMoXYFbMV1xsaMJBZxxCAZkywdN4h+G2np5/OlRxYXhKgNIvUMUWgtL2zsN0EOnVEajtoO3K+A4CpmO480SqhlggKo6U64464ZaIYxLRb9ajONiaLNtZLYK4WorclAnQDQWOMUP8w75zd/Y/DF8S/pGflAy8ElCFbd0Kuramxb9IVPxIXosZa4C+PSvplht6tjgBKfHk83NzaQxxiVnA==");
		cartaoCreditoDadosDto.setCodigoVerificador(
				"NPFWj2Fo5jazlTV6O/ouNkTOU6h+/RSP2d0fvVryXtOm4PliK1MQT6TdaEKEdw1fkTg2Zzt0tr2qFgZvCq1nKewsS4SfRAUBLCQEJSF0JkzS42PV2HIEt8zTq2Y/iIT2AOowrM0NPztcFOmsuNvoIqt5GbqMpNpalRZrN9gHsjeDPjwdUrnI+3vUL8w1g9YQYgGUG+9P6ZzPrZsz0YQN4znchaU34hnSW6ojl83pu2xMn/HfCA3wGOJM3DWT7eDfplbOYOWWjzUGz3/HVI/bpv0H/4hyHimLK6eZP5Wa7EqBKxB0B3G9bbDMBzujZluQ3wemEhgfOX/LVi91l7NIGQ==");
		cartaoCreditoDadosDto.setValidadeAno(
				"jvBw0BkvEiYea/rBtjCzpP+/sa4Paha3SYGSeGdn7zDmwQqq65PH8fhIRNA9Sxhz+MrH4o+vuahhygGYpoPksk5rgd3EMGmwQHDOegasFabuW/SEr2ZrXQ4gLv+UTYY0MLgPX1D7OM/sBmdgQ/OdFT9mFvTpTRmAOWqmXM7I9TiG5MscVjnWInBHljmLCDL55JxWPqEYmis05RTaDDCFwhxob30ygrg6qfGTvVLUDKbNn6OGOiR6XMgajQWJySIrM1cW1wdKxElgqK1RxDXTBBdaTSTNf5nekMI16bRFzE0YUET5L28+OvySc5nQMta2FgykXxvY8o+fnb8ZxAF3FA==");
		cartaoCreditoDadosDto.setValidadeMes(
				"mDAyCqkh1mK1Mg/FuJCJ5jgjk4qC5mh/viINHvpXtqLLBTVtum8qZLtND9TkEXOdJNOSH+NCiDkvGWt2t3SuU+YfUi85WuIfR7UcAc8Zpo+n4T5upd5iz2y7H58cWu+4NqyZl35gpr8nlzKZXznIR8HIYkTD431GgBEGD3KouWxT+zUssmckSSgru7wEbI9aL9OE4cgMO0hFOciB5ZYGmZXM9iCYjqeXR1r6g08zMMFtjDJV4P9yKCy4vqxvyZ1Dt5MNBHOJ//549RQxMzj2h/Xl8JdMGrFEPnKBnAIP1yqqDP22aF8HyHsLkEPUVJwJz/mKlHw0itSzSfcEWOg/Sg==");
		cartaoCreditoDadosDto.setQuantidadeParcelas(1);

		pagamentoComplementarDto.setDadosCartaoCredito(cartaoCreditoDadosDto);

		// dados Cartao Credito Validacao
		CartaoCreditoDadosValidacaoDto cartaoCreditoDadosValidacaoDto = new CartaoCreditoDadosValidacaoDto();
		cartaoCreditoDadosValidacaoDto.setNome("Jose da Silva");
		cartaoCreditoDadosValidacaoDto.setNumeroMascarado("341235xxxxxx1885");
		cartaoCreditoDadosValidacaoDto.setQtCaracteresCodigoVerificador("4");
		cartaoCreditoDadosValidacaoDto.setValidadeAno("2025");
		cartaoCreditoDadosValidacaoDto.setValidadeMes("10");

		pagamentoComplementarDto.setDadosCartaoCreditoValidacao(cartaoCreditoDadosValidacaoDto);

		// pagamento complementar
		pagamentoComplementarDto.setValorComplementar(30.0);
		pagamentoComplementarDto.setValorComplementarComJuros(30.0);

		// dados entrega
		EntregaDadosDto dadosEntrega = new EntregaDadosDto();
		dadosEntrega.setValorFrete(15.90);

		// endereco cobranca
		EnderecoCobrancaDto enderecoCobranca = new EnderecoCobrancaDto();
		enderecoCobranca.setCep("01546090");
		enderecoCobranca.setEstado("SP");
		enderecoCobranca.setLogradouro("Rua Rodrigues Bastista");
		enderecoCobranca.setCidade("São Paulo");
		enderecoCobranca.setNumero(63);
		enderecoCobranca.setReferencia("teste");
		enderecoCobranca.setBairro("Vila Teodoro");
		enderecoCobranca.setComplemento("teste");
		enderecoCobranca.setTelefone("22333333");
		enderecoCobranca.setTelefone("22333335");
		enderecoCobranca.setTelefone("22333336");

		pedido.setProdutos(produtos);
		pedido.setEnderecoEntrega(enderecoEntrega);
		pedido.setDestinatario(destinatario);
		pedido.setDadosEntrega(dadosEntrega);
		pedido.setEnderecoCobranca(enderecoCobranca);
		pedido.setPagtosComplementares(Arrays.asList(pagamentoComplementarDto));
		pedido.setValorTotalPedido(135.80);
		pedido.setValorTotalComplementar(30.0);
		pedido.setValorTotalComplementarComJuros(30.0);

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
