## Correção Projeto 3 (branch de 02/05)

### Nota base: 10,75

### Comentários

- Backlog parcialmente preenchido
- Instruções de uso não preenchido
- Não gravaram video de apresentação
- Erros sintáticos que impediram a execução do sistema. Ex: uso de variaveis não definidos, em testes uso de construtores não definidos, etc
- Classe midia apenas criada, não estão utilizando ela para classe pai, garantindo assim polimorfismo entre os elementos
- A lista de elementos do Cliente deveria ser polimorfica (Midia), assim como as filtragens a serem realizadas
- Não implementado todas as filtragens solicitadas
- Filme não possui todas as informações especificadas
- Em Serie, rever metodo todasAsSeries()
- Como não utilizam herança, existem alguns métodos repetidos entre Filme e Serie, isso tende a aumentar depois que atualizar corretamente as informações de Filme
- Rever metodo registrarAudiencia() na PlataformaStreaming
- Não estão lidando corretamente com as informações de Genero e Idioma (elas não estão presentes no arquivo csv)
- Apenas iniciaram a implementação da classe Main

1. Aderência às classes do diagrama: 1,5/2 pontos
  - Diagramas

2. Requisitos de corretamente implementados: 4,5/12 pontos
  - Carga de dados					1/2 pontos
  - Cadastro + salvar dados			0/2 pontos
  - Robustez básica					0,25/1 ponto
  - Clientes							1/2 pontos
	Listas, audiência sem repet
  - Séries							0,5/1 ponto
	 - audiência
  - Filme/Herança de mídia			0,25/1 ponto
  - Buscas 							1,5/3 pontos
	 - nome, gênero, idioma

3. Documentação de código: 4/4 pontos

4. Implementação na aula inicial: 0,75/2 pontos (cliente e série testados)
