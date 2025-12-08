package models;

public final class PessoaJuridica extends Candidato {
    private final String razaoSocial;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public PessoaJuridica(String documento, Estado estado, double patrimonio, String razaoSocial) {
        super(documento, estado, patrimonio);
        this.razaoSocial = razaoSocial;
    }

    @Override
    public boolean documentoEhInvalido() {
        return getDocumento().length() != 14;
    }

    @Override
    public double calcularExtraPatrimonio() {
        return 0.10 * getPatrimonio();
    }

    @Override
    public String toString() {
        return String.format("""
                === Candidato ===
                Documento: %s
                Razao Social: %s
                Estado: %s
                Patrimonio: %.2f
                ===============
                """, getDocumento(), getRazaoSocial(), getEstado().getNome(), getPatrimonio() + calcularExtraPatrimonio());
    }
}
