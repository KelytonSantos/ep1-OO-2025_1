package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Aluno;

public class AlunoRepository {

    public void save(Aluno aluno) {

        File diretorio = new File("csv_files");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        try {
            FileWriter arquivo = new FileWriter("csv_files/Aluno.csv", true);
            Aluno alunoToCompare = getAlunoByMatricula(aluno.getMatricula());

            if (alunoToCompare != null && aluno.getMatricula().equals(alunoToCompare.getMatricula())) {
                System.out.println("Aluno ja existe!");

            } else {
                arquivo.write(aluno.toString());
                arquivo.write("\n");
                arquivo.close();
            }

        } catch (IOException erro) {
            System.out.println("Erro ao salvar " + erro.getMessage());
        }
    }

    public Aluno getAlunoByMatricula(Integer matricula) {

        try (Scanner leitor = new Scanner(new FileReader("csv_files/Alunos.csv"))) {

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                String[] colunas = linha.split(",");

                if (matricula.equals(Integer.parseInt(colunas[0]))) {
                    String nome = colunas[1];
                    String curso = colunas[2];
                    Boolean trancamento = Boolean.parseBoolean(colunas[3]);

                    Aluno aluno = new Aluno(nome, matricula, curso, trancamento);

                    return aluno;
                }

            }
        } catch (IOException error) {
            System.out.println("Erro ao tentar encontrar aluno pela matrícula " + error.getMessage());
        }

        return null;
    }

    public List<Aluno> getAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        try (Scanner sc = new Scanner(new FileReader("csv_files/Aluno.csv"))) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();

                String[] colunas = linha.split(",");
                Integer matricula = Integer.parseInt(colunas[0]);
                String nome = colunas[1];
                String curso = colunas[2];
                Boolean trancamento = Boolean.parseBoolean(colunas[3]);

                Aluno novoAluno = new Aluno(nome, matricula, curso, trancamento);
                alunos.add(novoAluno);
            }

        } catch (IOException erro) {
            System.out.println("Erro ao buscar lista de alunos " + erro.getMessage());
        }
        return alunos;

    }
}
