CREATE DATABASE SistemaAcademico;
USE SistemaAcademico;

CREATE TABLE tb_login(
	usuario varchar(255) primary key,
	senha varchar(255)
);

INSERT INTO tb_login VALUES 
('usuario','$2a$10$x5LxuB7sf5Mx8wZyY111m.Hpp2IMqdoAUuH729l5By8eY25kwsK1S');
// senha para 1234567

CREATE TABLE Curso (
    cd_curso INT PRIMARY KEY AUTO_INCREMENT,
    nome_curso VARCHAR(100) NOT NULL,
    area_curso VARCHAR(100),
    carga_horaria_curso INT,
    modalidade_curso VARCHAR(50)
);

CREATE TABLE Professor (
    cd_professor INT PRIMARY KEY AUTO_INCREMENT,
    nome_professor VARCHAR(100),
    cpf_professor VARCHAR(11) UNIQUE NOT NULL,
    tel_professor VARCHAR(15),
    email_professor VARCHAR(100) UNIQUE,
    senha_professor VARCHAR(50)
);

CREATE TABLE Aluno (
    cd_aluno INT PRIMARY KEY AUTO_INCREMENT,
	nome_aluno VARCHAR(100),
    email_aluno VARCHAR(100) UNIQUE,
    senha_aluno VARCHAR(50),
    cpf_aluno VARCHAR(11) UNIQUE NOT NULL,
    tel_aluno VARCHAR(15),
    data_nascimento DATE,
    sexo_aluno CHAR(1)
);

CREATE TABLE Matricula (
    cd_matricula INT PRIMARY KEY AUTO_INCREMENT,
    dt_matricula DATE NOT NULL,
    cd_aluno INT,
    cd_curso INT,
    FOREIGN KEY (cd_aluno) REFERENCES Aluno(cd_aluno),
    FOREIGN KEY (cd_curso) REFERENCES Curso(cd_curso)
);

CREATE TABLE Coordenacao (
    cd_coordenacao INT PRIMARY KEY AUTO_INCREMENT,
    tel_coordenacao VARCHAR(15),
    email_coordenacao VARCHAR(100),
    dt_inicio DATE,
    dt_fim DATE,
    cd_curso INT,
    cd_professor INT,
    FOREIGN KEY (cd_curso) REFERENCES Curso(cd_curso),
    FOREIGN KEY (cd_professor) REFERENCES Professor(cd_professor)
);

CREATE TABLE Disciplina (
    cd_disciplina INT PRIMARY KEY AUTO_INCREMENT,
    nome_disciplina VARCHAR(100),
    sigla_disciplina VARCHAR(10),
    ementa_disciplina TEXT,
    carga_horaria_disciplina INT,
    cd_curso INT,
    FOREIGN KEY (cd_curso) REFERENCES Curso(cd_curso)
);

CREATE TABLE Turma (
    cd_turma INT PRIMARY KEY AUTO_INCREMENT,
    ano_semestre VARCHAR(10),
    cd_disciplina INT,
    cd_professor INT,
    FOREIGN KEY (cd_disciplina) REFERENCES Disciplina(cd_disciplina),
    FOREIGN KEY (cd_professor) REFERENCES Professor(cd_professor)
);

CREATE TABLE VinculoTurmaAluno (
    cd_vinculo INT PRIMARY KEY AUTO_INCREMENT,
    nota DECIMAL(5,2),
    frequencia DECIMAL(5,2),
    cd_turma INT,
    cd_aluno INT,
    FOREIGN KEY (cd_turma) REFERENCES Turma(cd_turma),
    FOREIGN KEY (cd_aluno) REFERENCES Aluno(cd_aluno)
);

CREATE TABLE Atendimento (
    cd_atendimento INT PRIMARY KEY AUTO_INCREMENT,
    assunto_atendimento VARCHAR(255),
    status VARCHAR(50),
    cd_aluno INT,
    cd_coordenacao INT,
    FOREIGN KEY (cd_aluno) REFERENCES Aluno(cd_aluno),
    FOREIGN KEY (cd_coordenacao) REFERENCES Coordenacao(cd_coordenacao)
);

