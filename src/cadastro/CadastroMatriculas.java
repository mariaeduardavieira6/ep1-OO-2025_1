package cadastro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alunos.Aluno;
import alunos.AlunoEspecial;
import disciplinaturma.Disciplina;
import disciplinaturma.Turma;

public class CadastroMatriculas {

    private Map<Turma, List<Aluno>> matriculas;

    public CadastroMatriculas() {
        this.matriculas = new HashMap<>();
    }

    public boolean matricularAlunoEmTurma(Aluno aluno, Turma turma) {
        if (turma.getAlunosMatriculados().size() >= turma.getCapacidade()) {
            System.out.println("Turma cheia.");
            return false;
        }

        if (aluno instanceof AlunoEspecial) {
            int totalMatriculas = contarMatriculasPorAluno(aluno);
            if (totalMatriculas >= 2) {
                System.out.println("Aluno especial só pode cursar até 2 disciplinas.");
                return false;
            }
        }

        Disciplina disciplina = turma.getDisciplina();
        for (String prereqCodigo : disciplina.getPreRequisitos()) {
            if (!aluno.getDisciplinasConcluidas().contains(prereqCodigo)) {
                System.out.println("Aluno não possui todos os pré-requisitos.");
                return false;
            }
        }


        matriculas.putIfAbsent(turma, new ArrayList<>());
        List<Aluno> alunos = matriculas.get(turma);

        if (alunos.contains(aluno)) {
            System.out.println("Aluno já está matriculado nessa turma.");
            return false;
        }

        alunos.add(aluno);
        turma.adicionarAluno(aluno.getMatricula()); 
        return true;
    }
    public int contarMatriculasPorAluno(Aluno aluno) {
        int contador = 0;
        for (List<Aluno> lista : matriculas.values()) {
            if (lista.contains(aluno)) {
                contador++;
            }
        }
        return contador;
    }

    public List<Turma> listarTurmasPorAluno(Aluno aluno) {
        List<Turma> turmas = new ArrayList<>();
        for (Map.Entry<Turma, List<Aluno>> entry : matriculas.entrySet()) {
            if (entry.getValue().contains(aluno)) {
                turmas.add(entry.getKey());
            }
        }
        return turmas;
    }

    public List<Aluno> listarAlunosPorTurma(Turma turma) {
        return matriculas.getOrDefault(turma, new ArrayList<>());
    }
}






   