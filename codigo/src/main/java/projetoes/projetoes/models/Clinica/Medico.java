package projetoes.projetoes.models.Clinica;
import java.util.ArrayList;

public class Medico extends Pessoa
{
  private Integer cedulaMedica;
  private String especialidade;
  private ArrayList<Consulta> myConsulta = new ArrayList<>();
  private ArrayList<Horario> myHorarioMedico = new ArrayList<>();
}