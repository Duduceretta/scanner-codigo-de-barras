CREATE TABLE USUARIO (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tipo ENUM('professor', 'colaborador') NOT NULL,
    codigo_barra VARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE ITEM (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tipo ENUM('chave', 'controle') NOT NULL,
    codigo_barra VARCHAR(50) UNIQUE NOT NULL,
    status ENUM('disponivel', 'emprestado') NOT NULL DEFAULT 'disponivel'
);
CREATE TABLE MOVIMENTACAO (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_item INT NOT NULL,
    tipo ENUM('emprestimo', 'devolucao') NOT NULL,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id),
    FOREIGN KEY (id_item) REFERENCES ITEM(id)
);
