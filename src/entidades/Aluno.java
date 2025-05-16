package entidades;

import java.util.ArrayList;
import java.util.List;

import entidades.ENUM.MetodoDeAvaliacao;

public class Aluno {

    private Integer matricula;
    private String nome;
    private String curso;
    private Boolean trancamentoDeCurso;
    private Double nota;
    private double frequencia;
    private List<Turma> turmas = new ArrayList<>();

    public Aluno() {
    }

    public Aluno(String nome, Integer matricula, String curso, Boolean trancamento) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.trancamentoDeCurso = trancamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Boolean getTrancamentoDeCurso() {
        return trancamentoDeCurso;
    }

    public void setTrancamentoDeCurso(Boolean trancamentoDeCurso) {
        this.trancamentoDeCurso = trancamentoDeCurso;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota1, Double nota2, Double nota3, MetodoDeAvaliacao metodoDeAvaliacao) {

        if (metodoDeAvaliacao == MetodoDeAvaliacao.MEDIA_SIMPLES) {
            this.nota = (nota1 + nota2 + nota3) / 3;
        } else if (metodoDeAvaliacao == MetodoDeAvaliacao.MEDIA_PONDERADA) {
            this.nota = ((nota1 * 2) + (nota2 * 3) + (nota3 * 5)) / 10;
        } else {
            throw new IllegalArgumentException("Método de avaliação inválido");
        }
    }

    public List<Turma> getTurma() {
        return turmas;
    }

    public void setTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Turma turma : this.turmas) {
            sb.append(turma.getNumeroTurma()).append(",");
        }

        return getMatricula() + "," + getNome() + "," + getCurso() + "," + getTrancamentoDeCurso() + ","
                + sb.toString();
    }

}
