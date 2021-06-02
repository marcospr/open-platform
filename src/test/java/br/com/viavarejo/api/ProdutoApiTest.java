package br.com.viavarejo.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.response.ProdutoDTO;

public class ProdutoApiTest {
	ProdutoAPI produtoApi;
	
	@Before
	public void init() {
		produtoApi = new ProdutoAPI();
	}
	
	@Test
	public void getDadosProdutoSucess(){
		
		try {
			ProdutoDTO dadosProduto = produtoApi.getDadosProduto("15", "5880205");
			assertNotNull(dadosProduto);
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getListaDadosProdutosSucess(){
		try {
			ProdutoDTO listaDadosProdutos = produtoApi.getListaDadosProdutos("15","5880205", "5880206");

		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getDadosProdutoCampanhaSucess(){
		try {
			ProdutoDTO dadosProdutoCampanha = produtoApi.getDadosProdutoCampanha(null, null, null, null);
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
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
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getListaDadosProdutosFail() throws ApiException{
		try {
			ProdutoDTO listaDadosProdutos = produtoApi.getListaDadosProdutos("15","5880205", "5880206");
			assertNotNull(listaDadosProdutos);
			assertEquals("NaoEncontrado", listaDadosProdutos.getError().getCode());
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void getDadosProdutoCampanhaFail() throws ApiException{
		try {
			ProdutoDTO dadosProdutoCampanha = produtoApi.getDadosProdutoCampanha(null, null, null, null);
		}catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
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
