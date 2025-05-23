package cadastro;

import disciplinaturma.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class CadastroDisciplinas {

    private List<Disciplina> listaDisciplinas;

    public CadastroDisciplinas() {
        this.listaDisciplinas = new ArrayList<>();
    }

    public boolean adicionarDisciplina(Disciplina disciplina) {
        if (buscarDisciplina(disciplina.getCodigo()) == null) {
            listaDisciplinas.add(disciplina);
            return true;
        }
        return false; 
    }

    public boolean removerDisciplina(String codigo) {
        return listaDisciplinas.removeIf(d -> d.getCodigo().equals(codigo));
    }

    public Disciplina buscarDisciplina(String codigo) {
        for (Disciplina d : listaDisciplinas) {
            if (d.getCodigo().equals(codigo)) {
                return d;
            }
        }
        return null;
    }

    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(listaDisciplinas);
    }

}