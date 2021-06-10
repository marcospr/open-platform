package br.com.via.api;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
import br.com.via.api.security.Encryptor;

/**
 * Classe de testes para as URI's dos Pedidos do B2B.</br>
 * É importante que os metodos sejam executados na ordem estabelecida, pois</br>
 * alguns metodos de testes possuem dependencia dos resultados dos anteriores.
 * 
 * @author Marcos Pinheiro da Rocha
 *
 */
@TestMethodOrder(OrderAnnotation.class)
class PedidoApiTest {

	/** Instancia do client API. */
	private static PedidoApi pedidoApi;

	/** Token. */
	private static final String AUTHORIZATION_TOKEN = "H9xO4+R8GUy+18nUCgPOlg==";

	/** Host do servico do Extra. */
	private static final String HOST_EXTRA = "http://api-integracao-extra.hlg-b2b.net";

	/** Host do servico das Casas Bahia. */
	// private static final String HOST_CASAS_BAHIA = "";

	/** Host do servico do Ponto Frio. */
	// private static final String HOST_PONTO = "";

	/** CEP padrao dos testes */
	private static final String CEP = "01525000";

	/** Id Lojista padrao dos testes. */
	private static final int ID_LOJISTA = 15;

	/** CNPJ padrao dos testes. */
	private static final String CNPJ = "57.822.975/0001-12";

	/** Id Campanha padrao dos testes. */
	private static final int ID_CAMPANHA = 5940;

	/** Atributo do Id Sku para criacao do primeiro Pedido. */
	private static final Integer ID_SKU_CRIACAO_PEDIDO = 8935731;

	/** Atributo do Id Sku para criacao do segundo Pedido com cartao de credito. */
	private static final Integer ID_SKU_CRIACAO_PEDIDO_COM_CARTAO = 9342200;

	/**
	 * Atributo global utilizado para guardar o primeiro pedido criado para ser
	 * utilizado nos demais testes.
	 */
	private static DadosPedido pedidoGeral;

	/**
	 * Atributo global utilizado para guardar o segundo pedido criado com Cartao
	 * Credito para ser utilizado nos demais testes.
	 */
	private static DadosPedido pedidoGeralComCartao;

