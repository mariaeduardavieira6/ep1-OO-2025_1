# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** [Maria Eduarda do Nascimento Vieira]
- **Matrícula:** [232014511]
- **Curso:** [Engenharia de Software]
- **Turma:** [T06]

---

## Instruções para Compilação e Execução

1. **Compilação:**  
   [javac -d bin src/principal/Main.java]

2. **Execução:**  
   [java -cp bin principal.Main]

3. **Estrutura de Pastas:**  
 src/
├── alunos/
│   ├── Aluno.java
│   ├── AlunoEspecial.java
│   └── AlunoNormal.java
├── avaliaca/
│    ├── Avaliacao.java
│    └── ControleAvaliacaoFrequencia.java
├── cadastro/
│   ├── CadastroAlunos.java
│   ├── CadastroDisciplinas.java
│   ├── CadastroMatriculas
│   ├── CadastroTurmas
│   └── GerenciadorDados
├── disciplinaturma/
│   ├── Disciplina.java
│   └── Turma.java
└── principal/
   └── Main.java


3. **Versão do JAVA utilizada:**  
   [Java 23.0.2]

---

## Vídeo de Demonstração

- [[Inserir o link para o vídeo no YouTube/Drive aqui](https://drive.google.com/file/d/104F1NdChN215ERIY9PdvVgjuHg6__cIl/view?usp=sharing)]

---

## Prints da Execução

1. Menu Principal:  
   ![![Captura de tela 2025-05-26 193503](https://github.com/user-attachments/assets/0ce37618-b22b-488b-ae67-ac908c21e51b)
)

2. Cadastro de Aluno:  
   ![![Captura de tela 2025-05-26 194132](https://github.com/user-attachments/assets/ec030231-979a-46ae-8e88-c79ffa783c70)
)

3. Relatório de Frequência/Notas:  
   Estrutura não finalizada

---

## Principais Funcionalidades Implementadas

- [x] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais)
- [x] Cadastro de disciplinas e criação de turmas (presenciais e remotas)
- [x] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [x] Lançamento de notas e controle de presença
- [x] Cálculo de média final e verificação de aprovação/reprovação
- [ ] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [ ] Persistência de dados em arquivos (.txt ou .csv)
- [ ] Tratamento de duplicidade de matrículas
- [x] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

Durante o desenvolvimento do sistema, uma das maiores dificuldades foi organizar a lógica da matrícula de alunos em turmas, especialmente com a verificação dos pré-requisitos e o controle do número de vagas. No começo, também foi um pouco confuso entender como dividir bem as responsabilidades entre as classes e os pacotes, levei um tempo até encontrar uma estrutura que fizesse sentido e deixasse o código mais organizado. Além disso, tive bastante dificuldade no uso do Git e do GitHub. No início, entender os comandos e como organizar os commits, branches e pushs para o repositório foi confuso. Demorei um pouco para entender como usar esses recursos de forma eficiente durante o desenvolvimento, mas aos poucos fui aprendendo e me adaptando.

---

## Contato

- [mariaeduardavieira061@gmail.com]
