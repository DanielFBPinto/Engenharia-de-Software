package ws2.projetoes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Administrativo extends Funcionario {
    private String cargo;

    public Administrativo(String cargo) {
        this.cargo = cargo;
    }

    public Administrativo(String name, String cargo) {
        super(name);
        this.cargo = cargo;
    }
}