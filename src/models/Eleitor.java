package models;

public final class Eleitor {
    private final String tituloEleitoral;
    private final String nome;
    private final Estado estado;

    @Override
    public String toString() {
        return String.format("""
                === Eleitor ===
                Nome: %s
                Titulo: %s
                Estado: %s
                ===============
                """, nome, tituloEleitoral, estado.getNome());
    }

    public String getTituloEleitoral() {
        return tituloEleitoral;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public Eleitor(String tituloEleitoral, String nome, Estado estado) {
        this.tituloEleitoral = tituloEleitoral;
        this.nome = nome;
        this.estado = estado;
    }

    public boolean podeVotarEm(Candidato candidato) {
        return getEstado() == candidato.getEstado();
    }
}
