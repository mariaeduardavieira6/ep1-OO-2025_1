package principal;

import alunos.AlunoEspecial;
import alunos.AlunoNormal;
import avaliacao.Avaliacao;
import avaliacao.ControleAvaliacaoFrequencia;
import cadastro.CadastroAlunos;

public class MainTeste {
    public static void main(String[] args) {
        CadastroAlunos cadastro = new CadastroAlunos();

        AlunoNormal aluno1 = new AlunoNormal("Maria", "232014511", "Engenharia");
        AlunoEspecial aluno2 = new AlunoEspecial("João", "232010101", "Sistema de Informação");

        cadastro.cadastrarAluno(aluno1);
        cadastro.cadastrarAluno(aluno2);

        cadastro.registrarConclusaoDisciplina("232014511", "MAT001");

        boolean resultado = cadastro.registrarConclusaoDisciplina("232090909", "MAT001");
        if (!resultado) {
            System.out.println("Aluno com matrícula 232090909 não encontrado, não foi possível registrar a disciplina.");
        }

        if (aluno1.jaCursou("MAT001")) {
            System.out.println(aluno1.getNome() + " já cursou a disciplina MAT001.");
        } else {
            System.out.println(aluno1.getNome() + " não cursou a disciplina MAT001.");
        }

        
        Avaliacao avaliacao = new Avaliacao(1);
        avaliacao.setNotas(6.0, 7.5, 5.0, 8.0, 7.0);
        avaliacao.setFrequencia(80.0);

        System.out.println("Média final: " + avaliacao.calcularMedia());
        System.out.println("Situação: " + avaliacao.getSituacao());
        
        ControleAvaliacaoFrequencia controle = new ControleAvaliacaoFrequencia();
        
        Avaliacao avaliacao1 = new Avaliacao(1);
        avaliacao1.setNotas(6.0, 7.5, 5.0, 8.0, 7.0);
        avaliacao1.setFrequencia(80.0);
        
        controle.registrarAvaliacao("232014511", "MAT001", avaliacao1);

        System.out.println("Média final: " + controle.getAvaliacao("232014511", "MAT001").calcularMedia());
        System.out.println("Situação: " + controle.getSituacaoAluno("232014511", "MAT001"));
        
     }

}
