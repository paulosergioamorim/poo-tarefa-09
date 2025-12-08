package models;

import java.time.LocalDateTime;

public final class Voto {
    private final Candidato candidato;
    private final Eleitor eleitor;

    public LocalDateTime getData() {
        return data;
    }

    public Eleitor getEleitor() {
        return eleitor;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    private final LocalDateTime data;
    private static int quantidadeVotosTotais = 0;

    public Voto(Candidato candidato, Eleitor eleitor) {
        if (!eleitor.podeVotarEm(candidato))
            throw new IllegalArgumentException("Eleitor nao pode votar nesse candidato de estado diferente");

        this.candidato = candidato;
        this.eleitor = eleitor;
        data = LocalDateTime.now();
        quantidadeVotosTotais++;
    }

    public static int getQuantidadeVotosTotais() {
        return quantidadeVotosTotais;
    }
}
