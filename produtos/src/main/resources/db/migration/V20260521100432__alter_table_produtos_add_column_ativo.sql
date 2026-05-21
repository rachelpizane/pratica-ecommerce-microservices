ALTER TABLE IF EXISTS tb_produtos
      ADD COLUMN ativo boolean NOT NULL DEFAULT true;

UPDATE tb_produtos
      SET ativo = true
      WHERE ativo is NULL;

ALTER TABLE IF EXISTS tb_produtos
      ALTER COLUMN ativo SET NOT NULL;