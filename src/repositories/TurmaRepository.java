package repositories;

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
        try {
            FileWriter arquivo = new FileWriter("csv_files/Turma.csv", true);

            arquivo.write(turma.toString());
            arquivo.write("\n");
            arquivo.close();

        } catch (IOException error) {
            System.out.println("Erro ao tentar salvar " + error.getMessage());
        }
    }

    public List<Turma> getTurmas() {
        List<Turma> turmas = new ArrayList<>();

        try (Scanner leitor = new Scanner(new FileReader("csv_files/Turmas.csv"))) {

            while (leitor.hasNextLine()) {
                Professor professor = new Professor();
                HorarioDeAula horarioDeAula = new HorarioDeAula();

                String linha = leitor.nextLine();

                String[] colunas = linha.split(",");
                String nomeProfessor = colunas[0];

                professor = professorRepository.getProfessorByNome(nomeProfessor);

                if (professor != null && nomeProfessor.equals(professor.getNome())) {
                    Integer semestre = Integer.parseInt(colunas[1]);
                    Modalidade modalidade = Modalidade.valueOf(colunas[2]);
                    MetodoDeAvaliacao metodoDeAvaliacao = MetodoDeAvaliacao.valueOf(colunas[3]);
                    String dia = colunas[4];
                    Integer hora = Integer.parseInt(colunas[5]);
                    Integer min = Integer.parseInt(colunas[6]);
                    String sala = colunas[7];
                    Integer capacidade = Integer.parseInt(colunas[8]);

                    horarioDeAula.setDia(dia);
                    horarioDeAula.setHora(hora);
                    horarioDeAula.setMinuto(min);

                    Turma turma = new Turma(professor, semestre, metodoDeAvaliacao, modalidade, horarioDeAula,
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
