package br.com.via.api;

import java.util.HashMap;
import java.util.Map;

import br.com.via.api.client.ApiException;
import br.com.via.api.client.PropsReaderUtil;
import br.com.via.api.client.RequestUtil;
import br.com.via.api.model.response.CampanhasDTO;
import br.com.via.api.model.response.OpcoesParcelamentoDTO;

public class CampanhaApi {
	
	private RequestUtil<?, CampanhasDTO> requestUtilCampanha;
	private RequestUtil<?, OpcoesParcelamentoDTO> requestUtilParcelamento;
	
	private String basePath;
	private String authorization;
	
	public CampanhaApi() {
		PropsReaderUtil prop = new PropsReaderUtil();
		this.basePath = prop.getHost();
		this.authorization = prop.getToken();
		requestUtilCampanha = new RequestUtil<>(CampanhasDTO.class);
		requestUtilParcelamento = new RequestUtil<>(OpcoesParcelamentoDTO.class);
	}
	
	public CampanhasDTO getCampanhas(String dtInicio, String dtFim) throws ApiException {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("dataInicio", dtInicio);
		queryParams.put("dataFim", dtFim);
		return requestUtilCampanha.get(basePath + "/campanhas", authorization, queryParams);
	}
	
	public OpcoesParcelamentoDTO getOpcoesParcelamento(String idCampanha, String cnpj) throws ApiException {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("cnpj", cnpj);
		return requestUtilParcelamento.get(basePath + "/campanhas/"+idCampanha+"/formas-pagamento/opcoes-parcelamento", authorization, queryParams);
	}

}
