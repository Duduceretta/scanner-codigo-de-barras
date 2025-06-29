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

## 👤 Ator: Porteiro
O Porteiro tem acesso completo ao sistema e pode realizar as seguintes ações:

### CadastrarUsuário
Permite que o porteiro registre um novo usuário no sistema, informando seus dados pessoais e de acesso.

### EditarUsuário
O porteiro pode atualizar as informações de um usuário já existente, como nome, e-mail ou status.

### ExcluirUsuário
Remove um usuário do sistema de forma permanente.

### CadastrarItem
Adiciona um novo item ao acervo do sistema, com dados como nome, tipo, status e código de barras.

### EditarItem
Permite atualizar as informações de um item já cadastrado, como o nome ou o tipo.

### ExcluirItem
Remove o item do sistema, tornando-o indisponível para empréstimo.

### RealizarEmprestimo
Registra o empréstimo de um item a um usuário. O item tem seu status alterado para "emprestado".

### RealizarDevolucao
Registra a devolução de um item emprestado, alterando seu status para "disponível".

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
![C - RealizarEmprestimo_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/Emprestimo_Sequence.PNG)

## Realizar Devolucao
![C - RealizarDevolucao_Sequence](https://github.com/Duduceretta/ScannerCodigoDeBarras/blob/Documenta%C3%A7%C3%A3o/Diagramas/Devolucao_Sequence.PNG)

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
