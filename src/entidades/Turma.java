package entidades;

import java.util.HashSet;
import java.util.Set;

import entidades.ENUM.MetodoDeAvaliacao;
import entidades.ENUM.Modalidade;

public class Turma {
    private Integer numeroTurma;
    private Professor professor;
    private Integer semestre;
    private MetodoDeAvaliacao metodoDeAvaliacao;
    private Modalidade modoDeParticipacao;
    private HorarioDeAula horarioDeAula;
    private String sala;
    private Integer maxAlunos;
    private Disciplina disciplina;
    private Set<Aluno> alunos = new HashSet<>();// escrever metodos

    public Turma() {
    }

    public Turma(Integer turma, Professor professor, Integer semestre, MetodoDeAvaliacao metodoDeAvaliacao,
            Modalidade modoDeParticipacao, HorarioDeAula horarioDeAula, Integer maxAlunos) {
        this.numeroTurma = turma;
        this.professor = professor;
        this.semestre = semestre;
        this.metodoDeAvaliacao = metodoDeAvaliacao;
        this.modoDeParticipacao = modoDeParticipacao;
        this.modoDeParticipacao = modoDeParticipacao;
        this.horarioDeAula = horarioDeAula;
        this.maxAlunos = maxAlunos;
    }

    public Integer getNumeroTurma() {
        return numeroTurma;
    }

    public void setNumeroTurma(Integer numeroTurma) {
        this.numeroTurma = numeroTurma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public MetodoDeAvaliacao getMetodoDeAvaliacao() {
        return metodoDeAvaliacao;
    }

    public void setMetodoDeAvaliacao(MetodoDeAvaliacao metodoDeAvaliacao) {
        this.metodoDeAvaliacao = metodoDeAvaliacao;
    }

    public Modalidade getModoDeParticipacao() {
        return modoDeParticipacao;
    }

    public void setModoDeParticipacao(Modalidade modoDeParticipacao) {
        this.modoDeParticipacao = modoDeParticipacao;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        if (modoDeParticipacao == Modalidade.valueOf(2))
            this.sala = null;
        else
            this.sala = sala;
    }

    public Integer getMaxAlunos() {
        return maxAlunos;
    }

    public HorarioDeAula getHorarioDeAula() {
        return horarioDeAula;
    }

    public void setHorarioDeAula(HorarioDeAula horarioDeAula) {
        this.horarioDeAula = horarioDeAula;
    }

    public void setMaxAlunos(Integer maxAlunos) {
        this.maxAlunos = maxAlunos;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return getNumeroTurma() + "," + getProfessor().getNome() + "," + getSemestre() + "," + getModoDeParticipacao()
                + ","
                + getMetodoDeAvaliacao() + ","
                + getHorarioDeAula().toString()
                + "," + getSala() + "," + getMaxAlunos() + "," + getDisciplina().getNome();
    }
}
