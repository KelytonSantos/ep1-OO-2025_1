import java.util.List;
import java.util.Scanner;

import entidades.Aluno;
import entidades.AlunoEspecial;
import entidades.Disciplina;
import entidades.HorarioDeAula;
import entidades.Professor;
import entidades.Turma;
import entidades.TurmaAluno;
import entidades.ENUM.MetodoDeAvaliacao;
import entidades.ENUM.Modalidade;
import repositories.AlunoEspecialRepository;
import repositories.AlunoRepository;
import repositories.DisciplinaRepository;
import repositories.ProfessorRepository;
import repositories.TurmaAlunoRepository;
import repositories.TurmaRepository;

public class App {
    public static Scanner sc = new Scanner(System.in);
    public static AlunoRepository alunoRepository = new AlunoRepository();
    public static AlunoEspecialRepository alunoEspecialRepository = new AlunoEspecialRepository();
    public static ProfessorRepository professorRepository = new ProfessorRepository();
    public static DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
    public static TurmaRepository turmaRepository = new TurmaRepository();
    public static TurmaAlunoRepository turmaAlunoRepository = new TurmaAlunoRepository();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha um modo: ");
            System.out.println("0 - Sair");
            System.out.println("1 - Modo Aluno (Normal e Especial)");
            System.out.println("2 - Modo Disciplina/Turma");
            System.out.println("3 - Modo Avaliação/Frequência");

            int escolha = sc.nextInt();

            switch (escolha) {
                case 0:
                    System.out.println("Encerrando o programa.");
                    sc.close();
                    return; // ou use break seguido de um return fora do while

                case 1:
                    System.out.println("O que deseja fazer?");
                    System.out.println("1 - Cadastrar Aluno ou editar aluno");
                    System.out.println("2 - Matricular Alunos em disciplinas");
                    System.out.println("3 - Ver alunos cadastrados");

                    escolha = sc.nextInt();

                    switch (escolha) {
                        case 1:
                            System.out.println(
                                    "Deseja matrícular aluno/aluno especial ou editar? (1 para matricular, 2 para editar)");
                            escolha = sc.nextInt();
                            if (escolha == 1)
                                matricularAluno();
                            else if (escolha == 2)
                                editarAluno();
                            else
                                System.out.println("Número inválido!");
                            break;
                        case 2:
                            matricularAlunoDisciplina();
                            break;
                        case 3:
                            System.out.println(
                                    "Você deseja ver: 1 todos os alunos(normal/especial); 2 apenas alunos normais; 3 alunos especiais");
                            escolha = sc.nextInt();
                            listarAlunos(escolha);
                            break;
                        default:
                            System.out.println("Código inválido");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("O que deseja fazer?");
                    System.out.println("1 - Cadastrar novo Professor");
                    System.out.println("2 - Cadastrar Disciplina");
                    System.out.println("3 - Criar turmas");
                    System.out.println("4 - Ver Turmas disponíveis");

                    escolha = sc.nextInt();

                    switch (escolha) {
                        case 1:
                            criarProfessor();
                            break;
                        case 2:
                            criarDisciplina();
                            break;
                        case 3:
                            criarTurma();
                            break;
                        case 4:
                            processamentoTurma();
                            break;
                        default:
                            System.out.println("Código inválido");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("O que deseja fazer?");
                    System.out.println("1 - Lançar Notas ou lançar presença");
                    System.out.println("2 - Ver boletim individual");
                    System.out.println("3 - Ver boletim com dados da turma");

                    escolha = sc.nextInt();
                    switch (escolha) {
                        case 1:
                            lancarNotasOuFrequencia();
                            break;
                        case 2:
                            boletimIndividual();
                            break;
                        case 3:
                            boletimGeral();
                            break;
                        default:
                            System.out.println("Código inválido");
                            break;
                    }
                    break;

                default:
                    System.out.println("Código inválido");
                    break;
            }

            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine(); // Consumir o \n pendente
            sc.nextLine(); // Esperar o usuário pressionar ENTER
        }
    }

