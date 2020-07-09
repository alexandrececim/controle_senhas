# Sistema de Atendimento de Senhas Bancárias
## Finalidade do Sistema:

O Sistema de Controle de Fila Bancária, gerencia a ordem de chegada dos clientes e distingue na o atendimento dos clientes, atendendo primeiro todos os clientes preferências para depois começar os atendimentos Normal (sem prioridade).

![alt text](https://media-exp1.licdn.com/dms/image/C4E2DAQHRuLyvgqsREA/profile-treasury-image-shrink_800_800/0?e=1594346400&v=beta&t=EoNsHyZlGfX12NBFZYjFjCQuWyXY3CGTKlTI3Yh0bKg)

## Como funciona:
Faça o clone do repositório git, entre com o prompt na pasta ao qual você
"clonou" e digite 'mvn tomcat7:run' para carregar o servidor.

O servidor a pois ser carregado vai disponibilizar a aplicação web na porta 9090, e você poderá acessar pelo endereço: http://localhost:9090

A tela de abertura carrega por alguns segundos a logo e é automáticamente redirecionada a tela do 'Gestor' da aplicação que de maneira facíl e direta terá os controles das chamdas e também o 'Reset' a sua disposição.

![alt text](https://media-exp1.licdn.com/dms/image/C4E2DAQEIbwLVF4BMSw/profile-treasury-image-shrink_1280_1280/0?e=1594346400&v=beta&t=UB4qVtwYJRYshdjnBbW9Y3jSY3gNxX8C3p2shUI71wk)

Ao acionar as telas de 'atendimento' e do 'letreiro' que ficam no canto inferior, uma nova aba se abrirá carregando a pagina pretendida.

![alt text](https://media-exp1.licdn.com/dms/image/C4E2DAQG71G6V0EYbbw/profile-treasury-image-shrink_8192_8192/0?e=1594346400&v=beta&t=mH29iFhWqUhvhUsJ1G2Dp5f_Au7RA3mLiOubeINjlFc)

Ambas trazem de forma intuitiva, as funcionalidades de solicitar senhas preferenciais ou normais na tela de 'atendimento', bem como as chamadas que são verificadas (atualizadas) a cada 3 segundos na tela 'letreiro'.

O aplicativo android (contaFila.apk), é voltado para o uso do gerenciamento, sua tela é ainda mais simples, bastando somente configurar o endereço ip do servidor tomcat da aplicação.
