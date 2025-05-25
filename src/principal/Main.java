package principal;

import java.util.Scanner;

import alunos.Aluno;
import alunos.AlunoEspecial;
import alunos.AlunoNormal;
import cadastro.CadastroAlunos;
import disciplinaturma.Disciplina;
import disciplinaturma.Turma;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CadastroAlunos cadastroAlunos = new CadastroAlunos();
        
        cadastrarAlunos(sc, cadastroAlunos);
        
    }    
        private static void cadastrarAlunos(Scanner sc, CadastroAlunos cadastroAlunos) {
        
        System.out.println("Cadastro de Alunos ");
        System.out.print("Quantos alunos deseja cadastrar? ");
        int qtdAlunos = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < qtdAlunos; i++) {
            System.out.println("\nAluno " + (i + 1));
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Matrícula: ");
            String matricula = sc.nextLine();

            System.out.print("Curso: ");
            String curso = sc.nextLine();

            System.out.print("Tipo (normal/especial): ");
            String tipo = sc.nextLine().toLowerCase();

            Aluno novoAluno;
            if (tipo.equals("especial")) {
                novoAluno = new AlunoEspecial(nome, matricula, curso);
            } else {
                novoAluno = new AlunoNormal(nome, matricula, curso);
            }

            if (cadastroAlunos.cadastrarAluno(novoAluno)) {
                System.out.println("Aluno cadastrado com sucesso.");
            } else {
                System.out.println("Matrícula já existente. Aluno não cadastrado.");
            }
        }

        

        System.out.println("\nCadastro de Disciplina ");
        System.out.print("Nome da disciplina: ");
        String nomeDisc = sc.nextLine();

        System.out.print("Código da disciplina: ");
        String codigoDisc = sc.nextLine();

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = Integer.parseInt(sc.nextLine());

        Disciplina disciplina = new Disciplina(nomeDisc, codigoDisc, cargaHoraria);

        System.out.println("Quantos pré-requisitos essa disciplina tem?");
        int nPre = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < nPre; i++) {
            System.out.print("Código do pré-requisito " + (i + 1) + ": ");
            String preReq = sc.nextLine();
            disciplina.adicionarPreRequisito(preReq);
        }

        System.out.println("\nDisciplina cadastrada:");
        System.out.println(disciplina);

        
        
        System.out.println("\nCadastro de Turma ");
        System.out.print("Nome do professor: ");
        String professor = sc.nextLine();

        System.out.print("Semestre (ex: 2025.1): ");
        String semestre = sc.nextLine();

        System.out.print("Forma de avaliação: ");
        String formaAvaliacao = sc.nextLine();

        System.out.print("A turma é presencial? (true/false): ");
        boolean presencial = Boolean.parseBoolean(sc.nextLine());

        String sala = "";
        if (presencial) {
            System.out.print("Sala: ");
            sala = sc.nextLine();
        }

        System.out.print("Horário da turma: ");
        String horario = sc.nextLine();

        System.out.print("Capacidade máxima de alunos: ");
        int capacidade = Integer.parseInt(sc.nextLine());

        Turma turma = new Turma(disciplina, professor, semestre, formaAvaliacao, presencial, sala, horario, capacidade, cadastroAlunos);

        System.out.println("\nQuantos alunos deseja matricular na turma?");
        int qtdMatriculas = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < qtdMatriculas; i++) {
            System.out.print("Digite a matrícula do aluno #" + (i + 1) + ": ");
            String matricula = sc.nextLine();

            Aluno aluno = cadastroAlunos.buscarAlunoPorMatricula(matricula);

            if (aluno != null && turma.adicionarAluno(matricula)) {
                System.out.println("Aluno matriculado com sucesso.");
            } else {
                System.out.println("Não foi possível matricular o aluno (vaga cheia, matrícula repetida ou aluno não cadastrado).");
            }
        }

        System.out.println("\nTurma cadastrada:");
        System.out.println(turma);

        sc.close();
    }
}
