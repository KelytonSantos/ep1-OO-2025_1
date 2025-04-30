package entidades.ENUM;

public enum MetodoDeAvaliacao {
    MEDIA_SIMPLES("MEDIA SIMPLES"),
    MEDIA_PONDERADA("MEDIA PONDERADA");

    private String code;

    private MetodoDeAvaliacao(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MetodoDeAvaliacao fromCode(String code) {
        for (MetodoDeAvaliacao metodo : MetodoDeAvaliacao.values()) {// values é um array do meu enum
            if (metodo.getCode().equals(code)) {// compara cada metodo code (literalmente a string do enum) com o que
                                                // foi passado
                return metodo;// se achar devolve o enum
            }
        }
        throw new IllegalArgumentException("Nenhum MetodoDeAvaliacao encontrado para o código: " + code);
    }
}