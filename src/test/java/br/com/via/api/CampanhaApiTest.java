package br.com.via.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.via.api.client.ApiException;
import br.com.via.api.model.response.CampanhasDTO;
import br.com.via.api.model.response.OpcoesParcelamentoDTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.junit.Assert.*;

public class CampanhaApiTest {
	private static CampanhaApi campanhaApi;
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@BeforeAll
	public static void init() {
		campanhaApi = new CampanhaApi();
	}
	
	@Test
	public void testGetCampanhaSucess() throws ApiException{
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", "2100-08-04");
			System.out.println("\nResponse:");
			System.out.println(gson.toJson(campanhas));
			assertEquals("57.822.975/0001-12", campanhas.getData().get(0).getCnpjContrato());
	}
	
	@Test
	public void testGetCampanhaFail() {
		Assertions.assertThrows(ApiException.class, () -> {
			CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", null);
			assertNull(campanhas.getData());
			assertEquals("400", campanhas.getError().getCode());
			assertEquals("Request inválido\\r\\nA dataFim é um parâmetro obrigatório.", campanhas.getError().getMessage());
			});
	}
	

	@Test
	public void testGetOpcoesParcelamentoSucess() throws ApiException{
		OpcoesParcelamentoDTO opcoesParcelamento = campanhaApi.getOpcoesParcelamento("5940", "57.822.975/0001-12");
		System.out.println("\nResponse:");
		System.out.println(gson.toJson(opcoesParcelamento));
		assertNotNull(opcoesParcelamento);
		assertEquals(new Integer(1), opcoesParcelamento.getData().get(0).getIdFormaPagamento());
	}

	// erro fora do padrão
	@Test
	public void testGetOpcoesParcelamentoFail() {
		Assertions.assertThrows(ApiException.class, () -> {
			OpcoesParcelamentoDTO opcoesParcelamento = campanhaApi.getOpcoesParcelamento("590", "57.822.97-12");
			System.out.println(gson.toJson(opcoesParcelamento));
			assertNotNull(opcoesParcelamento);
			assertTrue(opcoesParcelamento.getData().isEmpty());
			assertTrue(opcoesParcelamento.getError().getCode() == null);
		});
	}
	
	
	
	private void printErrorApi(ApiException e, String method) {
		System.out.println(String.format("ApiException %s \nCode: %s \nMessage: %s \nBody: %s \nHeaders: %s", method,
				e.getCode(), e.getMessage(), e.getResponseBody(), e.getResponseHeaders()));
	}
	

}
