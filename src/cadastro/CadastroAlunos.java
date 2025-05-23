package cadastro;

import java.util.ArrayList;
import java.util.List;

import alunos.Aluno;


public class CadastroAlunos {

    private List<Aluno> listaAlunos;

    public CadastroAlunos() {
        this.listaAlunos = new ArrayList<>();
    }

    public boolean cadastrarAluno(Aluno aluno) {
        if (buscarAlunoPorMatricula(aluno.getMatricula()) == null) {
            listaAlunos.add(aluno);
            return true;
        }
        return false; 
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(listaAlunos); 
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    public boolean removerAlunoPorMatricula(String matricula) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            listaAlunos.remove(aluno);
            return true;
        }
        return false;
    }

    public boolean editarAluno(String matricula, String novoNome, String novoCurso) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            aluno.setNome(novoNome);
            aluno.setCurso(novoCurso);
            return true;
        }
        return false;
    }
}

