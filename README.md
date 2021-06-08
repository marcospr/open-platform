# SDK Java para API B2B

Prov� os componentes para o uso da API B2B, disponibilizado pela VIA, facilitando a integra��o com as documenta��es relacionadas:
- http://api-integracao-pontofrio.hlg-b2b.net/swagger/ui/index#/
- http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#/
- http://api-integracao-extra.hlg-b2b.net/swagger/ui/index#/

## Configurando o SDK
 Dentro do pacote [src/main/resources] se encontra o arquivo de propriedades (config.properties), o qual deve ser configurado duas propriedades: 
 - host  (end-point utilizado).
 - token (token de acesso).
 
## APIs Dispon�veis

A seguir, s�o apresentadas as APIs e exemplos com as as principais opera��es do B2B.

- ## Campanha
    Api Utilizada para opera��es de campanha
     ## Obt�m todas as campanhas vinculadas ao parceiro:
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#/Campanha/Campanha_ListarCampanhaAsync
    - http://api-integracao-pontofrio.hlg-b2b.net/swagger/ui/index#/Campanha/Campanha_ListarCampanhaAsync
    - http://api-integracao-extra.hlg-b2b.net/swagger/ui/index#/Campanha/Campanha_ListarCampanhaAsync
        ```java
        CampanhaApi campanhaApi = new CampanhaApi();
        CampanhasDTO campanhas = campanhaApi.getCampanhas("2019-08-04", "2100-08-04");
        ```
    
     ## Obt�m todas as op��es de pagamento para uma determinada campanha:
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Campanha/Campanha_ListarOpcoesParcelamentoAsync
    - http://api-integracao-pontofrio.hlg-b2b.net/swagger/ui/index#/Campanha/Campanha_ListarOpcoesParcelamentoAsync
    - http://api-integracao-extra.hlg-b2b.net/swagger/ui/index#/Campanha/Campanha_ListarOpcoesParcelamentoAsync
        ```java
        CampanhaApi campanhaApi = new CampanhaApi();
        OpcoesParcelamentoDTO opcoesParcelamento = campanhaApi.getOpcoesParcelamento("5940", "57.822.975/0001-12");
        ```
- ## Forma de Pagamento
    Api Utilizada para opera��es de forma de pagamento
     ## Obter op��es de parcelamento:
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/FormaPagamento/FormaPagamento_ListarOpcoesParcelamentoAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/FormaPagamento/FormaPagamento_ListarOpcoesParcelamentoAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/FormaPagamento/FormaPagamento_ListarOpcoesParcelamentoAsync
        ```java
        FormaPagamentoApi pagamentoApi = new FormaPagamentoApi();
        OpcoesParcelamentoDTO opcoesParcelamento = pagamentoApi.getOpcoesParcelamento("1", "5940", "57.822.975/0001-12", "1000");
        ```
- ## Produto
    Api Utilizada para opera��es de produto
     ## Obter dados do produto:
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ObterProdutoAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ObterProdutoAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ObterProdutoAsync
        ```java
        ProdutoApi produtoApi = new ProdutoApi();
        ProdutoDTO dadosProduto = produtoApi.getDadosProduto("15", "5880205");
        ```
     ## Obter lista de dados dos produtos:
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ListarProdutoAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ListarProdutoAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ListarProdutoAsync
        ```java
        ProdutoApi produtoApi = new ProdutoApi();
        ProdutosDTO listaDadosProdutos = produtoApi.getListaDadosProdutos("15","5880205", "5880206");
        ```
     ## Obter dados do produto por Campanha:
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ObterProdutoPorCampanhaAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ObterProdutoPorCampanhaAsync
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Produto/Produto_ObterProdutoPorCampanhaAsync
        ```java
        ProdutoApi produtoApi = new ProdutoApi();
        ProdutoDTO dadosProdutoCampanha = produtoApi.getDadosProdutoCampanha("5940", "5880205", "57.822.975/0001-12", "15");
        ```
- ## Seguranca
    Api Utilizada para opera��es de seguranca
     ## Obter chave p�blica 2048 bits utilizada para criptografia dos dados do cart�o:
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Seguranca/Seguranca_ObterChave
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Seguranca/Seguranca_ObterChave
    - http://api-integracao-casasbahia.hlg-b2b.net/swagger/ui/index#!/Seguranca/Seguranca_ObterChave
        ```java
        SegurancaApi segurancaApi = new SegurancaApi();;
        ChaveDTO chave = segurancaApi.setAuthorization("TOKEN").getChave();
        ```
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        