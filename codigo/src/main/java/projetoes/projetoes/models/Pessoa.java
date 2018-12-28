package projetoes.projetoes.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Pessoa extends BaseModel {
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataNascimento;
    private Integer numCC;

    public String getName() {
        return name;
    }

    public Pessoa(String name) {
        this.name = name;
    }

    public Pessoa(String name, LocalDateTime dataNascimento, Integer numCC) {
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.numCC = numCC;
    }
}