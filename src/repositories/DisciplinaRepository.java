package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entidades.Disciplina;
import entidades.Turma;

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

    public void update(Disciplina disciplina) {
        File arquivoOriginal = new File("csv_files/Disciplina.csv");
        File arquivoTemporario = new File("csv_files/Disciplina_temp.csv");

        try (Scanner sc = new Scanner(new FileReader(arquivoOriginal));
                FileWriter writer = new FileWriter(arquivoTemporario)) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                if (colunas.length > 0 && disciplina.getNome().equals(colunas[0])) {

                    StringBuilder novaLinha = new StringBuilder();
                    novaLinha.append(disciplina.getNome()).append(",");
                    novaLinha.append(disciplina.getCargaHoraria());

                    for (Turma turma : disciplina.getTurmas()) {
                        novaLinha.append(",").append(turma.getNumeroTurma());
                    }

                    writer.write(novaLinha.toString());
                } else {
                    writer.write(linha);
                }
                writer.write("\n");
            }

        } catch (IOException e) {
            System.out.println("Erro ao atualizar disciplina: " + e.getMessage());
        }

        if (arquivoOriginal.delete()) {
            arquivoTemporario.renameTo(arquivoOriginal);
        } else {
            System.out.println("Erro ao substituir o arquivo original.");
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
// tratar caso ja tenha uma turma com o mesmo horario