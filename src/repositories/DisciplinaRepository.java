package repositories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

}
