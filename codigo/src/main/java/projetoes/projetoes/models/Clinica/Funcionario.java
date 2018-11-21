package projetoes.projetoes.models.Clinica;
import java.util.ArrayList;

public class Funcionario extends Pessoa
{
  private int idFuncionario;
  private String cargo;
  private ArrayList<Horario> myHorario = new ArrayList<>();
}