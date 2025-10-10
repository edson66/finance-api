package com.finance.api.domain.dadosCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscarCategoria(TipoCategorias TipoCategorias){
        var categoria = repository.findByTipoCategoria(TipoCategorias);

        return categoria;
    }
}
