package br.com.viavarejo.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.response.OpcoesParcelamentoDTO;

public class FormaPagamentoTest {
	FormaPagamentoApi pagamentoApi;
	
	@Before
	public void init() {
		pagamentoApi = new FormaPagamentoApi();
		
	}
	
	@Test
	public void getOpcoesParcelamentoSucess() throws ApiException{
		OpcoesParcelamentoDTO opcoesParcelamento = pagamentoApi.getOpcoesParcelamento("1", "5940", "57.822.975/0001-12", "1000");
		assertNotNull(opcoesParcelamento);
	}
	
	@Test
	@Ignore
	public void getOpcoesParcelamentoFailure() throws ApiException{
		OpcoesParcelamentoDTO opcoesParcelamento = pagamentoApi.getOpcoesParcelamento("8", "5940", "57.822.975/0001-12", "1000");
		assertNotNull(opcoesParcelamento);
	}

}
