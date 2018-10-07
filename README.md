# clinica-vet

Autor: Eduardo Pooter Reis

Aplicativo desenvolvido para a disciplina de Programação Android Avançada, Especialização em Desenvolvimento de Aplicações para Dispostivos Móveis da Faculdade Senac Porto Alegre.
O objetivo/uso do aplicativo é permitir cadastrar animais de estimação e respectivos donos em uma clínica veterinária (pet shop). É possível obter os dados de endereço a partir do CEP informado pelo usuário; também é possível compartilhar os dados dos pets na forma de texto para outros aplicativos e mídias sociais.

Foram aplicados os seguintes conceitos e tecnologias:
- Intent Explicita para fluir entre atividades;
- Intent Implicita com social share;
- Receiver (broadcast receiver) para avisar o usuário sobre eventos de carregamento da bateria;
- Service rodando em plano de fundo para logar o tempo de execução;
- Conectividade HTTP com Retrofit2 e Butterknife para buscar dados JSON a partir de um webservice de CEP;
- Banco de Dados SQLite nativo para persistência.

Demo:
https://youtu.be/3tiSxRNI1Vk
