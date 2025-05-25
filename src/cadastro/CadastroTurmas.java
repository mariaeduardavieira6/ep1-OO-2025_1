package cadastro;

import java.util.ArrayList;
import java.util.List;
import disciplinaturma.Turma;

public class CadastroTurmas {

    private List<Turma> listaTurmas;

    public CadastroTurmas() {
        this.listaTurmas = new ArrayList<>();
    }

    public boolean adicionarTurma(Turma turma) {
        if (buscarTurma(turma.getCodigo()) == null) {
            listaTurmas.add(turma);
            return true;
        }
        return false; 
    }

    public boolean removerTurma(String codigo) {
        return listaTurmas.removeIf(t -> t.getCodigo().equals(codigo));
    }

    public Turma buscarTurma(String codigo) {
        for (Turma t : listaTurmas) {
            if (t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }

    public List<Turma> listarTurmas() {
        return new ArrayList<>(listaTurmas);
    }

    public List<Turma> listarTurmasPorDisciplina(String codigoDisciplina) {
        List<Turma> turmasPorDisciplina = new ArrayList<>();
        for (Turma t : listaTurmas) {
            if (t.getDisciplina().getCodigo().equals(codigoDisciplina)) {
                turmasPorDisciplina.add(t);
            }
        }
        return turmasPorDisciplina;
    }
    
    
    
    public void gerarRelatorioPorTurmas() {
        if (listaTurmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        System.out.println("RELATÓRIO POR TURMAS ");
        for (Turma turma : listaTurmas) {
            System.out.println(turma);
        }
    }

    public void gerarRelatorioPorDisciplina(String codigoDisciplina) {
        boolean encontrou = false;
        System.out.println("RELATÓRIO POR DISCIPLINA: " + codigoDisciplina + " ");

        for (Turma turma : listaTurmas) {
            if (turma.getDisciplina().getCodigo().equalsIgnoreCase(codigoDisciplina)) {
                System.out.println(turma);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma turma encontrada para essa disciplina.");
        }
    }

    public void gerarRelatorioPorProfessor(String nomeProfessor) {
        boolean encontrou = false;
        System.out.println("RELATÓRIO POR PROFESSOR: " + nomeProfessor + " ");

        for (Turma turma : listaTurmas) {
            if (turma.getProfessor().equalsIgnoreCase(nomeProfessor)) {
                System.out.println(turma);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma turma encontrada para esse professor.");
        }
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
