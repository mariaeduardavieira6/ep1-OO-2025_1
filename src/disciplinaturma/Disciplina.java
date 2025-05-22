package disciplinaturma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L; 

    private String nome;
    private String codigo;
    private int cargaHoraria;
    private List<String> preRequisitos;

    public Disciplina(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public List<String> getPreRequisitos() {
        return preRequisitos;
    }

    public void adicionarPreRequisito(String codigoDisciplina) {
        if (!preRequisitos.contains(codigoDisciplina)) {
            preRequisitos.add(codigoDisciplina);
        }
    }

    @Override
    public String toString() {
        String info = "Disciplina: " + nome + " (Código: " + codigo + ")\n";
        info += "Carga Horária: " + cargaHoraria + "h\n";
        if (preRequisitos.isEmpty()) {
            info += "Pré-requisitos: Nenhum";
        } else {
            info += "Pré-requisitos: " + String.join(", ", preRequisitos);
        }
        return info;
    }
}
