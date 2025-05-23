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
}