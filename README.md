# 📦 ScannerCódigoDeBarras

> 🎓 Projeto acadêmico - Universidade Franciscana (UFN)  

Sistema web para controle de **entrega e recebimento de itens na portaria da Universidade Franciscana**.  
Permite o cadastro de usuários e itens, leitura de códigos de barras e registro das movimentações realizadas.

---

## 🧩 Funcionalidades

- Leitura de código de barras (usuário + item)
- Cadastro, edição e exclusão de usuários
- Cadastro, edição e exclusão de itens
- Registro de empréstimo e devolução
- Visualização de itens emprestados
- Menu lateral retrátil (sidebar)
- Estilização com Tailwind CSS e CSS customizado

---

## 🧪 Telas do Sistema

- **Login** — `acesso/login.html`
- **Cadastro de Usuário** — `scanner/cadastro_usuario.html`
- **Cadastro de Item** — `scanner/cadastro_item.html`
- **Scanner** — `scanner/index.html`
- **Usuários e Itens** — `scanner/usuarios_itens_cadastrados.html`
- **Movimentações Atuais** — `scanner/movimento.html`

---

## 🎨 Estilos

- **sidebar.css** — controla o menu lateral
- **style.css** — aplica estilos gerais
- **Tailwind CDN** — usado nas páginas principais

---

## Diagrama de Domínio

O Diagrama de Domínio é uma representação conceitual que descreve as principais entidades do sistema e seus relacionamentos.

![C - Dominio](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/Dominio.png)

---

## Diagrama de Caso de Uso

O diagrama de casos de uso é uma ferramenta visual utilizada para representar as interações entre os atores e o sistema que está sendo modelado, destacando as funcionalidades que o sistema oferece.

![C - CasoDeUso](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/Caso%20de%20Uso.png)

---

## Descrição do Caso de Uso

Este sistema é voltado para o controle de usuários e itens, incluindo o registro de empréstimos e devoluções. O ator principal do sistema é o Porteiro, responsável por gerenciar tanto os usuários quanto os itens cadastrados na portaria da instituição.

### **1. Cadastrar Usuário**
Ator: Porteiro  
Pré-condição: A tela de cadastro deve estar acessível.  
Pós-condição: Um novo usuário é salvo no sistema.  

Base-sequence:  
O porteiro acessa o formulário de cadastro.  
O porteiro preenche os dados do novo usuário.  
O sistema valida as informações e registra o novo usuário.  
Nota: Caso os dados estejam incompletos ou inválidos, uma mensagem de erro é exibida.  

### **2. Editar Usuário**
Ator: Porteiro  
Pré-condição: O usuário deve estar previamente cadastrado.  
Pós-condição: Os dados do usuário são atualizados.  

Base-sequence:  
O porteiro seleciona um usuário da lista.  
O porteiro altera os campos desejados.  
O sistema salva as alterações e atualiza os dados.  
Nota: A edição pode ser limitada para determinados campos.  

### **3. Excluir Usuário**
Ator: Porteiro  
Pré-condição: O usuário deve estar cadastrado.  
Pós-condição: O usuário é removido do banco de dados.    

Base-sequence:  
O porteiro seleciona o usuário.  
O porteiro confirma a exclusão.  
O sistema remove o registro do usuário.  
Nota: A ação é irreversível.  

### **4. Cadastrar Item**
Ator: Porteiro  
Pré-condição: A tela de cadastro deve estar disponível.  
Pós-condição: Um novo item é salvo no sistema com status “disponível”.  

Base-sequence:  
O porteiro acessa o formulário de cadastro de item.  
Insere os dados como nome, tipo, código de barras, etc.  
O sistema registra o novo item.  
Nota: O código de barras deve ser único.  

### **5. Editar Item**
Ator: Porteiro  
Pré-condição: O item já deve estar cadastrado.  
Pós-condição: As informações do item são atualizadas.  

Base-sequence:  
O porteiro localiza e seleciona o item.  
Modifica os dados necessários.  
O sistema salva as mudanças.  

### **6. Excluir Item**
Ator: Porteiro  
Pré-condição: O item deve estar cadastrado e não emprestado.  
Pós-condição: O item é excluído do sistema.  

Base-sequence:  
O porteiro seleciona o item na interface.  
Confirma a exclusão.  
O sistema remove o item.  

### **7. Realizar Empréstimo**
Ator: Porteiro  
Pré-condição: O usuário e o item devem estar cadastrados; o item deve estar disponível.  
Pós-condição: O item é marcado como “emprestado” e a movimentação registrada.  

Base-sequence:  
O porteiro escaneia o código do usuário.  
Em seguida, escaneia o código do item.  
O sistema valida a disponibilidade e registra o empréstimo.  
Nota: O sistema exibe mensagem de erro caso o item já esteja emprestado.

### **8. Realizar Devolução**
Ator: Porteiro  
Pré-condição: O item deve estar emprestado.  
Pós-condição: O item é marcado como “disponível” e a devolução registrada.  

