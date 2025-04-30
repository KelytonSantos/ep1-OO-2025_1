package entidades;

public class Professor {
    private String nome;
    private Integer matricula;

    public Professor() {
        this.nome = "";
        this.matricula = 0;
    }

    public Professor(Integer matricula, String nome) {
        this.nome = nome;
        this.matricula = matricula;
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

    @Override
    public String toString() {
        return getMatricula() + "," + getNome();
    }
}
