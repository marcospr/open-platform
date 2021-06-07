package br.com.via.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.via.api.CampanhaAPI;
import br.com.via.api.client.ApiException;
import br.com.via.api.model.response.CampanhasDTO;
import br.com.via.api.model.response.OpcoesParcelamentoDTO;

public class CampanhaApiTest {
	private CampanhaAPI campanhaApi;
	
	@Before
	public void init() {
		campanhaApi = new CampanhaAPI();
	}
	
	@Test
	public void testGetCampanhaSucess() throws ApiException{
		try {
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", "2100-08-04");
			assertEquals("2019-10-17T17:00:00", campanhas.getData().get(0).getDataInicio());
			assertEquals("2100-02-08T00:00:00",campanhas.getData().get(0).getDataFim());
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
