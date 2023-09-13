package br.com.agendamento.api.repository;

import br.com.agendamento.api.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    Email findByCodigoConfirmacao(String cod);

}
