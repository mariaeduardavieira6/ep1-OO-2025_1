package alunos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private List<String> disciplinasCursando;
    private List<String> disciplinasConcluidas;
    private List<String> disciplinasTrancadas;
    private boolean semestreTrancado = false;
    


    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinasCursando = new ArrayList<>();
        this.disciplinasConcluidas = new ArrayList<>();
        this.disciplinasTrancadas = new ArrayList<>();
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
        if (!disciplinasCursando.contains(codigoDisciplina)) {
            disciplinasCursando.add(codigoDisciplina);
        }
    }

    public List<String> getDisciplinasConcluidas() {
        return disciplinasConcluidas;
    }

    public void adicionarDisciplinaConcluida(String codigoDisciplina) {
        if (!disciplinasConcluidas.contains(codigoDisciplina)) {
            disciplinasConcluidas.add(codigoDisciplina);
        }
    }
    
    public boolean jaCursou(String codigoDisciplina) {
        return disciplinasConcluidas.contains(codigoDisciplina);
    }

    
    public List<String> getDisciplinasTrancadas() {
        return disciplinasTrancadas;
    }

    public void trancarDisciplina(String codigoDisciplina) {
        if (!disciplinasTrancadas.contains(codigoDisciplina)) {
            disciplinasTrancadas.add(codigoDisciplina);
        }
    }

    public void destrancarDisciplina(String codigoDisciplina) {
        disciplinasTrancadas.remove(codigoDisciplina);
    }

    public boolean isDisciplinaTrancada(String codigoDisciplina) {
        return disciplinasTrancadas.contains(codigoDisciplina);
    }

    public boolean isSemestreTrancado() {
        return semestreTrancado;
    }

    public void setSemestreTrancado(boolean semestreTrancado) {
        this.semestreTrancado = semestreTrancado;
    }

    public void trancarSemestre() {
        this.semestreTrancado = true;
    }

    public void destrancarSemestre() {
        this.semestreTrancado = false;
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