INSERT INTO Curso (nome_curso, area_curso, carga_horaria_curso, modalidade_curso)
VALUES 
('Ciência da Computação', 'Tecnologia', 4200, 'Presencial'),
('Engenharia de Software', 'Tecnologia', 4000, 'Presencial'),
('Sistemas de Informação', 'Tecnologia', 3600, 'EAD'),
('Análise e Desenvolvimento de Sistemas', 'Tecnologia', 3000, 'EAD'),
('Segurança da Informação', 'Tecnologia', 3600, 'Presencial');

INSERT INTO Professor (nome_professor, cpf_professor, tel_professor, email_professor, senha_professor)
VALUES 
('Carlos Silva', '12345678901', '21987654321', 'carlos.silva@universidade.com', 'senha123'),
('Ana Maria', '23456789012', '11998765432', 'ana.maria@universidade.com', 'senha456'),
('João Pedro', '34567890123', '31999887766', 'joao.pedro@universidade.com', 'senha789'),
('Mariana Costa', '45678901234', '21987650000', 'mariana.costa@universidade.com', 'senha101'),
('Rafael Santos', '56789012345', '11999988877', 'rafael.santos@universidade.com', 'senha202'),
('José Almeida', '67890123456', '31987654321', 'jose.almeida@universidade.com', 'senha303'),
('Patricia Lima', '78901234567', '21987654322', 'patricia.lima@universidade.com', 'senha404'),
('Carlos Souza', '89012345678', '11987654323', 'carlos.souza@universidade.com', 'senha505'),
('Fabiana Oliveira', '90123456789', '31987654324', 'fabiana.oliveira@universidade.com', 'senha606'),
('Tiago Pereira', '11234567890', '21987654325', 'tiago.pereira@universidade.com', 'senha707');

