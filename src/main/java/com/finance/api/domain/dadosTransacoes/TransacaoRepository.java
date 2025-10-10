package com.finance.api.domain.dadosTransacoes;

import com.finance.api.domain.dadosUsuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

    Page<Transacao> findByUsuario(Usuario usuario, Pageable pageable);
}
