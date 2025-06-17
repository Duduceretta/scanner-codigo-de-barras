
### 🛠️ Configuração do Banco de Dados MySQL para o Projeto

Para que o projeto funcione corretamente com o banco de dados MySQL, siga os passos abaixo:

---

#### 📌 1. Verifique se o MySQL está instalado

Se você ainda **nunca usou o MySQL** no seu computador, instale o MySQL Server:

- [Download MySQL Community Server](https://dev.mysql.com/downloads/mysql/)

---

#### 🚀 2. Inicie o serviço do MySQL

Antes de rodar o projeto, certifique-se de que o **serviço do MySQL está ativo**.

**No Windows:**

- Abra o prompt de comando (como administrador) e execute:
  ```bash
  net start mysql
  ```

**No Linux:**

- Use:
  ```bash
  sudo service mysql start
  ```

---

#### 📝 3. Configure o `application.properties`

Abra o arquivo `src/main/resources/application.properties` e configure os dados de conexão com o banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/portaria_ufn
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

Substitua:
- `SEU_USUARIO` pelo nome de usuário do seu MySQL (ex: `root`)
- `SUA_SENHA` pela senha do seu MySQL

---

#### 🗃️ 4. Criação do banco de dados

Antes de iniciar o sistema, você pode criar o banco manualmente no MySQL:

```sql
CREATE DATABASE IF NOT EXISTS portaria_ufn;
```

Ou usar o arquivo SQL que está em:

```
BancoDeDados/BancoV2.sql
```

---

#### ✅ Pronto!

Agora você pode rodar o projeto com segurança. O Spring Boot criará as tabelas automaticamente com base nas entidades do sistema.
