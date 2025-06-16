-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS portaria_ufn;
USE portaria_ufn;

-- Tabela de usuários (professores e colaboradores)
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo ENUM('professor', 'colaborador') NOT NULL,
    codigo_barra VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela de itens (chaves e controles de ar-condicionado)
CREATE TABLE item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo ENUM('chave', 'controle') NOT NULL,
    codigo_barra VARCHAR(50) NOT NULL UNIQUE,
    status ENUM('disponivel', 'emprestado') NOT NULL DEFAULT 'disponivel'
);

-- Tabela de movimentações (empréstimos e devoluções)
CREATE TABLE movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_item INT NOT NULL,
    tipo ENUM('emprestimo', 'devolucao') NOT NULL,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_item) REFERENCES item(id)
);
