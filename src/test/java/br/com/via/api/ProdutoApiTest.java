package br.com.via.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


import br.com.via.api.ProdutoAPI;
import br.com.via.api.client.ApiException;
import br.com.via.api.model.response.ProdutoDTO;
import br.com.viavarejo.api.model.response.ProdutosDTO;


public class ProdutoApiTest {
	ProdutoAPI produtoApi;
	
	@Before
	public void init() {
		produtoApi = new ProdutoAPI("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
	}
	
	@Test
	public void getDadosProdutoSucess(){
		
		try {
			ProdutoDTO dadosProduto = produtoApi.getDadosProduto("15", "5880205");
			assertNotNull(dadosProduto);
			assertEquals("Bola de Natal Santini Christmas 10cm Transparente - 3 Unidades.", dadosProduto.getData().getNome());
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exce��o ApiException n�o deveria ser lan�ada!");
		} catch (Exception e) {
			fail("Falha. Uma exce��o n�o deveria ser lan�ada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getListaDadosProdutosSucess(){
		try {
			ProdutosDTO listaDadosProdutos = produtoApi.getListaDadosProdutos("15","5880205", "5880206");
			assertNotNull(listaDadosProdutos);
			assertEquals(listaDadosProdutos.getData().get(0).getImagem(), "http://imagens.extra.com.br/Control/ArquivoExibir.aspx?IdArquivo=253172122");
		}catch (ApiException e) {
			printErrorApi(e, "getListaDadosProdutosSucess");
			fail("Falha. Uma exce��o ApiException n�o deveria ser lan�ada!");
		} catch (Exception e) {
			fail("Falha. Uma exce��o n�o deveria ser lan�ada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getDadosProdutoCampanhaSucess(){
		try {
			ProdutoDTO dadosProdutoCampanha = produtoApi.getDadosProdutoCampanha(null, null, null, null);
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exce��o ApiException n�o deveria ser lan�ada!");
		} catch (Exception e) {
			fail("Falha. Uma exce��o n�o deveria ser lan�ada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getDadosProdutoFail() throws ApiException{
		try {
			ProdutoDTO dadosProduto = produtoApi.getDadosProduto("15", "5880205");
			assertNotNull(dadosProduto);
			assertEquals("ProdutoNaoEncontrado", dadosProduto.getError().getCode());
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exce��o ApiException n�o deveria ser lan�ada!");
		} catch (Exception e) {
			fail("Falha. Uma exce��o n�o deveria ser lan�ada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getListaDadosProdutosFail() throws ApiException{
		try {
			ProdutosDTO listaDadosProdutos = produtoApi.getListaDadosProdutos("15","5880205", "5880206");
			assertNotNull(listaDadosProdutos);
			assertEquals("NaoEncontrado", listaDadosProdutos.getError().getCode());
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exce��o ApiException n�o deveria ser lan�ada!");
		} catch (Exception e) {
			fail("Falha. Uma exce��o n�o deveria ser lan�ada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getDadosProdutoCampanhaFail() throws ApiException{
		try {
			ProdutoDTO dadosProdutoCampanha = produtoApi.getDadosProdutoCampanha(null, null, null, null);
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exce��o ApiException n�o deveria ser lan�ada!");
		} catch (Exception e) {
			fail("Falha. Uma exce��o n�o deveria ser lan�ada!\n" + e.getMessage());
		}
	}
	
	private void printErrorApi(ApiException e, String method) {
		System.out.println(String.format("ApiException %s \nCode: %s \nMessage: %s \nBody: %s \nHeaders: %s", method,
				e.getCode(), e.getMessage(), e.getResponseBody(), e.getResponseHeaders()));
	}
	
	

}