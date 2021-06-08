package br.com.via.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.via.api.client.ApiException;
import br.com.via.api.model.response.ChaveDTO;

public class SegurancaApiTest {
	private SegurancaApi segurancaApi;
	
	@Before
	public void init() {
		segurancaApi = new SegurancaApi("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
	}
	
	@Test
	public void getChaveTestSucess() {
		try {
			ChaveDTO chave = segurancaApi.getChave();
			assertNotNull(chave);
			assertEquals("2018-11-28T13:56:06", chave.getData().getDataCadastro());
		} catch (ApiException e) {
			printErrorApi(e, "testgetChaveTestSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void getChaveTestFail() {
		try {
			ChaveDTO chave = segurancaApi.getChave();
		} catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaFail");
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
