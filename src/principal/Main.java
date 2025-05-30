package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import alunos.Aluno;
import alunos.AlunoEspecial;
import alunos.AlunoNormal;
import avaliacao.Avaliacao;
import avaliacao.ControleAvaliacaoFrequencia;
import cadastro.CadastroAlunos;
import cadastro.CadastroTurmas;
import cadastro.GerenciadorDados;
import disciplinaturma.Disciplina;
import disciplinaturma.Turma;

public class Main {
	private static List<Disciplina> listaDisciplinas = new ArrayList<>();
    private static List<Turma> listaTurmas = new ArrayList<>();
	private static ControleAvaliacaoFrequencia controleAvaliacao = new ControleAvaliacaoFrequencia();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Aluno> alunos = GerenciadorDados.carregarDados();
        CadastroAlunos cadastroAlunos = new CadastroAlunos();
        cadastroAlunos.setListaAlunos(alunos);
        CadastroTurmas cadastroTurmas = new CadastroTurmas();
        menuPrincipal(sc, cadastroAlunos, cadastroTurmas);
        menuRelatorios(cadastroTurmas, sc);
        cadastroAlunos.exibirTodosAlunos();
        sc.close();
    }

    private static void menuPrincipal(Scanner sc, CadastroAlunos cadastroAlunos, CadastroTurmas cadastroTurmas) {
        int opcao;
        do {
            System.out.println("\nMENU PRINCIPAL ");
            System.out.println("1. Menu Alunos");
            System.out.println("2. Menu Disciplina/Turma");
            System.out.println("3. Menu Avaliação/Frequência");
            System.out.println("4. Gerar Relatório Geral");
            System.out.println("5. Sair");
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
                menuRelatorios(cadastroTurmas, sc);
                break;
            case 5:
                System.out.println("Encerrando o programa...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    } while (opcao != 5);
}
    
    private static void menuRelatorios(CadastroTurmas cadastroTurmas, Scanner sc) {
        boolean relatoriosAtivos = true;

        while (relatoriosAtivos) {
            System.out.println("\nMenu Relatórios:");
            System.out.println("1 - Relatório por Turma");
            System.out.println("2 - Relatório por Disciplina");
            System.out.println("3 - Relatório por Professor");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            int opcaoRel = sc.nextInt();
            sc.nextLine(); 

            switch (opcaoRel) {
                case 1:
                    System.out.print("Digite o código da turma (código da disciplina): ");
                    String codigoTurma = sc.nextLine();
                    Turma turma = cadastroTurmas.buscarTurma(codigoTurma);
                    if (turma != null) {
                        System.out.println("Código da Turma: " + turma.getCodigo());
                        System.out.println("Disciplina: " + turma.getDisciplina().getNome());
                        System.out.println("Professor: " + turma.getProfessor());
                        System.out.println("Semestre: " + turma.getSemestre());
                        System.out.println("Alunos matriculados:");
                        if (turma.getAlunosMatriculados().isEmpty()) {
                            System.out.println("Nenhum aluno matriculado.");
                        } else {
                            for (String matricula : turma.getAlunosMatriculados()) {
                            	Aluno aluno = turma.getCadastroAlunos().buscarAlunoPorMatricula(matricula);

                                if (aluno != null) {
                                    System.out.println("- " + aluno.getNome() + " (Matrícula: " + matricula + ")");
                                } else {
                                    System.out.println("- Matrícula não encontrada: " + matricula);
                                }
                            }
                        }
                    } else {
                        System.out.println("Turma não encontrada.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o código da disciplina: ");
                    String codigoDisc = sc.nextLine();
                    var turmasDisc = cadastroTurmas.listarTurmasPorDisciplina(codigoDisc);
                    if (!turmasDisc.isEmpty()) {
                        for (Turma t : turmasDisc) {
                            System.out.println("Turma: " + t.getCodigo() + " | Professor: " + t.getProfessor());
                        }
                    } else {
                        System.out.println("Nenhuma turma encontrada para essa disciplina.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do professor: ");
                    String nomeProf = sc.nextLine();
                    var turmasProf = cadastroTurmas.listarTurmas().stream()
                        .filter(t -> t.getProfessor().equalsIgnoreCase(nomeProf))
                        .toList();
                    if (!turmasProf.isEmpty()) {
                        for (Turma t : turmasProf) {
                            System.out.println("Turma: " + t.getCodigo() + " | Disciplina: " + t.getDisciplina().getNome());
                        }
                    } else {
                        System.out.println("Nenhuma turma encontrada para esse professor.");
                    }
                    break;

                case 0:
                    relatoriosAtivos = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuAlunos(Scanner sc, CadastroAlunos cadastroAlunos) {
        int opcao;
        do {
            System.out.println("\nMenu Alunos");
            System.out.println("1. Cadastrar aluno normal");
            System.out.println("2. Cadastrar aluno especial");
            System.out.println("3. Listar alunos");
            System.out.println("4. Editar aluno");
            System.out.println("5. Remover aluno");
            System.out.println("6. Registrar disciplina concluída");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrarAlunoNormal(sc, cadastroAlunos);
                case 2 -> cadastrarAlunoEspecial(sc, cadastroAlunos);
                case 3 -> listarAlunos(cadastroAlunos);
                case 4 -> editarAluno(sc, cadastroAlunos);
                case 5 -> removerAluno(sc, cadastroAlunos);
                case 6 -> registrarConclusaoDisciplina(sc, cadastroAlunos);
                case 7 -> System.out.println("Saindo do menu de alunos...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
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

    private static void cadastrarAlunoNormal(Scanner sc, CadastroAlunos cadastro) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Curso: ");
        String curso = sc.nextLine();

        Aluno aluno = new AlunoNormal(nome, matricula, curso);
        if (cadastro.cadastrarAluno(aluno)) {
            System.out.println("Aluno normal cadastrado com sucesso!");
        }
    }

    private static void cadastrarAlunoEspecial(Scanner sc, CadastroAlunos cadastro) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Curso: ");
        String curso = sc.nextLine();

        Aluno aluno = new AlunoEspecial(nome, matricula, curso);
        if (cadastro.cadastrarAluno(aluno)) {
            System.out.println("Aluno especial cadastrado com sucesso!");
        }
    }

    
    private static void editarAluno(Scanner sc, CadastroAlunos cadastro) {
        System.out.print("Digite a matrícula do aluno para editar: ");
        String matricula = sc.nextLine();
        System.out.print("Novo nome: ");
        String novoNome = sc.nextLine();
        System.out.print("Novo curso: ");
        String novoCurso = sc.nextLine();

        if (cadastro.editarAluno(matricula, novoNome, novoCurso)) {
            System.out.println("Aluno atualizado com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void removerAluno(Scanner sc, CadastroAlunos cadastro) {
        System.out.print("Digite a matrícula do aluno a remover: ");
        String matricula = sc.nextLine();

        if (cadastro.removerAlunoPorMatricula(matricula)) {
            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void registrarConclusaoDisciplina(Scanner sc, CadastroAlunos cadastro) {
        System.out.print("Matrícula do aluno: ");
        String matricula = sc.nextLine();
        System.out.print("Código da disciplina concluída: ");
        String codigo = sc.nextLine();

        cadastro.registrarConclusaoDisciplina(matricula, codigo);
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
 
        System.out.print("Forma de avaliação (1 para média simples, 2 para média ponderada): ");
        int formaAvaliacao = Integer.parseInt(sc.nextLine());


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
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Cadastrar turma");
            System.out.println("3. Listar disciplinas");
            System.out.println("4. Sair do menu de disciplina/turma");
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
                        System.out.println("Disciplinas disponíveis:");
                        for (int i = 0; i < listaDisciplinas.size(); i++) {
                            System.out.println((i + 1) + ". " + listaDisciplinas.get(i).getNome() + " (" + listaDisciplinas.get(i).getCodigo() + ")");
                        }
                        System.out.print("Escolha o número da disciplina para associar à turma: ");
                        int indice = Integer.parseInt(sc.nextLine()) - 1;

                        if (indice >= 0 && indice < listaDisciplinas.size()) {
                            Disciplina disciplinaSelecionada = listaDisciplinas.get(indice);
                            cadastrarTurma(sc, disciplinaSelecionada, cadastroAlunos);
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    }
                    break;
                case 3:
                    if (listaDisciplinas.isEmpty()) {
                        System.out.println("Nenhuma disciplina cadastrada.");
                    } else {
                        System.out.println("Disciplinas cadastradas:");
                        for (Disciplina d : listaDisciplinas) {
                            System.out.println(d);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Saindo do menu de disciplina/turma...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void menuAvaliacaoFrequencia(Scanner sc) {
    	boolean voltarMenu = false;

        while (!voltarMenu) {
            System.out.println("\nMenu de Avaliação e Frequência ");
            System.out.println("1. Registrar avaliação e frequência");
            System.out.println("2. Consultar situação do aluno");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Matrícula do aluno: ");
                    String matricula = sc.nextLine();

                    System.out.print("Código da turma: ");
                    String turma = sc.nextLine();

                    System.out.print("Forma de avaliação (1 - simples | 2 - ponderada): ");
                    int forma = sc.nextInt();

                    Avaliacao avaliacao = new Avaliacao(forma);

                    System.out.print("Nota P1: ");
                    double p1 = sc.nextDouble();
                    System.out.print("Nota P2: ");
                    double p2 = sc.nextDouble();
                    System.out.print("Nota P3: ");
                    double p3 = sc.nextDouble();
                    System.out.print("Nota dos trabalhos (L): ");
                    double l = sc.nextDouble();
                    System.out.print("Nota dos seminários (S): ");
                    double s = sc.nextDouble();

                    avaliacao.setNotas(p1, p2, p3, l, s);

                    System.out.print("Frequência (%): ");
                    double freq = sc.nextDouble();
                    sc.nextLine(); 
                    avaliacao.setFrequencia(freq);

                    controleAvaliacao.registrarAvaliacao(matricula, turma, avaliacao);
                    System.out.println("Avaliação registrada com sucesso!");
                    break;

                case 2:
                    System.out.print("Matrícula do aluno: ");
                    String matConsulta = sc.nextLine();

                    System.out.print("Código da turma: ");
                    String turmaConsulta = sc.nextLine();

                    String situacao = controleAvaliacao.getSituacaoAluno(matConsulta, turmaConsulta);
                    System.out.println("Situação: " + situacao);
                    break;

                case 3:
                    voltarMenu = true;
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}   