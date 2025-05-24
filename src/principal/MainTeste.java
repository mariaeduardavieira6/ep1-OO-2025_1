package principal;

import cadastro.CadastroAlunos;
import alunos.AlunoNormal;
import alunos.AlunoEspecial;

public class MainTeste {
    public static void main(String[] args) {
        CadastroAlunos cadastro = new CadastroAlunos();

        AlunoNormal aluno1 = new AlunoNormal("Maria", "232014511", "Engenharia");
        AlunoEspecial aluno2 = new AlunoEspecial("João", "232010101", "Sistema de Informação");

        cadastro.cadastrarAluno(aluno1);
        cadastro.cadastrarAluno(aluno2);

        cadastro.registrarConclusaoDisciplina("232014511", "MAT001");

        cadastro.registrarConclusaoDisciplina("232090909", "MAT001");

        if (aluno1.jaCursou("MAT001")) {
        }
    }
}
