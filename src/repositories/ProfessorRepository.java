package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entidades.Professor;

public class ProfessorRepository {

    public void save(Professor professor) {

        File diretorio = new File("csv_files");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        try {
            FileWriter arquivo = new FileWriter("csv_files/Professor.csv", true);
            arquivo.write(professor.toString());
            arquivo.write("\n");
            arquivo.close();

        } catch (IOException erro) {

            System.out.println("Erro ao salvar " + erro.getMessage());
        }

    }

    public Professor getProfessorByMatricula(Integer matricula) {
        try (Scanner sc = new Scanner(new FileReader("csv_files/Professor.csv"))) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                if (matricula.equals(Integer.parseInt(colunas[0]))) {
                    String nome = colunas[1];
                    Professor professor = new Professor(matricula, nome);

                    return professor;
                }

            }

        } catch (IOException erro) {
            System.out.println("Erro ao consultra professor " + erro.getMessage());
        }

        return null;
    }

}
