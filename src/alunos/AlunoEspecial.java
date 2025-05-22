package alunos;

public class AlunoEspecial extends Aluno {

    public AlunoEspecial(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
    }

    @Override
    public boolean podeFazerMaisDisciplinas(int totalDisciplinas) {
        if (totalDisciplinas >= 2) {
            System.out.println("Ops! Aluno Especial só pode fazer até 2 disciplinas ao mesmo tempo.");
            return false;
        }
        return true;
    }

    @Override
    public boolean recebeNota() {
        return false;
    }

    @Override
    public void mostrarResumo() {
        System.out.println("Aluno Especial: " + getNome());
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Curso: " + getCurso());
        System.out.println("Recebe nota? Só presença");
        System.out.println();
    }
}