    public static void matricularAluno() {
        System.out.println("O aluno que deseja matricular é especial (s ou n)?");
        char escolha = sc.next().charAt(0);

        if (escolha == 's' || escolha == 'S') {
            System.out.println("Digite a matrícula do novo aluno especial: ");
            int matricula = sc.nextInt();

            sc.nextLine();
            System.out.println("Digite o nome do aluno: ");
            String nome = sc.nextLine();

            System.out.println("Digite o nome do curso: ");
            String curso = sc.nextLine();

            AlunoEspecial alunoEspecial = new AlunoEspecial(nome, matricula, curso, false, true);
            alunoEspecialRepository.save(alunoEspecial);

        } else {

            System.out.println("Digite a matrícula do aluno: ");
            int matricula = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o nome do Aluno: ");
            String nome = sc.nextLine();

            System.out.println("Digite o nome do curso: ");
            String curso = sc.nextLine();

            Aluno aluno = new Aluno(nome, matricula, curso, Boolean.valueOf(false));

            alunoRepository.save(aluno);
        }
    }

    public static void editarAluno() {

        System.out.println("Voce deseja editar um aluno especial? (s para sim, qualquer outra tecla para n)");
        char escolha = sc.next().charAt(0);

        if (escolha == 's' || escolha == 'S') {

            System.out.println("Digite a matricula do aluno: ");
            int matricula = sc.nextInt();

            AlunoEspecial alunoEspecialParaEditar = alunoEspecialRepository.getAlunoEspecialByMatricula(matricula);

            if (alunoEspecialParaEditar.getMatricula() == null) {

                System.out.println("Aluno não encontrado");

            } else if (alunoEspecialParaEditar.getMatricula() != null
                    && alunoEspecialParaEditar.getMatricula().equals(matricula)) {

                System.out.println("Voce deseja trancar o curso? (s para sim, qualquer tecla para não)");
                escolha = sc.next().charAt(0);
                sc.nextLine();
                if (escolha == 'S' || escolha == 's') {
                    alunoEspecialParaEditar.setTrancamentoDeCurso(true);
                } else {

                    System.out.println("Digite o novo nome: ");
                    String nome = sc.nextLine();

                    System.out.println("Digite o novo curso: ");
                    String curso = sc.nextLine();

                    alunoEspecialParaEditar.setNome(nome);
                    alunoEspecialParaEditar.setCurso(curso);

                }
                alunoEspecialRepository.update(alunoEspecialParaEditar);
            }
        } else {

            Aluno alunoParaEditar = new Aluno();

            System.out.println("Digite a matricula do aluno: ");
            int matricula = sc.nextInt();

            sc.nextLine();

            if ((alunoParaEditar = alunoRepository.getAlunoByMatricula(matricula)) != null) {
                System.out.println("Deseja trocar de curso ou trancar (digite 1 para trocar ou 2 para trancar): ");
                int escolha1 = sc.nextInt();

                sc.nextLine();

                if (escolha1 == 1) {
                    System.out.println("Digite o novo curso: ");
                    String curso = sc.nextLine();
                    alunoParaEditar.setCurso(curso);
                } else if (escolha == 2) {

                    System.out.println("Voce quer trancar o curso? (s ou qualquer tecla para n)");
                    char c = sc.next().charAt(0);

                    if (c == 's' || c == 'S') {
                        alunoParaEditar.setTrancamentoDeCurso(Boolean.valueOf(true));
                        System.out.println("Voce trancou o curso!");
                    }
                }
            }

            alunoRepository.update(alunoParaEditar);
        }
    }

