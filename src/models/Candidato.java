package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Candidato {
    private final String documento;
    private final Estado estado;
    private double patrimonio;
    private final List<Voto> votos =  new ArrayList<>();

    public abstract boolean documentoEhInvalido();

    public abstract double calcularExtraPatrimonio();

    public abstract String getDisplayName();

    public List<Voto> getVotos() {
        return votos;
    }

    public String getDocumento() {
        return documento;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(double patrimonio) {
        if (patrimonio < 0)
            throw new IllegalArgumentException("Patrimonio negativo");

        this.patrimonio = patrimonio;
    }

    public Candidato(String documento, Estado estado, double patrimonio) {
        this.documento = documento;
        this.estado = estado;
        setPatrimonio(patrimonio);
    }
}
