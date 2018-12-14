package projetoes.projetoes.models;

import java.util.ArrayList;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Funcionario extends Pessoa {
    public Funcionario(String name) {
        super(name);
    }

    public String getName() {
        return super.getName();
    }
}