package models;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Estado {
    ES("ES"), SP("SP");

    private final String nome;
    private static final Map<String, Estado> map = new HashMap<>();

    static {
        for (Estado estado : Estado.values()) {
            map.put(estado.nome, estado);
        }
    }

    Estado(String nome) {
        this.nome = nome;
    }

    public static Optional<Estado> fromString(String nome) {
        return Optional.ofNullable(map.get(nome));
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
