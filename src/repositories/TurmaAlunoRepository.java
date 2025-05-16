package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entidades.Aluno;
import entidades.TurmaAluno;

public class TurmaAlunoRepository {

    public TurmaRepository turmaRepository = new TurmaRepository();
    public AlunoRepository alunoRepository = new AlunoRepository();

    public void save(TurmaAluno turmaAluno) {

        File diretorio = new File("csv_files");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        try {
            FileWriter arquivo = new FileWriter("csv_files/TurmaAluno.csv", true);
            TurmaAluno novaTurmaAluno = getTurmaAlunoByMatricula(turmaAluno.getAluno().getMatricula());

            if (novaTurmaAluno != null
                    && turmaAluno.getTurma().getNumeroTurma().equals(novaTurmaAluno.getTurma().getNumeroTurma())) {
                System.out.println("Associação turma aluno ja existe!");

            } else {
                novaTurmaAluno = new TurmaAluno(turmaAluno.getAluno(), turmaAluno.getTurma(), turmaAluno.getNota(),
                        turmaAluno.getFrequencia());
                arquivo.write(novaTurmaAluno.toString());
                arquivo.write("\n");
                arquivo.close();
            }

        } catch (IOException error) {
            System.out.println("Erro ao salvar " + error.getMessage());
        }
    }

    public TurmaAluno getTurmaAlunoByMatricula(Integer alunoMatricula) {
        try (Scanner sc = new Scanner(new FileReader("csv_files/TurmaAluno.csv"))) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                Aluno aluno = alunoRepository.getAlunoByMatricula(Integer.parseInt(colunas[0]));

                if (alunoMatricula.equals(Integer.parseInt(colunas[0]))) {

                    TurmaAluno turmaAluno = new TurmaAluno(aluno,
                            turmaRepository.getTurmaByNum(Integer.parseInt(colunas[1])),
                            Double.parseDouble(colunas[2]), Double.parseDouble(colunas[3]));

                    return turmaAluno;
                }

            }

        } catch (IOException erro) {
            System.out.println("Erro ao consultar turmaAluno " + erro.getMessage());
        }

        return null;
    }

}
