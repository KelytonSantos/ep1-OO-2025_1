package entidades;

public class AlunoEspecial extends Aluno {
    private Boolean alunoEspecial;

    public AlunoEspecial() {
        super();
        this.alunoEspecial = Boolean.valueOf(false);
    }

    public AlunoEspecial(String nome, Integer matricula, String curso, Boolean trancamento, Boolean alunoEspecial) {
        super(nome, matricula, curso, trancamento);
        this.alunoEspecial = Boolean.valueOf(alunoEspecial);
    }

    public Boolean getAlunoEspecial() {
        return alunoEspecial;
    }

    public void setAlunoEspecial(Boolean alunoEspecial) {
        this.alunoEspecial = alunoEspecial;
    }

    public String toString() {
        return super.toString() + getAlunoEspecial();
    }
}
