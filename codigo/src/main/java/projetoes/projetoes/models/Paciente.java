package projetoes.projetoes.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Paciente extends Pessoa {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", orphanRemoval = true)
    private Set<Consulta> consultas = new HashSet<>();

    public Paciente(String name) {
        super(name);
    }

    public boolean isFree(LocalDateTime dataConsulta) {
        for (Consulta consulta : this.consultas) {
            if (consulta.getData().equals(dataConsulta)) {
                return false;
            }
        }
        return true;
    }

    public void addConsulta(Consulta consulta) {
        this.consultas.add(consulta);
        consulta.addPaciente(this);
    }
}