INSERT INTO Aluno (nome_aluno, email_aluno, senha_aluno, cpf_aluno, tel_aluno, data_nascimento, sexo_aluno) 
VALUES 
('Maria Souza', 'maria.souza@gmail.com', '12345', '45678901234', '21987651234', '2001-05-12', 'F'), 
('João Lima', 'joao.lima@gmail.com', '12345', '56789012345', '21987654321', '1999-08-23', 'M'),
('Lucas Pereira', 'lucas.pereira@gmail.com', '12345', '78901234567', '21965432109', '2001-02-07', 'M'), 
('Júlia Santos', 'julia.santos@gmail.com', '12345', '89012345678', '21954321098', '2002-09-12', 'F'), 
('Rafael Silva', 'rafael.silva@gmail.com', '12345', '90123456789', '21943210987', '2000-03-03', 'M'), 
('Pedro Rocha', 'pedro.rocha@gmail.com', '12345', '11234567891', '21921098765', '1999-11-22', 'M'), 
('Luciana Martins', 'luciana.martins@gmail.com', '12345', '21234567892', '21910987654', '1997-01-11', 'F'), 
('Gabriel Souza', 'gabriel.souza@gmail.com', '12345', '31234567893', '21999887766', '2001-07-30', 'M'), 
('Carla Andrade', 'carla.andrade@gmail.com', '12345', '41234567894', '21977766655', '2002-04-15', 'F'), 
('Renato Oliveira', 'renato.oliveira@gmail.com', '12345', '51234567895', '21966655544', '1998-12-01', 'M'), 
('Rafaela Melo', 'rafaela.melo@gmail.com', '12345', '63456789012', '21987654321', '1999-03-12', 'F'), 
('Marcos Costa', 'marcos.costa@gmail.com', '12345', '74567890123', '21987654321', '1998-06-18', 'M'), 
('Carolina Farias', 'carolina.farias@gmail.com', '12345', '85678901234', '21987654321', '2000-01-25', 'F'), 
('Daniel Ferreira', 'daniel.ferreira@gmail.com', '12345', '96789012345', '21987654321', '2001-04-10', 'M'), 
('Luana Martins', 'luana.martins@gmail.com', '12345', '07890123456', '21987654321', '1999-09-29', 'F'), 
('Felipe Silva', 'felipe.silva@gmail.com', '12345', '18901234567', '21987654321', '2002-01-10', 'M'), 
('Beatriz Rodrigues', 'beatriz.rodrigues@gmail.com', '12345', '29012345678', '21987654321', '2000-11-15', 'F'), 
('André Pereira', 'andre.pereira@gmail.com', '12345', '40123456789', '21987654321', '1999-07-22', 'M'), 
('Josefina Souza', 'josefina.souza@gmail.com', '12345', '51234567890', '21987654321', '2001-12-04', 'F'), 
('Paulo Santos', 'paulo.santos@gmail.com', '12345', '62345678901', '21987654321', '1998-05-08', 'M'),
('Ana Clara', 'ana.clara@gmail.com', '12345', '72345678901', '21955544433', '2002-05-20', 'F'),
('Bruno Vieira', 'bruno.vieira@gmail.com', '12345', '82345678902', '21933322211', '2001-10-11', 'M'),
('Fernanda Lopes', 'fernanda.lopes@gmail.com', '12345', '92345678903', '21922211100', '1999-03-15', 'F'),
('Caio Almeida', 'caio.almeida@gmail.com', '12345', '03345678904', '21911100099', '2000-07-22', 'M'),
('Isabela Ramos', 'isabela.ramos@gmail.com', '12345', '14345678905', '21900099988', '1997-11-08', 'F'),
('Rodrigo Nunes', 'rodrigo.nunes@gmail.com', '12345', '25345678906', '21988877766', '2002-03-03', 'M'),
('Juliana Rocha', 'juliana.rocha@gmail.com', '12345', '36345678907', '21977766655', '2001-09-17', 'F'),
('Eduardo Pinto', 'eduardo.pinto@gmail.com', '12345', '47345678908', '21966655544', '1998-06-06', 'M'),
('Larissa Oliveira', 'larissa.oliveira@gmail.com', '12345', '58345678909', '21955544433', '1999-01-13', 'F'),
('Tiago Moreira', 'tiago.moreira@gmail.com', '12345', '69345678910', '21944433322', '2000-12-18', 'M'),
('Camila Borges', 'camila.borges@gmail.com', '12345', '70345678911', '21933322211', '1997-04-21', 'F'),
('Vinícius Melo', 'vinicius.melo@gmail.com', '12345', '81345678912', '21922211100', '1998-09-09', 'M'),
('Helena Ramos', 'helena.ramos@gmail.com', '12345', '92345678913', '21911100099', '1999-07-25', 'F'),
('Otávio Lima', 'otavio.lima@gmail.com', '12345', '03345678914', '21900099988', '2000-10-30', 'M'),
('Alice Costa', 'alice.costa@gmail.com', '12345', '14345678915', '21988877766', '2001-01-12', 'F'),
('Rafael Santos', 'rafael.santos@gmail.com', '12345', '25345678916', '21977766655', '1998-08-14', 'M'),
('Sofia Almeida', 'sofia.almeida@gmail.com', '12345', '36345678917', '21966655544', '2000-05-19', 'F'),
('Matheus Teixeira', 'matheus.teixeira@gmail.com', '12345', '47345678918', '21955544433', '1999-03-28', 'M'),
('Bianca Lopes', 'bianca.lopes@gmail.com', '12345', '58345678919', '21944433322', '2002-11-11', 'F'),
('Joana Silva', 'joana.silva@gmail.com', '12345', '69345678920', '21933322211', '2001-06-01', 'F'),
('Leonardo Rocha', 'leonardo.rocha@gmail.com', '12345', '70345678921', '21911122233', '1998-02-15', 'M'),
('Clara Menezes', 'clara.menezes@gmail.com', '12345', '81345678922', '21933344455', '2000-07-23', 'F'),
('Miguel Araújo', 'miguel.araujo@gmail.com', '12345', '92345678923', '21955566677', '1999-10-10', 'M'),
('Amanda Silva', 'amanda.silva@gmail.com', '12345', '03345678924', '21977788899', '2001-04-08', 'F'),
('Victor Carvalho', 'victor.carvalho@gmail.com', '12345', '14345678925', '21999900011', '1997-09-27', 'M'),
('Larissa Santos', 'larissa.santos@gmail.com', '12345', '25345678926', '21988811122', '1998-12-19', 'F'),
('Bruna Figueiredo', 'bruna.figueiredo@gmail.com', '12345', '36345678927', '21977722233', '2002-06-18', 'F'),
('Diego Almeida', 'diego.almeida@gmail.com', '12345', '47345678928', '21966633344', '2000-11-21', 'M'),
('Mariana Costa', 'mariana.costa@gmail.com', '12345', '58345678929', '21955544455', '1999-01-29', 'F'),
('Renan Oliveira', 'renan.oliveira@gmail.com', '12345', '69345678930', '21944455566', '1998-08-05', 'M');

