package entidades;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    private String nome;
    private Integer cargaHoraria;
    private List<Turma> turmas = new ArrayList<>();

    public Disciplina() {
        this.nome = "";
        this.cargaHoraria = 0;
    }

    public Disciplina(String nome, Integer cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void addTurma(Turma turma) {
        this.turmas.add(turma);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(",").append(cargaHoraria);

        for (Turma turma : turmas) {
            sb.append(",").append(turma.getNumeroTurma());
        }

        return sb.toString();
    }

}
