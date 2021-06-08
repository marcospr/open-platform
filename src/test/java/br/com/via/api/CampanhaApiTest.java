package br.com.via.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.via.api.client.ApiException;
import br.com.via.api.model.response.CampanhasDTO;
import br.com.via.api.model.response.OpcoesParcelamentoDTO;

public class CampanhaApiTest {
	private CampanhaApi campanhaApi;
	
	@Before
	public void init() {
		campanhaApi = new CampanhaApi("http://api-integracao-extra.hlg-b2b.net", "H9xO4+R8GUy+18nUCgPOlg==");
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
	
	@Test(expected = ApiException.class)
	public void testGetCampanhaFail() throws ApiException{
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", null);
			Assert.assertNull(campanhas.getData());
			assertEquals("400", campanhas.getError().getCode());
	}
	

	@Test
	public void testGetOpcoesParcelamentoSucess() throws ApiException{
		OpcoesParcelamentoDTO opcoesParcelamento = campanhaApi.getOpcoesParcelamento("5940", "57.822.975/0001-12");
		assertNotNull(opcoesParcelamento);
		assertEquals(new Integer(1), opcoesParcelamento.getData().get(0).getIdFormaPagamento());
	}
	
	//erro fora do padrão
	@Test
	public void testGetOpcoesParcelamentoFail() throws ApiException{
		OpcoesParcelamentoDTO opcoesParcelamento = campanhaApi.getOpcoesParcelamento("590", "57.822.97-12");
		assertNotNull(opcoesParcelamento);
		assertTrue(opcoesParcelamento.getData().isEmpty());
	}
	
	
	
	private void printErrorApi(ApiException e, String method) {
		System.out.println(String.format("ApiException %s \nCode: %s \nMessage: %s \nBody: %s \nHeaders: %s", method,
				e.getCode(), e.getMessage(), e.getResponseBody(), e.getResponseHeaders()));
	}
	

}
