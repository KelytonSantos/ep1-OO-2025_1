package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.HorarioDeAula;
import entidades.Professor;
import entidades.Turma;
import entidades.ENUM.MetodoDeAvaliacao;
import entidades.ENUM.Modalidade;

public class TurmaRepository {

    private ProfessorRepository professorRepository = new ProfessorRepository();

    public void save(Turma turma) {

        File diretorio = new File("csv_files");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        try {
            FileWriter arquivo = new FileWriter("csv_files/Turma.csv", true);

            // tratar aqui caso ja tenha uma turma com o mesmo horario

            arquivo.write(turma.toString());
            arquivo.write("\n");
            arquivo.close();

        } catch (IOException error) {
            System.out.println("Erro ao salvar " + error.getMessage());
        }
    }

    public Turma getTurmaByNum(Integer num) {

        try (Scanner leitor = new Scanner(new FileReader("csv_files/Turmas.csv"))) {

            while (leitor.hasNextLine()) {
                Professor professor = new Professor();
                HorarioDeAula horarioDeAula = new HorarioDeAula();

                String linha = leitor.nextLine();

                String[] colunas = linha.split(",");
                String nomeProfessor = colunas[1];

                professor = professorRepository.getProfessorByNome(nomeProfessor);

                if (professor != null && nomeProfessor.equals(professor.getNome())) {
                    Integer numTurma = Integer.parseInt(colunas[0]);
                    Integer semestre = Integer.parseInt(colunas[2]);
                    Modalidade modalidade = Modalidade.valueOf(colunas[3]);
                    MetodoDeAvaliacao metodoDeAvaliacao = MetodoDeAvaliacao.valueOf(colunas[4]);
                    String dia = colunas[5];
                    Integer hora = Integer.parseInt(colunas[6]);
                    Integer min = Integer.parseInt(colunas[7]);
                    String sala = colunas[8];
                    Integer capacidade = Integer.parseInt(colunas[9]);

                    horarioDeAula.setDia(dia);
                    horarioDeAula.setHora(hora);
                    horarioDeAula.setMinuto(min);

                    Turma turma = new Turma(numTurma, professor, semestre, metodoDeAvaliacao, modalidade, horarioDeAula,
                            capacidade);
                    turma.setSala(sala);

                    return turma;
                }
            }

        } catch (IOException error) {
            System.out.println("Erro ao tentar buscar alunos" + error.getMessage());
        }
        return null;

    }

    public List<Turma> getTurmas() {
        List<Turma> turmas = new ArrayList<>();

        try (Scanner leitor = new Scanner(new FileReader("csv_files/Turmas.csv"))) {

            while (leitor.hasNextLine()) {
                Professor professor = new Professor();
                HorarioDeAula horarioDeAula = new HorarioDeAula();

                String linha = leitor.nextLine();

                String[] colunas = linha.split(",");
                String nomeProfessor = colunas[1];

                professor = professorRepository.getProfessorByNome(nomeProfessor);

                if (professor != null && nomeProfessor.equals(professor.getNome())) {
                    Integer numTurma = Integer.parseInt(colunas[0]);
                    Integer semestre = Integer.parseInt(colunas[2]);
                    Modalidade modalidade = Modalidade.valueOf(colunas[3]);
                    MetodoDeAvaliacao metodoDeAvaliacao = MetodoDeAvaliacao.valueOf(colunas[4]);
                    String dia = colunas[5];
                    Integer hora = Integer.parseInt(colunas[6]);
                    Integer min = Integer.parseInt(colunas[7]);
                    String sala = colunas[8];
                    Integer capacidade = Integer.parseInt(colunas[9]);

                    horarioDeAula.setDia(dia);
                    horarioDeAula.setHora(hora);
                    horarioDeAula.setMinuto(min);

                    Turma turma = new Turma(numTurma, professor, semestre, metodoDeAvaliacao, modalidade, horarioDeAula,
                            capacidade);
                    turma.setSala(sala);

                    turmas.add(turma);
                }
            }

        } catch (IOException error) {
            System.out.println("Erro ao tentar buscar alunos" + error.getMessage());
        }

        return turmas;

    }
}
