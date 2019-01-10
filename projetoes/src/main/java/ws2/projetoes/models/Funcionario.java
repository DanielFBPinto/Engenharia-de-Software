package ws2.projetoes.models;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Funcionario extends Pessoa {

    public Funcionario(String name)
    {
        super(name);
    }

    public Funcionario(String name, LocalDateTime date)
    {
        super(name,date);
    }

    public Funcionario(String name, LocalDateTime date,Integer numCC)
    {

        super(name,date,numCC);
    }

    public String getName()
    {
        return super.getName();
    }
}