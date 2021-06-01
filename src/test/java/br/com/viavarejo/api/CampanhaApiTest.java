package br.com.viavarejo.api;

import static org.junit.Assert.assertEquals;

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
		campanhaApi = new CampanhaAPI();
	}
	
	@Test
	public void testGetCampanhaSucess() throws ApiException{
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", "2100-08-04");
			assertEquals("2019-10-17T17:00:00", campanhas.getData().get(0).getDataInicio());
			assertEquals("2100-02-08T00:00:00",campanhas.getData().get(0).getDataFim());
	}
	
	@Test
	public void testGetCampanhaFail() throws ApiException{
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", null);
			Assert.assertNull(campanhas.getData());
			assertEquals("400", campanhas.getError().getCode());
	}
	
	@Test
	@Ignore
	public void testGetOpcoesParcelamento() throws ApiException{
		OpcoesParcelamentoDTO opcoesParcelamento = campanhaApi.getOpcoesParcelamento(null, null);
		//sem massa para teste
	}
	
	

}
