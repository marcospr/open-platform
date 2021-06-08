package br.com.via.api;

import java.util.HashMap;
import java.util.Map;

import br.com.via.api.client.ApiException;
import br.com.via.api.client.RequestUtil;
import br.com.via.api.model.response.OpcoesParcelamentoDTO;

public class FormaPagamentoApi {
	
	private RequestUtil<?, OpcoesParcelamentoDTO> request;
	
	private String basePath;
	private String authorization;
	
	public FormaPagamentoApi(String basePath, String authorization) {
		this.basePath = basePath;
		this.authorization = authorization;
		request = new RequestUtil<>(OpcoesParcelamentoDTO.class);
	}
	
	public OpcoesParcelamentoDTO getOpcoesParcelamento(String idFormaPagamento, String idCampanha, String cnpj, String valorParcela) throws ApiException {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("idCampanha", idCampanha);
		queryParams.put("cnpj", cnpj);
		queryParams.put("valorParcelar", valorParcela);
		return request.get(basePath + "/formas-pagamento/"+idFormaPagamento+"/opcoes-parcelamento", authorization, queryParams);
	}

}
