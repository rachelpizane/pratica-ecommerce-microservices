ALTER TABLE IF EXISTS tb_clientes
      ADD COLUMN ativo boolean NOT NULL DEFAULT true;

UPDATE tb_clientes
      SET ativo = true
      WHERE ativo is NULL;

ALTER TABLE IF EXISTS tb_clientes
      ALTER COLUMN ativo SET NOT NULL;