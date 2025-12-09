package models;

public final class PessoaFisica extends Candidato {
    private final String nome;
    private final String sobrenome;

    public PessoaFisica(String documento, Estado estado, double patrimonio, String nome, String sobrenome) {
        super(documento, estado, patrimonio);
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    @Override
    public boolean documentoEhInvalido() {
        return getDocumento().length() != 11;
    }

    @Override
    public double calcularExtraPatrimonio() {
        return 0.05 * getPatrimonio();
    }

    @Override
    public String getDisplayName() {
        return nome;
    }

    @Override
    public String toString() {
        return String.format("""
                === Candidato ===
                Documento: %s
                Nome: %s
                Sobrenome: %s
                Estado: %s
                Patrimonio: %.2f
                ===============
                """, getDocumento(), getNome(), getSobrenome(), getEstado().getNome(), getPatrimonio() + calcularExtraPatrimonio());
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
}
