package projetoes.projetoes.models;


import lombok.*;
import javax.persistence.*;

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