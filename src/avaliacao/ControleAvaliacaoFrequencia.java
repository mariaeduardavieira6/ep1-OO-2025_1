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
    
    
    public void imprimirRelatorioPorTurma(String codigoTurma, Map<String, String> alunosMatriculados) {
        System.out.println("Relatório da Turma: " + codigoTurma + " ");
        System.out.println("Aluno\t\tMédia\tFrequência\tSituação");
        for (Map.Entry<String, String> entry : alunosMatriculados.entrySet()) {
            String matricula = entry.getKey();
            String nomeAluno = entry.getValue();
            Avaliacao avaliacao = getAvaliacao(matricula, codigoTurma);
            if (avaliacao != null) {
                System.out.printf("%s (%s)\t%.2f\t%.2f%%\t\t%s\n", 
                    nomeAluno, matricula, 
                    avaliacao.calcularMedia(), 
                    avaliacao.getFrequencia(), 
                    avaliacao.getSituacao());
            } else {
                System.out.printf("%s (%s)\tSem avaliação registrada.\n", nomeAluno, matricula);
            }
        }
        System.out.println();
    }
}