    public static void criarProfessor() {

        System.out.println("Digite a matricula do novo professor: ");
        int matricula = sc.nextInt();

        if (professorRepository.getProfessorByMatricula(matricula) != null) {
            System.out.println("Professor ja existe");

        } else {
            sc.nextLine();

            System.out.println("Digite o nome do professor: ");
            String nomeProfessor = sc.nextLine();

            Professor novoProfessor = new Professor(matricula, nomeProfessor);

            professorRepository.save(novoProfessor);
        }
    }

    public static void criarDisciplina() {

        System.out.println("Digite o nome da nova disciplina: ");
        String nomeDisciplina = sc.nextLine();

        System.out.println("Digite a carga horaria: ");
        int cargaHoraria = sc.nextInt();

        Disciplina novaDisciplina = new Disciplina(nomeDisciplina, cargaHoraria);

        System.out.println("Disciplina criada com sucesso!");
        disciplinaRepository.save(novaDisciplina);

    }

    public static void criarTurma() {

        System.out.println("Digite o nome da disciplina existente que a nova turma estara associada: ");
        String nomeDisciplina = sc.nextLine();

        Disciplina disciplina = disciplinaRepository.getByNome(nomeDisciplina);

        if (disciplina == null) {
            System.out.println("Disciplina inexistente, crie uma primeiro!");
        } else {

            System.out.println("Digite a matricula do professor que dara a materia: ");
            int matriculaProfessor = sc.nextInt();

            Professor novoProfessor = professorRepository.getProfessorByMatricula(matriculaProfessor);

            if (novoProfessor == null) {
                System.out.println("Professor inexistente, cadastre primeiro um professor!");
            } else {

                System.out.println("Digite o número da turma (ex: 05): ");
                int numTurma = sc.nextInt();

                novoProfessor = professorRepository.getProfessorByMatricula(matriculaProfessor);

                System.out.println("Digite o semestre em que a matéria esta dísponivel: ");
                int semestre = sc.nextInt();

                sc.nextLine();

                System.out.println("Digite o metodo de avaliação do professor(MEDIA PONDERADA ou MEDIA SIMPLES): ");
                String metodoDeAval = sc.nextLine();

                System.out.println("Digite o modo de participação na materia (1 para online ou 2 para presencial): ");
                int modoDePartici = sc.nextInt();

                System.out.println(
                        "Defina o dia da semana, o horario e os minutos em que serão ministradas as aulas (ex: Quinta 14 00)");
                String diaDaSemana = sc.next();
                int hora = sc.nextInt();

                int minutos = sc.nextInt();

                System.out.println("Defina a capacidade maxima de alunos: ");
                int capacidadeMax = sc.nextInt();

                HorarioDeAula horarioDeAula = new HorarioDeAula(diaDaSemana, hora, minutos);

                Turma novaTurma = new Turma(numTurma, novoProfessor, semestre, MetodoDeAvaliacao.fromCode(metodoDeAval),
                        horarioDeAula, capacidadeMax);

                sc.nextLine();
                if (modoDePartici == 2) {
                    System.out.println("Digite a sala (ex: S9): ");
                    String sala = sc.nextLine();
                    novaTurma.setSala(sala);
                    novaTurma.setModoDeParticipacao(Modalidade.valueOf(1));
                } else {
                    novaTurma.setModoDeParticipacao(Modalidade.valueOf(modoDePartici));
                }

                novaTurma.setDisciplina(disciplina);
                disciplina.addTurma(novaTurma);

                turmaRepository.save(novaTurma);
                disciplinaRepository.update(disciplina);
            }
        }
    }

