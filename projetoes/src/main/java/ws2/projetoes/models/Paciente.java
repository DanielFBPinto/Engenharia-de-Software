package ws2.projetoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Paciente extends Pessoa
{
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", orphanRemoval = true)
    private Set<Consulta> consultas = new HashSet<>();

    public Paciente(String name) {
        super(name);
    }



    public Paciente(String name, LocalDateTime dataNascimento, Integer numCC) {
        super(name, dataNascimento, numCC);
    }

    public Paciente(String name, LocalDateTime dataNascimento) {
        super(name, dataNascimento);
    }

    public boolean isFree(LocalDateTime dataConsulta)
    {
        for (Consulta consulta : this.consultas)
        {
            if (consulta.getData().equals(dataConsulta))
            {
                return false;
            }
        }
        return true;
    }

    public void addConsulta(Consulta consulta)
    {
        this.consultas.add(consulta);
        consulta.addPaciente(this);
    }
}