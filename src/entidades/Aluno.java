package entidades;

public class Aluno {

    private Integer matricula;
    private String nome;
    private String curso;
    private Boolean trancamentoDeCurso;

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

    @Override
    public String toString() {

        return getMatricula() + "," + getNome() + "," + getCurso() + "," + getTrancamentoDeCurso();
    }

}