    public static void listarAlunos(int numero) {
        switch (numero) {
            case 1:

                System.out.println("Lista de Alunos");
                for (Aluno aluno : alunoRepository.getAlunos()) {
                    System.out.println("Matricula: " + aluno.getMatricula() + " Nome: " + aluno.getNome()
                            + " Curso: " + aluno.getCurso());
                }

                System.out.println("");

                System.out.println("Lista de Alunos Especiais");
                for (AlunoEspecial aluno : alunoEspecialRepository.getAlunos()) {
                    System.out.println("Matricula: " + aluno.getMatricula() + " Nome: " + aluno.getNome()
                            + " Curso: " + aluno.getCurso());
                }

                break;
            case 2:
                for (Aluno aluno : alunoRepository.getAlunos()) {
                    System.out.println("Matricula: " + aluno.getMatricula() + " Nome: " + aluno.getNome()
                            + " Curso: " + aluno.getCurso());
                }
                break;
            case 3:
                for (AlunoEspecial aluno : alunoEspecialRepository.getAlunos()) {
                    System.out.println("Matricula: " + aluno.getMatricula() + " Nome: " + aluno.getNome()
                            + " Curso: " + aluno.getCurso());
                }
                break;
            default:
                System.out.println("Código inválido");
                break;
        }
    }

