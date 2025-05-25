package avaliacao;

import java.util.HashMap;
import java.util.Map;

public class ControleAvaliacaoFrequencia {

    
    private Map<String, Avaliacao> avaliacoes;

    public ControleAvaliacaoFrequencia() {
        avaliacoes = new HashMap<>();
    }
    
    private String gerarChave(String matriculaAluno, String codigoTurma) {
        return matriculaAluno + "_" + codigoTurma;
    }

    public void registrarAvaliacao(String matriculaAluno, String codigoTurma, Avaliacao avaliacao) {
        String chave = gerarChave(matriculaAluno, codigoTurma);
        avaliacoes.put(chave, avaliacao);
    }

    
    public Avaliacao getAvaliacao(String matriculaAluno, String codigoTurma) {
        String chave = gerarChave(matriculaAluno, codigoTurma);
        return avaliacoes.get(chave);
    }
    
    public String getSituacaoAluno(String matriculaAluno, String codigoTurma) {
        Avaliacao avaliacao = getAvaliacao(matriculaAluno, codigoTurma);
        if (avaliacao == null) {
            return "Avaliação não encontrada.";
        }
        return avaliacao.getSituacao();
    }
}