	/**
	 * Chave pública 2048 bits utilizada para criptografia dos dados do cartão.</br>
	 * Pode ser obtida pelo endpoint Rest abaixo.
	 * 
	 * @see http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Seguranca/Seguranca_ObterChave
	 * 
	 */
	private static final String CHAVE_PUBLICA = "MIIENTCCAx2gAwIBAgIJAJ5ApEGl2oaIMA0GCSqGSIb3DQEBBQUAMIGwMQswCQYDVQQGEwJCUjELMAkGA1UECAwCU1AxFDASBgNVBAcMC1NBTyBDQUVUQU5PMRMwEQYDVQQKDApWSUEgVkFSRUpPMSAwHgYDVQQLDBdTRUdVUkFOQ0EgREEgSU5GT1JNQUNBTzEOMAwGA1UEAwwFUFJPWFkxNzA1BgkqhkiG9w0BCQEWKHRpLnNlZ3VyYW5jYS5pbmZvcm1hY2FvQHZpYXZhcmVqby5jb20uYnIwHhcNMTgwODE2MTIzNjQ2WhcNMjEwODE1MTIzNjQ2WjCBsDELMAkGA1UEBhMCQlIxCzAJBgNVBAgMAlNQMRQwEgYDVQQHDAtTQU8gQ0FFVEFOTzETMBEGA1UECgwKVklBIFZBUkVKTzEgMB4GA1UECwwXU0VHVVJBTkNBIERBIElORk9STUFDQU8xDjAMBgNVBAMMBVBST1hZMTcwNQYJKoZIhvcNAQkBFih0aS5zZWd1cmFuY2EuaW5mb3JtYWNhb0B2aWF2YXJlam8uY29tLmJyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqObNb7KAP09WsV9h76Dw3tj2qa3l97K+slfzLkOBvi0xjacuKCnvsMSGEBosvWY/qNmSLE1YaoyFt7ZaeOiALKh2AFckJRM+/zvQzqi6cPnW0cGsEE/9WO48Fgh894pKjHpukATFb9tBYGTBEW46AH2WiAR735KEnDfFAHG//pkLKriPWEZBr9tf4gdNvyJ/ybs5JrBRU1RKE9MM7qnMkCouKTPwY/lS/2Xb1IYkyZulCf3Uyl7zpB6hQUhprS1R5meRocpGgHJCFfiWD/uXa5nREuGuQxcImwzvf+enwT6CooRoM2rN6IQWSY+uQ64dhSt4FMajZFmHVpLfUIOjEwIDAQABo1AwTjAdBgNVHQ4EFgQUZ22K62aMm/lI5LfblgINPvz8ae8wHwYDVR0jBBgwFoAUZ22K62aMm/lI5LfblgINPvz8ae8wDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQUFAAOCAQEAj23IDXLPkQpFDbgAtgKuO9N66o61edbJ1+BMjdSsfO0vMVpmBDlKdinxlh509/qJm/WLYswKkKOi7VHojBSV5HyrO5YGCSJFvVGJqF4JUxy7GrWTHqgwcylmX5B5lNd5aMIxwG6AF4o2cp6IPe+Uwaroa8kLTrtM0eRgAInHbQA7MXbvOZY+pzE4s6jFbA1O321zVg4C4Y3C4e30yf9YJNK5XjUP26duvwGqQrZg49ZU3W/t6GYY1kQhSeBG0FPg2GOIHX03WPZpaJ7i1uCv6Ial07pxDxqcT8oCJalY9tW9sv7zBJRaJgTIf5oz5jElb9kWd2D6XwaGB5PJfD6CTQ==";

	/** Atributo auxiliar para os testes de criacao de pedido. */
	private static DadosCartaoHelper dadosCartaoHelper;

	@BeforeAll
	public static void init() {
		pedidoApi = new PedidoApi(HOST_EXTRA, AUTHORIZATION_TOKEN);
		dadosCartaoHelper = new DadosCartaoHelper(new Encryptor(CHAVE_PUBLICA), "Jose da Silva", "5155901222280001",
				"1234", "2045", "12");
	}

