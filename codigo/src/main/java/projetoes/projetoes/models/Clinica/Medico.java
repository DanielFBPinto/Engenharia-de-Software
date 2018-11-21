package projetoes.projetoes.models.Clinica;

import java.util.Vector;


public class Medico extends Pessoa {

  public Integer cedulaMedica;

  public String especialidade;

    /**
   * 
   * @element-type Consulta
   */
  public Vector  myConsulta;
    /**
   * 
   * @element-type HorarioMedico
   */
  public Vector  myHorarioMedico;

}