INSERT INTO Disciplina (nome_disciplina, sigla_disciplina, ementa_disciplina, carga_horaria_disciplina, cd_curso)
VALUES 
('Introdução à Programação', 'IP', 'Fundamentos de lógica e algoritmos.', 60, 1),
('Estruturas de Dados', 'ED', 'Conceitos avançados de manipulação de dados.', 80, 1),
('Banco de Dados', 'BD', 'Modelagem de dados e SQL.', 90, 2),
('Desenvolvimento Web', 'DW', 'Construção de aplicações web.', 120, 4),
('Segurança em Redes', 'SR', 'Práticas e conceitos sobre segurança de redes.', 100, 5),
('Programação Orientada a Objetos', 'POO', 'Fundamentos e técnicas de POO.', 80, 3),
('Sistemas Operacionais', 'SO', 'Princípios e funcionamento de sistemas operacionais.', 100, 3),
('Engenharia de Software', 'ES', 'Metodologias e processos de desenvolvimento de software.', 120, 2),
('Redes de Computadores', 'RC', 'Arquitetura e protocolos de redes.', 90, 4),
('Gestão de Banco de Dados', 'GBD', 'Administração de sistemas de bancos de dados.', 100, 5);

INSERT INTO Turma (ano_semestre, cd_disciplina, cd_professor)
VALUES 
('2025-1', 1, 1),
('2025-1', 2, 2),
('2025-1', 3, 3),
('2025-1', 4, 4),
('2025-1', 5, 5),
('2025-1', 6, 6),
('2025-1', 7, 7),
('2025-1', 8, 8),
('2025-1', 9, 9),
('2025-1', 10, 10);

INSERT INTO Matricula (dt_matricula, cd_aluno, cd_curso)
VALUES 
('2025-01-15', 1, 1),
('2025-01-15', 2, 1),
('2025-01-15', 3, 1),
('2025-01-15', 4, 1),
('2025-01-15', 5, 1),
('2025-01-15', 6, 2),
('2025-01-15', 7, 2),
('2025-01-15', 8, 2),
('2025-01-15', 9, 2),
('2025-01-15', 10, 2),
('2025-01-15', 11, 3),
('2025-01-15', 12, 3),
('2025-01-15', 13, 3),
('2025-01-15', 14, 3),
('2025-01-15', 15, 3),
('2025-01-15', 16, 4),
('2025-01-15', 17, 4),
('2025-01-15', 18, 4),
('2025-01-15', 19, 4),
('2025-01-15', 20, 4),
('2025-01-15', 21, 5),
('2025-01-15', 22, 5),
('2025-01-15', 23, 5),
('2025-01-15', 24, 5),
('2025-01-15', 25, 5),
('2025-01-15', 26, 1),
('2025-01-15', 27, 1),
('2025-01-15', 28, 1),
('2025-01-15', 29, 1),
('2025-01-15', 30, 1),
('2025-01-15', 31, 2),
('2025-01-15', 32, 2),
('2025-01-15', 33, 2),
('2025-01-15', 34, 2),
('2025-01-15', 35, 2),
('2025-01-15', 36, 3),
('2025-01-15', 37, 3),
('2025-01-15', 38, 3),
('2025-01-15', 39, 3),
('2025-01-15', 40, 3),
('2025-01-15', 41, 4),
('2025-01-15', 42, 4),
('2025-01-15', 43, 4),
('2025-01-15', 44, 4),
('2025-01-15', 45, 4),
('2025-01-15', 46, 5),
('2025-01-15', 47, 5),
('2025-01-15', 48, 5),
('2025-01-15', 49, 5),
('2025-01-15', 50, 5);

