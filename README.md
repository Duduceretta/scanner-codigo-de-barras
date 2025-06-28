# 📦 ScannerCódigoDeBarras

Sistema web para controle de entrega e recebimento de itens na portaria da Universidade Franciscana (UFN). \
Permite o cadastro de usuários e itens, leitura de códigos de barras e registro de movimentações.

---

## 🧩 Funcionalidades

- Leitura de código de barras (usuário + item)
- Cadastro de usuários
- Cadastro de itens
- Visualização de usuários e itens cadastrados
- Visualização de itens em movimento
- Menu lateral retrátil
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

## ⚙️ Backend

O backend será implementado com Spring Boot, mas as rotas já estão preparadas para integração.

- `/usuario/cadastro`
- `/item/cadastro`
- `/usuarios`
- `/movimentos`

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
