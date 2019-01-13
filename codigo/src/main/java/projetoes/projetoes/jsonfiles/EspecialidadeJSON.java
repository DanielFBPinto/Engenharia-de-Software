package projetoes.projetoes.jsonfiles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import projetoes.projetoes.models.BaseModel;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EspecialidadeJSON extends BaseModel
{
    private String name;

    public EspecialidadeJSON(String name)
    {
        this.name = name;
    }
}
