package alunos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Aluno)) return false;
        Aluno outro = (Aluno) obj;
        return Objects.equals(this.matricula, outro.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    public abstract boolean podeFazerMaisDisciplinas(int totalDisciplinas);
    public abstract boolean recebeNota();
    public abstract void mostrarResumo();
}
