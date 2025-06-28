CREATE DATABASE IF NOT EXISTS portaria_ufn;
USE portaria_ufn;

CREATE TABLE Porteiro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo_barra VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo ENUM('chave', 'controle') NOT NULL,
    codigo_barra VARCHAR(50) NOT NULL UNIQUE,
    status ENUM('disponivel', 'emprestado') NOT NULL DEFAULT 'disponivel'
);

CREATE TABLE Movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_item INT NOT NULL,
    tipo ENUM('EMPRESTIMO', 'DEVOLUCAO') NOT NULL,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_item) REFERENCES item(id)
);
