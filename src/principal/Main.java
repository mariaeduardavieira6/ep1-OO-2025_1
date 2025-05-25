package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import alunos.Aluno;
import alunos.AlunoEspecial;
import alunos.AlunoNormal;
import cadastro.CadastroAlunos;
import disciplinaturma.Disciplina;
import disciplinaturma.Turma;

public class Main {

    private static List<Disciplina> listaDisciplinas = new ArrayList<>();
    private static List<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CadastroAlunos cadastroAlunos = new CadastroAlunos();

        menuPrincipal(sc, cadastroAlunos);

        sc.close();
    }

    private static void menuPrincipal(Scanner sc, CadastroAlunos cadastroAlunos) {
        int opcao;
        do {
            System.out.println("\nMENU PRINCIPAL ");
            System.out.println("1. Menu Alunos");
            System.out.println("2. Menu Disciplina/Turma");
            System.out.println("3. Menu Avaliação/Frequência");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    menuAlunos(sc, cadastroAlunos);
                    break;
                case 2:
                    menuDisciplinaTurma(sc, cadastroAlunos);
                    break;
                case 3:
                    menuAvaliacaoFrequencia(sc);
                    break;
                case 4:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void menuAlunos(Scanner sc, CadastroAlunos cadastroAlunos) {
        int opcao;
        do {
            System.out.println("\nMenu Alunos ");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Sair do menu de alunos");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarAlunos(sc, cadastroAlunos);
                    break;
                case 2:
                    listarAlunos(cadastroAlunos);
                    break;
                case 3:
                    System.out.println("Saindo do menu de alunos...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 3);
    }

    private static void listarAlunos(CadastroAlunos cadastroAlunos) {
        System.out.println("\nLista de Alunos:");
        if (cadastroAlunos.listarAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno aluno : cadastroAlunos.listarAlunos()) {
            aluno.mostrarResumo();
        }
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
    }

    private static Disciplina cadastrarDisciplina(Scanner sc) {
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

        return disciplina;
    }

    private static void cadastrarTurma(Scanner sc, Disciplina disciplina, CadastroAlunos cadastroAlunos) {
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

        listaTurmas.add(turma);

        System.out.println("\nTurma cadastrada:");
        System.out.println(turma);
    }

    private static void menuDisciplinaTurma(Scanner sc, CadastroAlunos cadastroAlunos) {
        int opcao;
        do {
            System.out.println("\nMenu Disciplina/Turma");
            System.out.println("1. Cadastrar Disciplina");
            System.out.println("2. Cadastrar Turma");
            System.out.println("3. Listar Turmas");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    Disciplina novaDisciplina = cadastrarDisciplina(sc);
                    listaDisciplinas.add(novaDisciplina);
                    break;
                case 2:
                    if (listaDisciplinas.isEmpty()) {
                        System.out.println("Nenhuma disciplina cadastrada. Cadastre uma disciplina primeiro.");
                    } else {
                        System.out.println("\nDisciplinas disponíveis:");
                        for (int i = 0; i < listaDisciplinas.size(); i++) {
                            System.out.println((i + 1) + ". " + listaDisciplinas.get(i).getNome());
                        }
                        System.out.print("Escolha o número da disciplina: ");
                        int indice = Integer.parseInt(sc.nextLine()) - 1;
                        if (indice >= 0 && indice < listaDisciplinas.size()) {
                            cadastrarTurma(sc, listaDisciplinas.get(indice), cadastroAlunos);
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nTurmas cadastradas:");
                    if (listaTurmas.isEmpty()) {
                        System.out.println("Nenhuma turma cadastrada.");
                    } else {
                        for (Turma turma : listaTurmas) {
                            System.out.println(turma);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private static void menuAvaliacaoFrequencia(Scanner sc) {
        System.out.println("Menu de Avaliação e Frequência ainda não implementado.");
       
    }
}
