package br.com.viavarejo.api;

import java.util.HashMap;
import java.util.Map;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.RequestUtil;
import br.com.viavarejo.api.model.response.CampanhasDTO;
import br.com.viavarejo.api.model.response.OpcoesParcelamentoDTO;

public class CampanhaAPI {
	
	private RequestUtil<CampanhasDTO> requestUtilCampanha;
	private RequestUtil<OpcoesParcelamentoDTO> requestUtilParcelamento;
	
	public CampanhaAPI() {
		requestUtilCampanha  =  new RequestUtil<CampanhasDTO>(CampanhasDTO.class);
		requestUtilParcelamento = new RequestUtil<OpcoesParcelamentoDTO>(OpcoesParcelamentoDTO.class);
	}
	
	public CampanhasDTO getCampanhas(String dtInicio, String dtFim) throws ApiException {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("dataInicio", dtInicio);
		queryParams.put("dataFim", dtInicio);
		return requestUtilCampanha.get("http://api-integracao-casasbahia.hlg-b2b.net/campanhas", "H9xO4+R8GUy+18nUCgPOlg==", queryParams);
	}
	

}
