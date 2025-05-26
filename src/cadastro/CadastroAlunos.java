package cadastro;

import alunos.Aluno;
import java.util.List;
import java.util.ArrayList;

public class CadastroAlunos {

    private List<Aluno> listaAlunos;

    public CadastroAlunos() {
        this.listaAlunos = GerenciadorDados.carregarAlunos();
    }
    public void setListaAlunos(List<Aluno> alunos) {
        this.listaAlunos = alunos;
    }

    public boolean cadastrarAluno(Aluno aluno) {
        if (buscarAlunoPorMatricula(aluno.getMatricula()) == null) {
            listaAlunos.add(aluno);
            GerenciadorDados.salvarAlunos(listaAlunos);  // salva CSV
            return true;
        }
        System.out.println("Erro: já existe um aluno com a matrícula " + aluno.getMatricula());
        return false;
    }

    public boolean editarAluno(String matricula, String novoNome, String novoCurso) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            aluno.setNome(novoNome);
            aluno.setCurso(novoCurso);
            GerenciadorDados.salvarAlunos(listaAlunos);
            return true;
        }
        return false;
    }

    public boolean removerAlunoPorMatricula(String matricula) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            listaAlunos.remove(aluno);
            GerenciadorDados.salvarAlunos(listaAlunos);
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

    public boolean registrarConclusaoDisciplina(String matricula, String codigoDisciplina) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return false;
        }
        aluno.adicionarDisciplinaConcluida(codigoDisciplina);
        GerenciadorDados.salvarAlunos(listaAlunos);
        System.out.println("Disciplina " + codigoDisciplina + " registrada como concluída para " + aluno.getNome());
        return true;
    }

    public void exibirTodosAlunos() {
        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : listaAlunos) {
                aluno.mostrarResumo();
            }
        }
    }
}
