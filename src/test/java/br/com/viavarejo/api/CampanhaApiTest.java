package br.com.viavarejo.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.response.CampanhasDTO;
import br.com.viavarejo.api.model.response.OpcoesParcelamentoDTO;

public class CampanhaApiTest {
	private CampanhaAPI campanhaApi;
	
	@Before
	public void init() {
		campanhaApi = new CampanhaAPI("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
	}
	
	@Test
	public void testGetCampanhaSucess() throws ApiException{
		try {
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", "2100-08-04");
			assertEquals("57.822.975/0001-12", campanhas.getData().get(0).getCnpjContrato());
		} catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	@Test
	public void testGetCampanhaFail() throws ApiException{
		try {
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", null);
			Assert.assertNull(campanhas.getData());
			assertEquals("400", campanhas.getError().getCode());
		} catch (ApiException e) {
			printErrorApi(e, "testGetCampanhaFail");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	//sem massa para teste
	@Test
	@Ignore
	public void testGetOpcoesParcelamento() throws ApiException{
		OpcoesParcelamentoDTO opcoesParcelamento = campanhaApi.getOpcoesParcelamento(null, null);
	}
	
	private void printErrorApi(ApiException e, String method) {
		System.out.println(String.format("ApiException %s \nCode: %s \nMessage: %s \nBody: %s \nHeaders: %s", method,
				e.getCode(), e.getMessage(), e.getResponseBody(), e.getResponseHeaders()));
	}
	

}
