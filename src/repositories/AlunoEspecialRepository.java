package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entidades.AlunoEspecial;

public class AlunoEspecialRepository {

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
                    return alunoEspecial;
                }

            }

        } catch (IOException erro) {
            System.out.println("Erro ao buscar " + erro.getMessage());
        }

        return null;
    }
}
