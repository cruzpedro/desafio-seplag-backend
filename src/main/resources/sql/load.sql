-- CATEGORIA --
INSERT INTO tb_categoria(id, descricao) VALUES (1, 'Identificação');
INSERT INTO tb_categoria(id, descricao) VALUES (2, 'Vida Funcional');
INSERT INTO tb_categoria(id, descricao) VALUES (3, 'Contagem de tempo');
INSERT INTO tb_categoria(id, descricao) VALUES (4, 'Remuneração/Proventos');

-- TRAMITACAO --
INSERT INTO tb_tramitacao(id, nome) VALUES (1, 'Instrição Inicial do Processo');
INSERT INTO tb_tramitacao(id, nome) VALUES (2, 'Análise Administrativa');
INSERT INTO tb_tramitacao(id, nome) VALUES (3, 'Análise Jurídica');

-- USUARIO --
INSERT INTO tb_usuario(id, nome, cpf, orgao, matricula) VALUES (1, 'Usuario', '111.111.111-11', 'Órgão', 11);

-- BENEFICIO --
INSERT INTO tb_beneficio(id, arquivo, categoria_id, tramitacao_id, usuario_id)
  VALUES (1, 'arquivo.pdf', 1, 2, 1);

-- MOVIMENTO --
INSERT INTO tb_movimento(id, data, beneficio_id, tramitacaodestino_id, tramitacaoorigem_id)
  VALUES (1, '2018-01-01 00:00:00', 1, 2, 1);

-- GENERAL SETUP --
INSERT INTO tb_generalsetup(id, ds_key, ds_value) VALUES (1, 'CAMINHO_FISICO_ARQUIVOS', '${JBOSS_HOME}/standalone/tmp/vfs/arquivos}');
INSERT INTO tb_generalsetup(id, ds_key, ds_value) VALUES (2, 'CAMINHO_HTTP_ARQUIVOS', 'desafio-seplag-backend/arquivos');