package br.com.agendamento.api.repository;

import br.com.agendamento.api.model.UsuarioToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioTokenRepository extends JpaRepository<UsuarioToken, Long> {
    UsuarioToken findByCodigoConfirmacao(String codigoConfirmacao);
}
