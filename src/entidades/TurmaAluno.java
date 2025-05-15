package entidades;

public class TurmaAluno {
    private Integer alunoMatricula;
    private Turma turma;
    private Double nota;
    private double frequencia;

    public TurmaAluno(Integer alunoMatricula, Turma turma, Double nota, double frequencia) {
        this.alunoMatricula = alunoMatricula;
        this.turma = turma;
        this.nota = nota;
        this.frequencia = frequencia;
    }

    public Integer getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAluno(Integer alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return getAlunoMatricula() + "," +
                getTurma().getNumeroTurma() + "," +
                getNota() + "," +
                getFrequencia();
    }
}
