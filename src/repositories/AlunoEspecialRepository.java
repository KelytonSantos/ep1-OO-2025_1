package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.AlunoEspecial;
import entidades.Turma;

public class AlunoEspecialRepository {

    public TurmaRepository turmaRepository = new TurmaRepository();

    public void save(AlunoEspecial alunoEspecial) {

        File diretorio = new File("csv_files");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        try {

            AlunoEspecial alunoToCompare = getAlunoEspecialByMatricula(alunoEspecial.getMatricula());

            if (alunoToCompare != null && alunoEspecial.getMatricula().equals(alunoToCompare.getMatricula())) {

                System.out.println("Usuario ja existe");

            } else {

                FileWriter arquivo = new FileWriter("csv_files/AlunoEspecial.csv");
                arquivo.write(alunoEspecial.toString());
                arquivo.write("\n");
                arquivo.close();

            }

        } catch (IOException erro) {
            System.out.println("Erro ao salvar " + erro.getMessage());
        }
    }

    public void update(AlunoEspecial alunoEspecial) {

        File arquivoOriginal = new File("csv_files/AlunoEspecial.csv");
        File arquivoTemporario = new File("csv_files/AlunoEspecial_temp.csv");

        try (Scanner sc = new Scanner(new FileReader(arquivoOriginal));
                FileWriter arquivoDeEscrita = new FileWriter(arquivoTemporario)) {

            while (sc.hasNextLine()) {

                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                if (colunas.length > 0 && Integer.toString(alunoEspecial.getMatricula()).equals(colunas[0])) {
                    arquivoDeEscrita.write(alunoEspecial.toString());
                } else {
                    arquivoDeEscrita.write(linha);
                }
                arquivoDeEscrita.write("\n");
            }

        } catch (IOException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }

        if (arquivoOriginal.delete()) {
            arquivoTemporario.renameTo(arquivoOriginal);
        } else {
            System.out.println("Erro ao substituir o arquivo original.");
        }
    }

    public AlunoEspecial getAlunoEspecialByMatricula(Integer matricula) {

        try (Scanner sc = new Scanner(new FileReader("csv_files/AlunoEspecial.csv"))) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                if (matricula.equals(Integer.parseInt(colunas[0]))) {
                    String nome = colunas[1];
                    String curso = colunas[2];
                    Boolean trancamento = Boolean.parseBoolean(colunas[3]);
                    Boolean isAlunoEspecial = Boolean.parseBoolean(colunas[4]);

                    AlunoEspecial alunoEspecial = new AlunoEspecial(nome, matricula, curso, trancamento,
                            isAlunoEspecial);

                    if (colunas.length > 6) {

                        for (int i = 6; i < colunas.length; i++) {
                            String turma = colunas[i].trim();
                            if (!turma.isEmpty()) {
                                Turma turma1 = turmaRepository.getTurmaByNum(Integer.parseInt(turma));
                                if (turma1 != null) {
                                    alunoEspecial.setTurma(turma1);
                                }
                            }
                        }

                    }
                    return alunoEspecial;
                }

            }

        } catch (IOException erro) {
            System.out.println("Erro ao buscar " + erro.getMessage());
        }

        return null;
    }

    public List<AlunoEspecial> getAlunos() {
        List<AlunoEspecial> alunos = new ArrayList<>();

        try (Scanner sc = new Scanner(new FileReader("csv_files/AlunoEspecial.csv"))) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();

                String[] colunas = linha.split(",");
                Integer matricula = Integer.parseInt(colunas[0]);
                String nome = colunas[1];
                String curso = colunas[2];
                Boolean trancamento = Boolean.parseBoolean(colunas[3]);

                AlunoEspecial novoAluno = new AlunoEspecial(nome, matricula, curso, trancamento, true);
                alunos.add(novoAluno);
            }

        } catch (IOException erro) {
            System.out.println("Erro ao buscar lista de alunos " + erro.getMessage());
        }
        return alunos;
    }
}
