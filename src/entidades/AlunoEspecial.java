package entidades;

import java.util.ArrayList;
import java.util.List;

public class AlunoEspecial extends Aluno {
    private Boolean alunoEspecial;
    private Integer numeroDeMateriasMax;
    private List<Turma> turmas = new ArrayList<>();

    public AlunoEspecial() {
        super();
        this.alunoEspecial = Boolean.valueOf(false);
        this.numeroDeMateriasMax = 2;
    }

    public AlunoEspecial(String nome, Integer matricula, String curso, Boolean trancamento, Boolean alunoEspecial) {
        super(nome, matricula, curso, trancamento);
        this.alunoEspecial = Boolean.valueOf(alunoEspecial);
        this.numeroDeMateriasMax = 2;
    }

    public Boolean getAlunoEspecial() {
        return alunoEspecial;
    }

    public void setAlunoEspecial(Boolean alunoEspecial) {
        this.alunoEspecial = alunoEspecial;
    }

    public Integer getNumeroDeMateriasMax() {
        return numeroDeMateriasMax;
    }

    @Override
    public void setTurma(Turma turma) {
        if (numeroDeMateriasMax > 0) {
            turmas.add(turma);
            numeroDeMateriasMax--;
        } else {
            System.out.println("Aluno especial atingiu numero maximo de materias");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Turma turma : this.turmas) {
            sb.append(turma.getNumeroTurma()).append(",");
        }

        return super.toString() + getAlunoEspecial() + "," + getNumeroDeMateriasMax() + "," + sb.toString();
    }
}
