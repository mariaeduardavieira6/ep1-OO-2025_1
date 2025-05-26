package cadastro;

import java.util.ArrayList;
import java.util.List;
import disciplinaturma.Disciplina;

public class CadastroDisciplinas {
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public Disciplina buscarDisciplina(String codigo) {
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                return d;
            }
        }
        return null;
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }
}
