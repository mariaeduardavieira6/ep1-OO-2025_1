package disciplinaturma;

import cadastro.CadastroAlunos;
import alunos.Aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	private Disciplina disciplina;
	private String professor;
	private String semestre;
	private String formaAvaliacao;
	private boolean presencial;
	private String sala;
	private String horario;
	private int capacidadeMaxima;
	private List<String> alunosMatriculados;
	private CadastroAlunos cadastroAlunos;

	public Turma(Disciplina disciplina, String professor, String semestre, String formaAvaliacao, boolean presencial,
			String sala, String horario, int capacidadeMaxima, CadastroAlunos cadastroAlunos) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.semestre = semestre;
		this.formaAvaliacao = formaAvaliacao;
		this.presencial = presencial;
		this.horario = horario;
		this.capacidadeMaxima = capacidadeMaxima;
		this.cadastroAlunos = cadastroAlunos;
		this.alunosMatriculados = new ArrayList<>();
		this.sala = presencial ? sala : "Remota";
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public String getProfessor() {
		return professor;
	}

	public String getSemestre() {
		return semestre;
	}

	public String getFormaAvaliacao() {
		return formaAvaliacao;
	}

	public boolean isPresencial() {
		return presencial;
	}

	public String getSala() {
		return sala;
	}

	public String getHorario() {
		return horario;
	}

	public int getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public int getCapacidade() {
		return capacidadeMaxima;
	}

	public List<String> getAlunosMatriculados() {
		return alunosMatriculados;
	}

	public String getCodigo() {
		return disciplina.getCodigo();
	}

	public boolean adicionarAluno(String matricula) {
		if (alunosMatriculados.contains(matricula)) {
			System.out.println("Aluno já está matriculado nesta turma.");
			return false;
		}

		if (alunosMatriculados.size() >= capacidadeMaxima) {
			System.out.println("Turma cheia. Não é possível adicionar mais alunos.");
			return false;
		}

		Aluno aluno = cadastroAlunos.buscarAlunoPorMatricula(matricula);
		if (aluno == null) {
			System.out.println("Aluno com matrícula " + matricula + " não encontrado.");
			return false;
		}
		
		if (aluno.isSemestreTrancado()) {
	        System.out.println("Aluno " + aluno.getNome() + " está com o semestre trancado e não pode se matricular.");
	        return false;
		}

		for (String preReq : disciplina.getPreRequisitos()) {
			if (!aluno.jaCursou(preReq)) {
				System.out.println("Aluno " + aluno.getNome()
						+ " não pode ser matriculado. Pré-requisito não atendido: " + preReq);
				return false;
			}
		}

		if (!aluno.podeFazerMaisDisciplinas(aluno.getDisciplinasCursando().size())) {
			System.out.println("Aluno não pode cursar mais disciplinas.");
			return false;
		}

		alunosMatriculados.add(matricula);
		aluno.adicionarDisciplinaCursando(disciplina.getCodigo());
		System.out.println(
				"Aluno " + aluno.getNome() + " matriculado com sucesso na disciplina " + disciplina.getCodigo());
		return true;
}
		

	public boolean removerAluno(String matricula) {
		return alunosMatriculados.remove(matricula);
	}

	@Override
	public String toString() {
		return "Turma da disciplina " + disciplina.getNome() + " (" + disciplina.getCodigo() + ") - " + "Professor: "
				+ professor + " | Semestre: " + semestre + "\nForma de Avaliação: " + formaAvaliacao + " | Modalidade: "
				+ (presencial ? "Presencial" : "Remota") + " | Sala: " + sala + " | Horário: " + horario
				+ "\nCapacidade: " + capacidadeMaxima + " alunos | Matriculados: " + alunosMatriculados.size();
	}
}