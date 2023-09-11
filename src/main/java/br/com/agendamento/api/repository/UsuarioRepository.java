package br.com.agendamento.api.repository;

import br.com.agendamento.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        boolean existsByEmail(String email);
}
