package entidades;

public class TurmaAluno {
    private Aluno aluno;
    private Turma turma;
    private Double nota;
    private Double frequencia;

    public TurmaAluno(Aluno aluno, Turma turma, Double nota, double frequencia) {
        this.aluno = aluno;
        this.turma = turma;
        this.nota = nota;
        this.frequencia = frequencia;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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
        return getAluno().getMatricula() + "," +
                getTurma().getNumeroTurma() + "," +
                getNota() + "," +
                getFrequencia();
    }
}
