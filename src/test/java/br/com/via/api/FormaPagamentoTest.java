package br.com.via.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.via.api.FormaPagamentoApi;
import br.com.via.api.client.ApiException;
import br.com.via.api.model.response.OpcoesParcelamentoDTO;

public class FormaPagamentoTest {
	FormaPagamentoApi pagamentoApi;
	
	@Before
	public void init() {
		pagamentoApi = new FormaPagamentoApi();
	}
	
	//SEM MASSA
	@Test
	public void getOpcoesParcelamentoSucess() throws ApiException{
		try {
			OpcoesParcelamentoDTO opcoesParcelamento = pagamentoApi.getOpcoesParcelamento("1", "5940", "57.822.975/0001-12", "1000");
			assertNotNull(opcoesParcelamento);
		}catch (ApiException e) {
			printErrorApi(e, "testGetOpcoesParcelamentoSucess");
			fail("Falha. Uma exceção ApiException não deveria ser lançada!");
		} catch (Exception e) {
			fail("Falha. Uma exceção não deveria ser lançada!\n" + e.getMessage());
		}
	}
	
	//ERRO FORA DO PADRÃO
	@Test
	@Ignore
	public void getOpcoesParcelamentoFailure() throws ApiException{
		try {
			OpcoesParcelamentoDTO opcoesParcelamento = pagamentoApi.getOpcoesParcelamento("8", "5940", "57.822.975/0001-12", "1000");
			assertNotNull(opcoesParcelamento);
		}catch (ApiException e) {
			printErrorApi(e, "testGetOpcoesParcelamentoSucess");
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
