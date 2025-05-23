package disciplinaturma;

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

    public Turma(Disciplina disciplina, String professor, String semestre, String formaAvaliacao,
                 boolean presencial, String sala, String horario, int capacidadeMaxima) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.formaAvaliacao = formaAvaliacao;
        this.presencial = presencial;
        this.horario = horario;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = new ArrayList<>();

        if (presencial) {
            this.sala = sala;
        } else {
            this.sala = "Remota";
        }
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

    public boolean adicionarAluno(String matricula) {
        if (alunosMatriculados.size() < capacidadeMaxima && !alunosMatriculados.contains(matricula)) {
            alunosMatriculados.add(matricula);
            return true;
        }
        return false;
    }

    public boolean removerAluno(String matricula) {
        return alunosMatriculados.remove(matricula);
    }
    
    public String getCodigo() {
        return disciplina.getCodigo();
    }

    @Override
    public String toString() {
        return "Turma da disciplina " + disciplina.getNome() + " (" + disciplina.getCodigo() + ") - " +
                "Professor: " + professor + " | Semestre: " + semestre +
                "\nForma de Avaliação: " + formaAvaliacao +
                " | Modalidade: " + (presencial ? "Presencial" : "Remota") +
                " | Sala: " + sala + " | Horário: " + horario +
                "\nCapacidade: " + capacidadeMaxima + " alunos | Matriculados: " + alunosMatriculados.size();
    }
}
