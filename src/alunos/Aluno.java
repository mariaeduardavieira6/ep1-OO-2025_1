package alunos;

import java.util.ArrayList;
import java.util.List;

public abstract class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private List<String> disciplinasCursando;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinasCursando = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public List<String> getDisciplinasCursando() {
        return disciplinasCursando;
    }

    public void adicionarDisciplinaCursando(String codigoDisciplina) {
        disciplinasCursando.add(codigoDisciplina);
    }

    public abstract boolean podeFazerMaisDisciplinas(int totalDisciplinas);

    public abstract boolean recebeNota();

    public abstract void mostrarResumo();
}
