package com.finance.api.domain.dadosCategoria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;


    @Test
    void findByTipoCategoria() {


        Categoria resultado = repository.findByTipoCategoria(TipoCategorias.ALIMENTACAO);

        assertEquals(TipoCategorias.ALIMENTACAO,resultado.getTipoCategoria());

    }
}