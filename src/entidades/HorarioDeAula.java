package entidades;

public class HorarioDeAula {
    private String dia;
    private Integer hora;
    private Integer minuto;

    public HorarioDeAula() {

    }

    public HorarioDeAula(String dia, Integer hora, Integer minuto) {
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    @Override
    public String toString() {
        return getDia() + "," + getHora() + "," + getMinuto();
    }

}
