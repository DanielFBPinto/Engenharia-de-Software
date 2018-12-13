package projetoes.projetoes.models;
import java.util.ArrayList;
import lombok.*;
import projetoes.projetoes.models.Funcionario;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Administrativo extends Funcionario
{

    public String cargo;

    public Administrativo(String cargo)
    {
        this.cargo = cargo;
    }

    public Administrativo(String name, String cargo)
    {
        super(name);
        this.cargo = cargo;
    }
}