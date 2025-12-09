import models.*;

import java.util.*;

public class Main {
    private static final List<Candidato> candidatos = new ArrayList<>();
    private static final List<Eleitor> eleitores = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private static void cadastrarCandidato() {
        System.out.println("""
                === Cadastro Candidato ===
                0 - Sair
                1 - Pessoa Fisica
                2 - Pessoa Juridica
                ==========================
                """);
        int opt = sc.nextInt();

        if (opt == 0)
            return;

        System.out.println("Insira o documento do candidato");
        String documento = sc.next();
        System.out.println("Insira o estado do candidato (ES ou SP)");
        String estadoStr = sc.next();
        Optional<Estado> estadoOpt = Estado.fromString(estadoStr);
        System.out.println("Insira o patrimonio do candidato");
        double patrimonio = sc.nextDouble();
        if (estadoOpt.isEmpty()) {
            System.out.println("Estado inválido");
            return;
        }

        if (opt == 1) {
            System.out.println("Insira o nome do candidato:");
            String nome = sc.next();
            System.out.println("Insira o sobrenome do candidato:");
            String sobrenome = sc.next();

            try {
                Candidato candidato = new PessoaFisica(documento, estadoOpt.get(), patrimonio, nome, sobrenome);

                if (candidato.documentoEhInvalido())
                    throw new IllegalArgumentException("Documento invalido");

                candidatos.add(candidato);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao cadastrar Pessoa Fisica");
                System.out.println(e.getMessage());
            }
        }

        if (opt == 2) {
            System.out.println("Insira a razao social do candidato:");
            String razaoSocial = sc.next();

            try {
                Candidato candidato = new PessoaJuridica(documento, estadoOpt.get(), patrimonio, razaoSocial);

                if (candidato.documentoEhInvalido())
                    throw new IllegalArgumentException("Documento invalido");

                candidatos.add(candidato);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao cadastrar Pessoa Juridica");
                System.out.println(e.getMessage());
            }
        }
    }

    private static void cadastrarEleitor() {
        System.out.println("Insira o titulo do eleitor");
        String titulo = sc.next();
        System.out.println("Insira o nome do eleitor:");
        String nome = sc.next();
        System.out.println("Insira o estado do eleitor:");
        String estadoStr = sc.next();
        Optional<Estado> estado = Estado.fromString(estadoStr);

        if (estado.isEmpty()) {
            System.out.println("Estado invalido");
            return;
        }

        Eleitor eleitor = new Eleitor(titulo, nome, estado.get());
        eleitores.add(eleitor);
    }

    private static void cadastrarVoto() {
        System.out.println("Insira o nome do eleitor:");
        String nome = sc.next();
        Optional<Eleitor> eleitorOpt = eleitores.stream()
                .filter(eleitor -> eleitor.getNome().equals(nome)).findFirst();

        if (eleitorOpt.isEmpty()) {
            System.out.println("Eleitor invalido");
            return;
        }

        System.out.println("Insira o documento do candidato:");
        String documento = sc.next();

        Optional<Candidato> candidatoOpt = candidatos.stream().filter(
                candidato -> candidato.getDocumento().equals(documento)
        ).findFirst();

        if (candidatoOpt.isEmpty()) {
            System.out.println("Candidato invalido");
            return;
        }

        Eleitor eleitor = eleitorOpt.get();
        Candidato candidato = candidatoOpt.get();

        if (!eleitor.podeVotarEm(candidato)) {
            System.out.println("O eleitor nao pode votar nesse candidato");
            return;
        }

        Voto voto = new Voto(candidato, eleitor);
        List<Voto> votosCandidato = candidato.getVotos();
        votosCandidato.add(voto);
    }

    private static void imprimirVotos() {
        System.out.println("Total de votos: " + Voto.getQuantidadeVotosTotais());
        System.out.println("Votos por candidato:");
        candidatos.forEach(candidato -> System.out.printf("%s: %d\n", candidato.getDisplayName(), candidato.getVotos().size()));
    }

    public static void main(String[] args) {
        int opt;

        do {
            System.out.println("""
                === MENU ===
                0 - Sair
                1 - Cadastrar Candidato
                2 - Cadastrar Eleitor
                3 - Cadastrar Voto
                4 - Imprimir Candidatos
                5 - Imprimir Eleitores
                6 - Imprimir Votos
                ============
                """);
            opt = sc.nextInt();

            if (opt > 6) {
                System.out.println("Opcao invalida");
                continue;
            }

            switch (opt) {
                case 1 -> cadastrarCandidato();
                case 2 -> cadastrarEleitor();
                case 3 -> cadastrarVoto();
                case 4 -> candidatos.forEach(System.out::println);
                case 5 -> eleitores.forEach(System.out::println);
                case 6 -> imprimirVotos();
            }
        } while (opt != 0);
    }
}
