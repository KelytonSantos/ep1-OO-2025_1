import java.util.Scanner;

import entidades.Aluno;
import entidades.AlunoEspecial;
import entidades.Disciplina;
import entidades.Professor;
import repositories.AlunoEspecialRepository;
import repositories.AlunoRepository;
import repositories.DisciplinaRepository;
import repositories.ProfessorRepository;
import repositories.TurmaRepository;

public class App {
    public static Scanner sc = new Scanner(System.in);
    public static AlunoRepository alunoRepository = new AlunoRepository();
    public static AlunoEspecialRepository alunoEspecialRepository = new AlunoEspecialRepository();
    public static ProfessorRepository professorRepository = new ProfessorRepository();
    public static DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
    public static TurmaRepository turmaRepository = new TurmaRepository();

    public static void main(String[] args) throws Exception {
        System.out.println("Escolha um modo: ");
        System.out.println("1 - Modo Aluno (Normal e Especial)");
        System.out.println("2 - Modo Disciplina/Turma");
        System.out.println("3 - Modo Avaliação/Frequencia");

        int escolha = sc.nextInt();

        switch (escolha) {
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

                        else if (escolha == 2) {
                            editarAluno();
                        } else {
                            System.out.println("Numero invalido!");
                        }
                        break;
                    case 2: // fazer depois de criar func de matricular turma

                        break;
                    case 3:
                        for (Aluno aluno : alunoRepository.getAlunos()) {
                            System.out.println("Matricula: " + aluno.getMatricula() + " Nome: " + aluno.getNome()
                                    + " Curso: " + aluno.getCurso());
                        }
                        break;
                    default:
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
                        break;
                    case 4:

                        break;
                    default:
                        break;
                }
                break;

            case 3:
                System.out.println("O que deseja fazer?");
                System.out.println("1 - Lançar Notas ou lançar presença");
                System.out.println("2 - Ver boletim individual");
                System.out.println("3 - Ver boletim com dados da turma");
                switch (escolha) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    default:
                        break;
                }
                break;

            default:
                break;
        }

        sc.close();
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
                    alunoEspecialRepository.update(alunoEspecialParaEditar);
                }
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
        disciplinaRepository.save(novaDisciplina);
    }
}