	@Test
	@Order(1)
	void testPostCalcularCarrinhoParaCriacaoPedido() {
		Produtos produto = new Produtos();
		produto.setCodigo(ID_SKU_CRIACAO_PEDIDO);
		produto.setQuantidade(1);
		produto.setIdLojista(ID_LOJISTA);

		PedidoCarrinho pedidoCarrinho = new PedidoCarrinho();

		pedidoCarrinho.setIdCampanha(ID_CAMPANHA);
		pedidoCarrinho.setCnpj(CNPJ);
		pedidoCarrinho.setCep(CEP);
		pedidoCarrinho.setProdutos(Arrays.asList(produto));

		CalculoCarrinho calculoCarrinho;
		try {
			calculoCarrinho = pedidoApi.postCalcularCarrinho(pedidoCarrinho);
			Assert.assertTrue(calculoCarrinho.getData().getProdutos().get(0).getValorTotalFrete().doubleValue() > 0.0);

			// preparacao do objeto que sera utilizado nos demais testes
			pedidoGeral = preparePedido(calculoCarrinho);

		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCalcularCarrinho"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	@Order(2)
	void testPostCalcularCarrinhoParaCriacaoPedidoComCartao() {
		Produtos produto = new Produtos();
		produto.setCodigo(ID_SKU_CRIACAO_PEDIDO_COM_CARTAO);
		produto.setQuantidade(1);
		produto.setIdLojista(ID_LOJISTA);

		PedidoCarrinho pedidoCarrinho = new PedidoCarrinho();

		pedidoCarrinho.setIdCampanha(ID_CAMPANHA);
		pedidoCarrinho.setCnpj(CNPJ);
		pedidoCarrinho.setCep(CEP);
		pedidoCarrinho.setProdutos(Arrays.asList(produto));

		CalculoCarrinho calculoCarrinho;
		try {
			calculoCarrinho = pedidoApi.postCalcularCarrinho(pedidoCarrinho);
			Assert.assertTrue(calculoCarrinho.getData().getProdutos().get(0).getValorTotalFrete().doubleValue() > 0.0);

			// preparacao do objeto que sera utilizado nos demais testes
			pedidoGeralComCartao = preparePedido(calculoCarrinho);

		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCalcularCarrinhoParaCriacaoPedidoComCartao"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	@Order(3)
	void testPostCriarPedido() {
		// Produto
		PedidoProdutoDto produto = new PedidoProdutoDto();
		produto.setIdLojista(ID_LOJISTA);
		produto.setCodigo(pedidoGeral.getIdSku());
		produto.setQuantidade(1);
		produto.setPremio(0);
		produto.setPrecoVenda(pedidoGeral.getPrecoVenda());
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
		dadosEntrega.setValorFrete(pedidoGeral.valorFrete);

		// pedido
		CriacaoPedidoRequest pedido = new CriacaoPedidoRequest();
		pedido.setProdutos(produtos);
		pedido.setEnderecoEntrega(enderecoEntrega);
		pedido.setDestinatario(destinatario);
		pedido.setDadosEntrega(dadosEntrega);
		pedido.setCampanha(ID_CAMPANHA);
		pedido.setCnpj(CNPJ);
		int idPedidoParceiro = geraPedidoParceiroId();
		pedido.setPedidoParceiro(idPedidoParceiro);
		pedido.setValorFrete(pedidoGeral.getValorFrete());
		pedido.setAguardarConfirmacao(true);
		pedido.setOptantePeloSimples(true);

		CriacaoPedidoDTO criacaoPedidoDTO;
		try {
			criacaoPedidoDTO = pedidoApi.postCriarPedido(pedido);
			double expectedValue = pedidoGeral.getTotalPedido();
			Assert.assertEquals(expectedValue, criacaoPedidoDTO.getData().getValorTotalPedido(), 0.01);

			// complementa dados do Pedido para utilizar nos outros metodos
			pedidoGeral.setIdPedido(criacaoPedidoDTO.getData().getCodigoPedido());
			pedidoGeral.setIdPedidoParceiro(criacaoPedidoDTO.getData().getPedidoParceiro());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedido"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	@Order(4)
	void testPostCriarPedidoPagCartao() {
		// Produto
		PedidoProdutoDto produto = new PedidoProdutoDto();
		produto.setIdLojista(ID_LOJISTA);
		produto.setCodigo(pedidoGeralComCartao.getIdSku());
		produto.setQuantidade(1);
		produto.setPrecoVenda(pedidoGeralComCartao.getPrecoVenda());
		List<PedidoProdutoDto> produtos = Arrays.asList(produto);

		// endereco Entrega
		EnderecoEntregaDto enderecoEntrega = new EnderecoEntregaDto();
		enderecoEntrega.setCep(CEP);
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
		int idPedidoParceiro = geraPedidoParceiroId();
		pedido.setCampanha(ID_CAMPANHA);
		pedido.setCnpj(CNPJ);
		pedido.setPedidoParceiro(idPedidoParceiro);
		pedido.setValorFrete(pedidoGeralComCartao.getValorFrete());
		pedido.setAguardarConfirmacao(true);
		pedido.setOptantePeloSimples(true);
		pedido.setPossuiPagtoComplementar(true);

		// pagamentos complementares
		PagamentoComplementarDto pagamentoComplementarDto = new PagamentoComplementarDto();
		pagamentoComplementarDto.setIdFormaPagamento(2);

		// dados cartao credito
		CartaoCreditoDadosDto cartaoCreditoDadosDto = new CartaoCreditoDadosDto();

		String encryptedName = dadosCartaoHelper.getEncryptedName();
		String encryptedNumber = dadosCartaoHelper.getEncryptedNumber();
		String encryptedVerifyCode = dadosCartaoHelper.getEncryptedVerifyCode();
		String encryptedValidateYear = dadosCartaoHelper.getEncryptedValidateYear();
		String encryptedValidateMonth = dadosCartaoHelper.getEncryptedValidateMonth();

		cartaoCreditoDadosDto.setNome(encryptedName);
		cartaoCreditoDadosDto.setNumero(encryptedNumber);
		cartaoCreditoDadosDto.setCodigoVerificador(encryptedVerifyCode);
		cartaoCreditoDadosDto.setValidadeAno(encryptedValidateYear);
		cartaoCreditoDadosDto.setValidadeMes(encryptedValidateMonth);

		/*
		 * cartaoCreditoDadosDto.setNome(
		 * "qrTvioqjqt2lLiAkzp8ERtKAIglq9PI9FyDh8eVtuoWZvtVFRHKBzidIxCCELAluS+65QpyO1u4kTruGMyzyj++QtJT9MI0si0gFy9euz4GvCz6sl8+yx3GnJ5u6H9D3O3DugfWM4Gl3Lq6CPjT+qSjrYKul7qKkAR395rXlKLgxv3ENtSqMe+ttE60/yDmGVboP6d/wG7Wr5IDwzWUz6VD6nw3aU584rJ4B6jUwnAENA12xKPGOpI/XlcrbkpJw5Gz4XBveWkB/C+m4fY1cLT6LRJXXAJx9h2flvtSA8J1XPvDdbsKX9IhIYs7H88Z/t3LTTmTjEVcxeE56kA1feg=="
		 * ); cartaoCreditoDadosDto.setNumero(
		 * "bsDiir5JMhpB3RCGu5IVF2FvvBMq+eoyva/T71B5HGff7/x0Wz9y3Q98UBWVJ9z8jm/y7TnqxrP7PFGbBwlVEg09aQbSpOCMGpe7TBgjPUH6mteioKVA3bDeBhOURtoqRnzMoXYFbMV1xsaMJBZxxCAZkywdN4h+G2np5/OlRxYXhKgNIvUMUWgtL2zsN0EOnVEajtoO3K+A4CpmO480SqhlggKo6U64464ZaIYxLRb9ajONiaLNtZLYK4WorclAnQDQWOMUP8w75zd/Y/DF8S/pGflAy8ElCFbd0Kuramxb9IVPxIXosZa4C+PSvplht6tjgBKfHk83NzaQxxiVnA=="
		 * ); cartaoCreditoDadosDto.setCodigoVerificador(
		 * "NPFWj2Fo5jazlTV6O/ouNkTOU6h+/RSP2d0fvVryXtOm4PliK1MQT6TdaEKEdw1fkTg2Zzt0tr2qFgZvCq1nKewsS4SfRAUBLCQEJSF0JkzS42PV2HIEt8zTq2Y/iIT2AOowrM0NPztcFOmsuNvoIqt5GbqMpNpalRZrN9gHsjeDPjwdUrnI+3vUL8w1g9YQYgGUG+9P6ZzPrZsz0YQN4znchaU34hnSW6ojl83pu2xMn/HfCA3wGOJM3DWT7eDfplbOYOWWjzUGz3/HVI/bpv0H/4hyHimLK6eZP5Wa7EqBKxB0B3G9bbDMBzujZluQ3wemEhgfOX/LVi91l7NIGQ=="
		 * ); cartaoCreditoDadosDto.setValidadeAno(
		 * "jvBw0BkvEiYea/rBtjCzpP+/sa4Paha3SYGSeGdn7zDmwQqq65PH8fhIRNA9Sxhz+MrH4o+vuahhygGYpoPksk5rgd3EMGmwQHDOegasFabuW/SEr2ZrXQ4gLv+UTYY0MLgPX1D7OM/sBmdgQ/OdFT9mFvTpTRmAOWqmXM7I9TiG5MscVjnWInBHljmLCDL55JxWPqEYmis05RTaDDCFwhxob30ygrg6qfGTvVLUDKbNn6OGOiR6XMgajQWJySIrM1cW1wdKxElgqK1RxDXTBBdaTSTNf5nekMI16bRFzE0YUET5L28+OvySc5nQMta2FgykXxvY8o+fnb8ZxAF3FA=="
		 * ); cartaoCreditoDadosDto.setValidadeMes(
		 * "mDAyCqkh1mK1Mg/FuJCJ5jgjk4qC5mh/viINHvpXtqLLBTVtum8qZLtND9TkEXOdJNOSH+NCiDkvGWt2t3SuU+YfUi85WuIfR7UcAc8Zpo+n4T5upd5iz2y7H58cWu+4NqyZl35gpr8nlzKZXznIR8HIYkTD431GgBEGD3KouWxT+zUssmckSSgru7wEbI9aL9OE4cgMO0hFOciB5ZYGmZXM9iCYjqeXR1r6g08zMMFtjDJV4P9yKCy4vqxvyZ1Dt5MNBHOJ//549RQxMzj2h/Xl8JdMGrFEPnKBnAIP1yqqDP22aF8HyHsLkEPUVJwJz/mKlHw0itSzSfcEWOg/Sg=="
		 * );
		 */

		cartaoCreditoDadosDto.setQuantidadeParcelas(1);

		pagamentoComplementarDto.setDadosCartaoCredito(cartaoCreditoDadosDto);

		// dados Cartao Credito Validacao
		CartaoCreditoDadosValidacaoDto cartaoCreditoDadosValidacaoDto = new CartaoCreditoDadosValidacaoDto();
		cartaoCreditoDadosValidacaoDto.setNome(dadosCartaoHelper.getNome());
		cartaoCreditoDadosValidacaoDto.setNumeroMascarado("341235xxxxxx1885");
		cartaoCreditoDadosValidacaoDto.setQtCaracteresCodigoVerificador("4");
		cartaoCreditoDadosValidacaoDto.setValidadeAno(dadosCartaoHelper.getAnoValidade());
		cartaoCreditoDadosValidacaoDto.setValidadeMes(dadosCartaoHelper.getMesValidade());
		/*
		 * cartaoCreditoDadosValidacaoDto.setNome("Jose da Silva");
		 * cartaoCreditoDadosValidacaoDto.setNumeroMascarado("341235xxxxxx1885");
		 * cartaoCreditoDadosValidacaoDto.setQtCaracteresCodigoVerificador("4");
		 * cartaoCreditoDadosValidacaoDto.setValidadeAno("2025");
		 * cartaoCreditoDadosValidacaoDto.setValidadeMes("10");
		 */

		pagamentoComplementarDto.setDadosCartaoCreditoValidacao(cartaoCreditoDadosValidacaoDto);

		// pagamento complementar
		pagamentoComplementarDto.setValorComplementar(30.0);
		pagamentoComplementarDto.setValorComplementarComJuros(30.0);

		// dados entrega
		EntregaDadosDto dadosEntrega = new EntregaDadosDto();
		dadosEntrega.setValorFrete(pedidoGeralComCartao.getValorFrete());

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
		pedido.setValorTotalPedido(pedidoGeralComCartao.getTotalPedido());
		pedido.setValorTotalComplementar(30.0);
		pedido.setValorTotalComplementarComJuros(30.0);

		CriacaoPedidoDTO criacaoPedidoDTO;
		try {
			criacaoPedidoDTO = pedidoApi.postCriarPedido(pedido);
			double valueExpected = pedidoGeralComCartao.getTotalPedido();
			Assert.assertEquals(valueExpected, criacaoPedidoDTO.getData().getValorTotalPedido(), 0);

			// complementa dados do Pedido para utilizar nos outros metodos
			pedidoGeralComCartao.setIdPedido(criacaoPedidoDTO.getData().getCodigoPedido());
			pedidoGeralComCartao.setIdPedidoParceiro(criacaoPedidoDTO.getData().getPedidoParceiro());

		} catch (ApiException e) {
			fail(printErrorApi(e, "testPostCriarPedidoPagCartao"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	@Order(5)
	void testPatchPedidosCancelamento() {
		Map<String, String> variableParams = new HashMap<>();
		variableParams.put("idCompra", pedidoGeral.getIdPedido().toString());

		ConfirmacaoReqDTO dto = new ConfirmacaoReqDTO();
		dto.setIdCampanha(ID_CAMPANHA);
		dto.setIdPedidoParceiro(pedidoGeral.getIdPedidoParceiro());
		dto.setCancelado(true);
		dto.setConfirmado(false);
		dto.setIdPedidoMktplc("1-01");
		dto.setMotivoCancelamento("teste");
		dto.setParceiro("BANCO INTER");

		ConfirmacaoDTO confirmacaoDto;
		try {
			confirmacaoDto = pedidoApi.patchPedidosCancelamentoOrConfirmacao(dto, variableParams);
			Assert.assertTrue(confirmacaoDto.getData().getPedidoCancelado());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPatchPedidosCancelamento"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	@Order(6)
	void testPatchPedidosConfirmacao() {
		Map<String, String> variableParams = new HashMap<>();
		variableParams.put("idCompra", pedidoGeralComCartao.getIdPedido().toString());

		ConfirmacaoReqDTO dto = new ConfirmacaoReqDTO();
		dto.setIdCampanha(ID_CAMPANHA);
		dto.setIdPedidoParceiro(pedidoGeralComCartao.getIdPedidoParceiro());
		dto.setConfirmado(true);

		ConfirmacaoDTO confirmacaoDto;
		try {
			confirmacaoDto = pedidoApi.patchPedidosCancelamentoOrConfirmacao(dto, variableParams);
			Assert.assertTrue(confirmacaoDto.getData().getPedidoConfirmado());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testPatchPedidosConfirmacao"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	@Order(7)
	void testGetDadosPedidoParceiro() {
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("idCompra", pedidoGeral.getIdPedido().toString());

		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("request.idCompra", pedidoGeral.getIdPedido().toString());
		queryParams.put("request.cnpj", CNPJ);
		queryParams.put("request.idCampanha", String.valueOf(ID_CAMPANHA));
		queryParams.put("request.idPedidoParceiro", pedidoGeral.getIdPedidoParceiro().toString());

		PedidoParceiroData pedido;
		try {
			pedido = pedidoApi.getDadosPedidoParceiro(pathParams, queryParams);
			Assert.assertEquals(pedidoGeral.getIdPedido().intValue(), pedido.getData().getPedido().getCodigoPedido());
		} catch (ApiException e) {
			fail(printErrorApi(e, "testGetDadosPedidoParceiro"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	@Test
	@Order(8)
	void testGetNotaFiscalPedidoPdf() {
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("idCompra", "247473612");
		pathParams.put("idCompraEntrega", "91712686");
		pathParams.put("formato", "PDF");

		String response;
		try {
			response = pedidoApi.getNotaFiscalPedido(pathParams);
			Assert.assertNotNull("Response nulo", response);
		} catch (ApiException e) {
			fail(printErrorApi(e, "testGetNotaFiscalPedidoPdf"));
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}

	private String printErrorApi(ApiException e, String method) {
		return String.format(
				"Falha. Uma exceção ApiException não deveria ser lançada!\nApiException %s \nCode: %s \nMessage: %s \nBody: %s \nHeaders: %s",
				method, e.getCode(), e.getMessage(), e.getResponseBody(), e.getResponseHeaders());
	}

	private DadosPedido preparePedido(CalculoCarrinho calculoCarrinho) {
		DadosPedido dadosProduto = new DadosPedido();
		dadosProduto.setIdSku(calculoCarrinho.getData().getProdutos().get(0).getIdSku());
		dadosProduto.setPrecoVenda(calculoCarrinho.getData().getProdutos().get(0).getValorUnitario());
		dadosProduto.setValorFrete(calculoCarrinho.getData().getProdutos().get(0).getValorTotalFrete());
		return dadosProduto;
	}

	private int geraPedidoParceiroId() {
		int idPedidoParceiro = new Random().nextInt(65536);
		idPedidoParceiro = idPedidoParceiro < 0 ? idPedidoParceiro * -1 : idPedidoParceiro;
		return idPedidoParceiro;
	}

	/**
	 * Classe interna utilizada para guardar os dados os pedidos gerados para serem
	 * utilizados nos outros metodos dependentes.
	 * 
	 * @author Marcos
	 *
	 */
	private static class DadosPedido {
		private Integer idPedido;
		private Integer idPedidoParceiro;
		private Integer idSku;
		private double valorFrete;
		private double precoVenda;

		public double getTotalPedido() {
			return valorFrete + precoVenda;
		}

		public void setIdSku(Integer idSku) {
			this.idSku = idSku;
		}

		public Integer getIdSku() {
			return idSku;
		}

		public void setValorFrete(double valorFrete) {
			this.valorFrete = valorFrete;
		}

		public double getValorFrete() {
			return valorFrete;
		}

		public void setPrecoVenda(double precoVenda) {
			this.precoVenda = precoVenda;
		}

		public double getPrecoVenda() {
			return precoVenda;
		}

		public Integer getIdPedido() {
			return idPedido;
		}

		public void setIdPedido(Integer idPedido) {
			this.idPedido = idPedido;
		}

		public void setIdPedidoParceiro(Integer idPedidoParceiro) {
			this.idPedidoParceiro = idPedidoParceiro;
		}

		public Integer getIdPedidoParceiro() {
			return idPedidoParceiro;
		}

	}

	/**
	 * Classe para dados do cartao credito.
	 * 
	 * @author Marcos P. da Rocha
	 *
	 */
	private static class DadosCartaoHelper {

		private Encryptor encryptor;
		private String nome;
		private String numero;
		private String codigoVerificador;
		private String anoValidade;
		private String mesValidade;

		public DadosCartaoHelper(Encryptor encryptor, String nome, String numero, String codigoVerificador,
				String anoValidade,
				String mesValidade) {
			this.encryptor = encryptor;
			this.nome = nome;
			this.numero = numero;
			this.codigoVerificador = codigoVerificador;
			this.anoValidade = anoValidade;
			this.mesValidade = mesValidade;
		}

		public String getEncryptedName() {
			return encryptor.encript(nome);
		}

		public String getEncryptedNumber() {
			return encryptor.encript(numero);
		}

		public String getEncryptedVerifyCode() {
			return encryptor.encript(codigoVerificador);
		}

		public String getEncryptedValidateYear() {
			return encryptor.encript(anoValidade);
		}

		public String getEncryptedValidateMonth() {
			return encryptor.encript(mesValidade);
		}

		public String getNome() {
			return nome;
		}

		public String getNumero() {
			return numero;
		}

		public String getCodigoVerificador() {
			return codigoVerificador;
		}

		public String getAnoValidade() {
			return anoValidade;
		}

		public String getMesValidade() {
			return mesValidade;
		}

	}
}
