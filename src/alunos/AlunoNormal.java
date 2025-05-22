package alunos;

public class AlunoNormal extends Aluno {

    public AlunoNormal(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
    }

    @Override
    public boolean podeFazerMaisDisciplinas(int totalDisciplinas) {
        return true;
    }

    @Override
    public boolean recebeNota() {
        return true;
    }

    @Override
    public void mostrarResumo() {
        System.out.println("Aluno Normal: " + getNome());
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Curso: " + getCurso());
        System.out.println("Recebe nota? Sim");
        System.out.println();
    }
}
