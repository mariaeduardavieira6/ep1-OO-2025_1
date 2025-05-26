package cadastro;

import alunos.Aluno;
import alunos.AlunoNormal;
import alunos.AlunoEspecial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDados {

    private static final String ARQUIVO = "alunos.csv";

    public static void salvarAlunos(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Aluno aluno : alunos) {
                String tipo = aluno instanceof AlunoNormal ? "normal" : "especial";
                String linha = tipo + ";" + aluno.getNome() + ";" + aluno.getMatricula() + ";" + aluno.getCurso();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    public static List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        
        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo de dados criado: " + ARQUIVO);
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo: " + e.getMessage());
              
            }
        };

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length < 4) continue;

                String tipo = partes[0];
                String nome = partes[1];
                String matricula = partes[2];
                String curso = partes[3];

                if ("normal".equalsIgnoreCase(tipo)) {
                    alunos.add(new AlunoNormal(nome, matricula, curso));
                } else if ("especial".equalsIgnoreCase(tipo)) {
                    alunos.add(new AlunoEspecial(nome, matricula, curso));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, iniciando lista vazia.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }

        return alunos;
    }

    public static List<Aluno> carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    
                    System.out.println("Arquivo de dados criado: " + ARQUIVO);
                }
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo: " + e.getMessage());
            }
            return new ArrayList<>();
        }
        return carregarAlunos();
    }

}
