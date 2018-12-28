package projetoes.projetoes.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Funcionario extends Pessoa {
    public Funcionario(String name) {
        super(name);
    }
    public Funcionario(String name, LocalDateTime date) {

        super(name,date);
    }
    public String getName() {
        return super.getName();
    }
}