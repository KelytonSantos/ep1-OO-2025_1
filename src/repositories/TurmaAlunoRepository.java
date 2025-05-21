package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

            novaTurmaAluno = new TurmaAluno(turmaAluno.getAluno(), turmaAluno.getTurma(), turmaAluno.getNota(),
                    turmaAluno.getFrequencia());
            arquivo.write(novaTurmaAluno.toString());
            arquivo.write("\n");
            arquivo.close();

        } catch (IOException error) {
            System.out.println("Erro ao salvar " + error.getMessage());
        }
    }

    public void update(TurmaAluno turmaAluno) {
        try (Scanner sc = new Scanner(new FileReader("csv_files/TurmaAluno.csv"))) {
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                if (turmaAluno.getAluno().getMatricula().equals(Integer.parseInt(colunas[0]))
                        && turmaAluno.getTurma().getNumeroTurma().equals(Integer.parseInt(colunas[1]))) {

                    TurmaAluno novaTurmaAluno = new TurmaAluno(turmaAluno.getAluno(), turmaAluno.getTurma(),
                            turmaAluno.getNota(), turmaAluno.getFrequencia());
                    sb.append(novaTurmaAluno.toString());
                } else {
                    sb.append(linha);
                }
                sb.append("\n");
            }
            FileWriter arquivo = new FileWriter("csv_files/TurmaAluno.csv");
            arquivo.write(sb.toString());
            arquivo.close();

        } catch (IOException erro) {
            System.out.println("Erro ao atualizar turma " + erro.getMessage());
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

    public TurmaAluno getTurmaAlunoByMatricula(Integer alunoMatricula, Integer turmaNum) {
        try (Scanner sc = new Scanner(new FileReader("csv_files/TurmaAluno.csv"))) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                Aluno aluno = alunoRepository.getAlunoByMatricula(Integer.parseInt(colunas[0]));

                if (alunoMatricula.equals(Integer.parseInt(colunas[0]))
                        && turmaNum.equals(Integer.parseInt(colunas[1]))) {

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

    public List<TurmaAluno> getTurmasAlunosByMatricula(Integer alunoMatricula) {

        List<TurmaAluno> turmasAlunos = new ArrayList<>();

        try (Scanner sc = new Scanner(new FileReader("csv_files/TurmaAluno.csv"))) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                Aluno aluno = alunoRepository.getAlunoByMatricula(Integer.parseInt(colunas[0]));

                if (alunoMatricula.equals(Integer.parseInt(colunas[0]))) {

                    TurmaAluno turmaAluno = new TurmaAluno(aluno,
                            turmaRepository.getTurmaByNum(Integer.parseInt(colunas[1])),
                            Double.parseDouble(colunas[2]), Double.parseDouble(colunas[3]));

                    turmasAlunos.add(turmaAluno);
                }

            }

        } catch (IOException erro) {
            System.out.println("Erro ao consultar turmaAluno " + erro.getMessage());
        }

        return turmasAlunos;
    }

}