Base-sequence:  
O porteiro escaneia o código do usuário.  
Escaneia o código do item.  
O sistema atualiza o status e registra a devolução.  

---

## 🧩 Diagrama de Classes

### Item

O diagrama abaixo representa a estrutura do sistema, destacando as classes principais (`Item`, `ItemService`, `ItemRepository` e `ItemController`) e suas relações:

![C - Dominio](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/Classe_Item.png)

### Porteiro

O diagrama abaixo representa a estrutura do sistema, destacando as classes principais (`Porteiro`, `PorteiroService`, `PorteiroRepository` e `PorteiroController`) e suas relações:

![C - Dominio](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/Classe_Porteiro.png)

### Usuario

O diagrama abaixo representa a estrutura do sistema, destacando as classes principais (`Usuario`, `UsuarioService`, `UsuarioRepository` e `UsuarioController`) e suas relações:

![C - Dominio](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/Classe_Usuario.png)

### Emprestimo/Devolucao

O diagrama abaixo representa o fluxo de interação entre as principais classes do sistema durante a realização de um empréstimo ou devolução de item. Ele destaca a comunicação entre as camadas Controller, Service e Repository, envolvendo as entidades Usuario e Item, além do controle de movimentações por meio da MovimentacaoService e MovimentacaoRepository. O ator principal é o Porteiro, que inicia a operação via MovimentacaoController.

![C - Dominio](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/main/Diagramas/Diagrama_EmprestimoDevolucao.png)

---

### Diagramas de Sequência

Estes diagramas mostram a interação entre os objetos para as ações do Porteiro, nas funcionalidades de cadastro, edição, exclusão de itens e usuários, empréstimo e devolução de itens.

## Cadastrar Item
![C - CadastrarItem_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/CadastroItem_Sequence.PNG)

## Editar Item
![C - EditarItem_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/EditarItem_Sequence.PNG)

## Excluir Item
![C - ExcluirItem_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/ExcluirItem_Sequence.PNG)

## Cadastrar Usuario
![C - CadastrarUsuario_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/CadastrarUsuario_Sequence.PNG)

## Editar Usuario
![C - EditarUsuario_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/EditarUsuario_Sequence.PNG)

## Excluir Usuario
![C - ExcluirUsuario_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/ExcluirUsuario_Sequence.PNG)

## Realizar Emprestimo
![C - RealizarEmprestimo_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/main/Diagramas/Emprestimo_Sequence.PNG)

## Realizar Devolucao
![C - RealizarDevolucao_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/main/Diagramas/Devolucao_Sequence.PNG)

---

## ⚙️ Backend

O backend será implementado com Spring Boot, mas as rotas já estão preparadas para integração.

- `/usuario/cadastro`
- `/item/cadastro`
- `/usuarios`
- `/movimentos`

---

## Organização dos Arquivos

```
C:.
├───.idea
│   ├───dataSources
│   └───dictionaries
├───BancoDeDados
└───Scanner
    └───Scanner
        ├───src
        │   └───main
        │       ├───java
        │       │   └───com
        │       │       └───example
        │       │           └───scanner
        │       │               ├───controller
        │       │               ├───model
        │       │               ├───repository
        │       │               └───service
        │       └───resources
        │           ├───static
        │           │   ├───css
        │           │   └───imagens
        │           └───templates
        │               ├───acesso
        │               └───scanner
        └───target
            ├───classes
            │   ├───com
            │   │   └───example
            │   │       └───scanner
            │   │           ├───controller
            │   │           ├───model
            │   │           ├───repository
            │   │           └───service
            │   ├───static
            │   │   ├───css
            │   │   └───imagens
            │   └───templates
            │       ├───acesso
            │       └───scanner
            ├───generated-sources
            │   └───annotations
            ├───generated-test-sources
            │   └───test-annotations
            └───test-classes
                └───com
                    └───example
                        └───scanner
```

---

## 📝 Licença

Este projeto está licenciado sob os termos da licença MIT.  
Consulte o arquivo [LICENSE](LICENSE) para mais informações.

---

## 👤 Autores

**Luiz Miguel Toller Marconatto**  
Curso de Ciência da Computação – Universidade Franciscana (UFN)  

E-mail: luizmigueltoller@gmail.com  
GitHub: [@MiguelToller](https://github.com/MiguelToller)

---

**Eduardo Carvalho Ceretta**  
Curso de Ciência da Computação – Universidade Franciscana (UFN)  

E-mail: Duduceretta@gmail.com  
GitHub: [@Duduceretta](https://github.com/Duduceretta)

---

**Yuri Alexander**  
Curso de Ciência da Computação – Universidade Franciscana (UFN)  

E-mail: yuri@gmail.com  
GitHub: [@yuyukiin](https://github.com/yuyukiin)

---

**Pedro Henrique Daroda**  
Curso de Ciência da Computação – Universidade Franciscana (UFN)  

E-mail: pedro@gmail.com  
GitHub: [@pedr0xh](https://github.com/pedr0xh)
