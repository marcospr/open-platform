package br.com.viavarejo.api;


import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.model.response.CampanhasDTO;
import br.com.viavarejo.api.model.response.ChaveDTO;
import br.com.viavarejo.api.model.response.OpcoesParcelamentoDTO;

public class CampanhaTeste {

	public static void main(String[] args) {
		CampanhaAPI api = new CampanhaAPI();
		SegurancaAPI apiSeguranca = new SegurancaAPI();
		try {
			CampanhasDTO campanhas = api.getCampanhas("2019-08-04", "2100-08-04");
			OpcoesParcelamentoDTO opcoesParcelamento = api.getOpcoesParcelamento(1L, null);
			ChaveDTO chave = apiSeguranca.getChave();
			System.out.println("Parcelamento:" + opcoesParcelamento);
			System.out.println("Campanhas:" + campanhas);
			System.out.println("Chaves:" + chave);
		} catch (ApiException e) {
			e.printStackTrace();
		}

	}

}
