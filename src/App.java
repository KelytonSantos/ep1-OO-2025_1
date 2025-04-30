import java.util.List;

import entidades.Aluno;
import repositories.AlunoRepository;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        AlunoRepository alunoRepository = new AlunoRepository();

        List<Aluno> alunos = alunoRepository.getAlunos();

        for (Aluno aluno : alunos) {
            System.out.println("Aluno: " + aluno.toString());
        }

    }
}
