package com.finance.api.domain.dadosCategoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Categoria findByTipoCategoria(TipoCategorias tipoCategoria);
}
