package entidades.ENUM;

public enum Modalidade {
    PRESENCIAL(1),
    ONLINE(2);

    private int code;

    private Modalidade(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Modalidade valueOf(int code) {
        for (Modalidade value : Modalidade.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo para Modalidade Invalido");
    }
}