    public static void matricularAlunoDisciplina() {

        System.out.println("Digite a matricula do aluno: ");
        int matricula = sc.nextInt();

        Aluno aluno = alunoEspecialRepository.getAlunoEspecialByMatricula(matricula);
        aluno = (aluno != null) ? aluno : alunoRepository.getAlunoByMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado");
        } else {
            System.out.println("Turmas disponíveis para o aluno " + aluno.getNome() + ": ");
            for (Turma t : turmaRepository.getTurmas()) {
                if (t.getAlunos().size() < t.getMaxAlunos()) {
                    System.out.println("Turma: " + t.getNumeroTurma() + " Disciplina: " + t.getDisciplina().getNome()
                            + " Semestre: " + t.getSemestre() + " Modo de Participação: "
                            + t.getModoDeParticipacao()
                            + " Horário de Aula: "
                            + t.getHorarioDeAula().getDia() + " " + t.getHorarioDeAula().getHora() + ":"
                            + t.getHorarioDeAula().getMinuto() + " Vagas: " + t.getMaxAlunos());
                }
            }

            System.out.println("Digite o numero da turma que deseja matricular o aluno " + aluno.getNome() + ":");
            int turmaNum = sc.nextInt();
            Turma turma = turmaRepository.getTurmaByNum(turmaNum);

            if (turma != null) {
                turma.setAluno(aluno);

                turmaRepository.update(turma);
                aluno.setTurma(turma);
                alunoRepository.update(aluno);

                TurmaAluno turmaAluno = new TurmaAluno(aluno, turma, 0.0, 0.0);
                turmaAlunoRepository.save(turmaAluno);
            }
        }
    }

    public static void processamentoTurma() {
        System.out.println("Lista de turmas disponíveis");

        for (Turma turma : turmaRepository.getTurmas()) {
            System.out.println(
                    "Número da turma: " + turma.getNumeroTurma() + "  Disciplina: " + turma.getDisciplina().getNome()
                            + "  Semestre: " + turma.getSemestre() + "  Modo de Participação: "
                            + turma.getModoDeParticipacao()
                            + "  Horário de Aula: "
                            + turma.getHorarioDeAula().getDia() + " " + turma.getHorarioDeAula().getHora() + ":"
                            + turma.getHorarioDeAula().getMinuto() + "  Vagas: " + turma.getMaxAlunos());
        }
        System.out.println();
    }

    public static void lancarNotasOuFrequencia() {
        System.out.println("Digite a matricula do aluno: ");
        int matricula = sc.nextInt();

        Aluno aluno = alunoEspecialRepository.getAlunoEspecialByMatricula(matricula);
        aluno = (aluno != null) ? aluno : alunoRepository.getAlunoByMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado");
        } else {
            TurmaAluno turmaAluno1 = turmaAlunoRepository.getTurmaAlunoByMatricula(matricula);

            if (turmaAluno1 == null) {
                System.out.println("Associação entre turma e aluno não encontrado");
            } else {

                System.out.println("O aluno " + turmaAluno1.getAluno().getNome() + " está matriculado nas turmas: ");

                List<Turma> turmas = turmaRepository.getTurmasByMatricula(aluno.getMatricula());

                for (Turma t : turmas) {
                    System.out.println("Turma: " + t.getNumeroTurma() + " Disciplina: " + t.getDisciplina().getNome());
                }
                System.out.println("Digite a turma que deseja lançar notas e presenças(ex: 05) ");
                int turmaNum = sc.nextInt();

                Turma turma = turmaRepository.getTurmaByNum(turmaNum);

                if (turma != null) {

                    System.out.println("Modo de avaliação: " + turma.getMetodoDeAvaliacao());

                    System.out.println("Digite a primeira nota do aluno: ");
                    double nota1 = sc.nextDouble();

                    System.out.println("Digite a segunda nota do aluno: ");
                    double nota2 = sc.nextDouble();

                    System.out.println("Digite a terceira nota do aluno: ");
                    double nota3 = sc.nextDouble();

                    System.out.println("Digite a frequencia do aluno: ");
                    double frequencia = sc.nextDouble();

                    aluno.setNota(nota1, nota2, nota3, turma.getMetodoDeAvaliacao());
                    aluno.setFrequencia(frequencia);

                    TurmaAluno turmaAluno = new TurmaAluno(aluno, turma, aluno.getNota(), frequencia);
                    turmaAlunoRepository.save(turmaAluno);
                }
            }
        }
    }

    /*
     * o fluxo seria: encontra aluno(especial ou não) depois encontra em qual
     * materia ele ta inscrito e listamos elas(por meio do turmaRepo) depois por
     * meio de seleção escolhemos qual turma queremos salvar no turmaAluno
     */

    public static void boletimIndividual() {
        System.out.println("Digite a matricula do aluno: ");
        int matricula = sc.nextInt();

        TurmaAluno turmaAluno = turmaAlunoRepository.getTurmaAlunoByMatricula(matricula);

        if (turmaAluno == null) {
            System.out.println("Associação entre turma e aluno não encontrado");
        } else {

            System.out.println("O aluno " + turmaAluno.getAluno().getNome() + " está matriculado nas turmas: ");

            for (TurmaAluno turma : turmaAlunoRepository.getTurmasAlunosByMatricula(matricula)) {
                System.out.println("Turma: " + turma.getTurma().getNumeroTurma() + " Disciplina: "
                        + turma.getTurma().getDisciplina().getNome());
            }

            System.out.println("Digite a turma que deseja ver o boletim (ex: 05) ");
            int turmaNum = sc.nextInt();

            TurmaAluno boletim = turmaAlunoRepository.getTurmaAlunoByMatricula(matricula, turmaNum);
            if (boletim == null) {
                System.out.println("Boletim não encontrado");
            } else {
                System.out.println("Boletim do aluno " + turmaAluno.getAluno().getNome() + ": ");
                System.out.println("Nota: " + turmaAluno.getNota());
                System.out.println("Frequencia: " + turmaAluno.getFrequencia());
                // colocar situação?(aprovado ou reprovado)

            }

        }
    }

    public static void boletimGeral() {
        System.out.println("Digite o nome da disciplina: ");
        String nomeDisciplina = sc.nextLine();

        Disciplina disciplina = disciplinaRepository.getByNome(nomeDisciplina);

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada");
        } else {
            System.out.println("Boletim da disciplina " + disciplina.getNome() + ": ");
            for (Turma turma : disciplina.getTurmas()) {
                System.out.println("Turma: " + turma.getNumeroTurma());
                for (Aluno aluno : turma.getAlunos()) {
                    System.out.println("Aluno: " + aluno.getNome() + " Nota: " + aluno.getNota() + " Frequencia: "
                            + aluno.getFrequencia());
                }
            }
        }
    }
}

// se o nome for diferente da ruim(tratar)

// um aluno uma turma, editar apenas naquela associação