INSERT INTO VinculoTurmaAluno (nota, frequencia, cd_turma, cd_aluno)
VALUES
(85.5, 90, 1, 1),
(78.0, 85, 1, 2),
(92.3, 95, 1, 3),
(88.7, 80, 1, 4),
(91.5, 88, 1, 5),
(87.2, 92, 2, 6),
(75.4, 89, 2, 7),
(83.6, 91, 2, 8),
(89.9, 93, 2, 9),
(72.0, 86, 2, 10),
(84.3, 87, 3, 11),
(90.1, 94, 3, 12),
(77.5, 89, 3, 13),
(88.9, 91, 3, 14),
(93.4, 96, 3, 15),
(76.8, 84, 4, 16),
(82.7, 90, 4, 17),
(91.2, 92, 4, 18),
(79.4, 88, 4, 19),
(88.3, 89, 4, 20),
(85.1, 92, 5, 21),
(80.3, 85, 5, 22),
(94.7, 97, 5, 23),
(88.5, 89, 5, 24),
(90.9, 93, 5, 25),
(86.2, 91, 6, 26),
(78.5, 87, 6, 27),
(89.7, 94, 6, 28),
(92.8, 96, 6, 29),
(84.6, 88, 6, 30),
(81.5, 86, 7, 31),
(89.2, 92, 7, 32),
(77.4, 84, 7, 33),
(90.6, 95, 7, 34),
(87.9, 91, 7, 35),
(88.4, 89, 8, 36),
(79.8, 85, 8, 37),
(92.3, 96, 8, 38),
(86.7, 93, 8, 39),
(91.1, 92, 8, 40),
(83.5, 88, 9, 41),
(78.9, 85, 9, 42),
(85.6, 89, 9, 43),
(90.4, 92, 9, 44),
(88.1, 91, 9, 45),
(87.4, 93, 10, 46),
(80.2, 89, 10, 47),
(92.5, 95, 10, 48),
(84.8, 88, 10, 49),
(89.7, 90, 10, 50);

INSERT INTO Coordenacao (cd_coordenacao, tel_coordenacao, email_coordenacao, dt_inicio, dt_fim, cd_curso, cd_professor)
VALUES
(1, '21999998888', 'coord1@universidade.com', '2025-01-01', '2026-01-01', 1, 1),
(2, '21888887777', 'coord2@universidade.com', '2025-01-01', '2026-01-01', 2, 2),
(3, '21777776666', 'coord3@universidade.com', '2025-01-01', '2026-01-01', 3, 3),
(4, '21666665555', 'coord4@universidade.com', '2025-01-01', '2026-01-01', 4, 4),
(5, '21555554444', 'coord5@universidade.com', '2025-01-01', '2026-01-01', 5, 5);

INSERT INTO Atendimento (assunto_atendimento, status, cd_aluno, cd_coordenacao)
VALUES 
('Dúvida algoritmo', 'Concluído', 1, 1),
('Revisão nota', 'Pendente', 2, 1),
('Alteração cadastro', 'Concluído', 3, 2),
('Consulta frequência', 'Concluído', 4, 1),
('Segunda chamada', 'Pendente', 5, 3),
('Dúvida algoritmo', 'Concluído', 6, 2),
('Revisão nota', 'Pendente', 7, 3),
('Alteração cadastro', 'Concluído', 8, 4),
('Consulta frequência', 'Concluído', 9, 1),
('Segunda chamada', 'Pendente', 10, 5),
('Dúvida algoritmo', 'Concluído', 11, 1),
('Revisão nota', 'Pendente', 12, 2),
('Alteração cadastro', 'Concluído', 13, 3),
('Consulta frequência', 'Concluído', 14, 4),
('Segunda chamada', 'Pendente', 15, 5);