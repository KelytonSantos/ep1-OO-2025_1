package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entidades.Disciplina;

public class DisciplinaRepository {

    public void save(Disciplina disciplina) {

        File diretorio = new File("csv_files");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        try {

            FileWriter arquivo = new FileWriter("csv_files/Disciplina.csv");

            arquivo.write(disciplina.toString());
            arquivo.write("\n");
            arquivo.close();

        } catch (IOException erro) {
            System.out.println("Erro ao salvar " + erro.getMessage());
        }
    }

    public Disciplina getByNome(String nome) {

        try (Scanner sc = new Scanner(new FileReader("csv_files/Disciplina.csv"))) {

            while (sc.hasNextLine()) {

                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                if (nome.equals(colunas[0])) {
                    Disciplina disciplina = new Disciplina(nome, Integer.parseInt(colunas[1]));
                    return disciplina;
                }

            }

        } catch (IOException erro) {
            System.out.println("Erro ao buscar por nome " + erro.getMessage());
        }

        return null;
    }

}
