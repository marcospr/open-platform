package br.com.viavarejo.api;


import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.response.CampanhasDTO;
import br.com.viavarejo.api.model.response.OpcoesParcelamentoDTO;

public class CampanhaTeste {

	public static void main(String[] args) {
		CampanhaAPI api = new CampanhaAPI();
		try {
			CampanhasDTO campanhas = api.getCampanhas("2019-08-04", "2100-08-04");
			OpcoesParcelamentoDTO opcoesParcelamento = api.getOpcoesParcelamento(1L, null);
			System.out.println(opcoesParcelamento);
			System.out.println(campanhas);
		} catch (ApiException e) {
			e.printStackTrace();
		}

	}

}
