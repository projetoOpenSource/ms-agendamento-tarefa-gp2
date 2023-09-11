package br.com.agendamento.api.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "idStatus")
@Table
@Entity
public class Status {
    @Id
    @Column(name = "id_status")
    private Long idStatus;

    @Column(name = "tipo_status")
    private String tipoStatus;

    private String status;

    public Status(Long idStatus) {
        this.idStatus = idStatus;
    }
}
