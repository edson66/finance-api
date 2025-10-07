package com.finance.api.domain.dadosTransacoes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
}
