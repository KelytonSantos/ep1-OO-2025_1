package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Aluno;
import entidades.Disciplina;
import entidades.HorarioDeAula;
import entidades.Professor;
import entidades.Turma;
import entidades.ENUM.MetodoDeAvaliacao;
import entidades.ENUM.Modalidade;

public class TurmaRepository {

    private ProfessorRepository professorRepository = new ProfessorRepository();
    private DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
    private AlunoRepository alunoRepository = new AlunoRepository();

    public void save(Turma turma) {

        File diretorio = new File("csv_files");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        try {
            FileWriter arquivo = new FileWriter("csv_files/Turma.csv", true);

            arquivo.write(turma.toString());
            arquivo.write("\n");
            arquivo.close();

        } catch (IOException error) {
            System.out.println("Erro ao salvar " + error.getMessage());
        }
    }

    public void update(Turma turma) {
        File arquivoOriginal = new File("csv_files/Turma.csv");
        File arquivoTemporario = new File("csv_files/Turma_temp.csv");

        try (Scanner sc = new Scanner(new FileReader(arquivoOriginal));
                FileWriter writer = new FileWriter(arquivoTemporario)) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] colunas = linha.split(",");

                System.out.println("antes do if");
                if (colunas.length > 0 && turma.getNumeroTurma() == Integer.parseInt(colunas[0])) {
                    System.out.println("deopis do if");

                    StringBuilder novaLinha = new StringBuilder();
                    novaLinha.append(turma.getNumeroTurma()).append(",");
                    novaLinha.append(turma.getProfessor().getNome()).append(",");
                    novaLinha.append(turma.getSemestre()).append(",");
                    novaLinha.append(turma.getModoDeParticipacao()).append(",");
                    novaLinha.append(turma.getMetodoDeAvaliacao()).append(",");
                    novaLinha.append(turma.getHorarioDeAula().toString()).append(",");
                    novaLinha.append(turma.getSala()).append(",");
                    novaLinha.append(turma.getMaxAlunos()).append(",");
                    novaLinha.append(turma.getDisciplina().getNome()).append(",");

                    for (Aluno aluno : turma.getAlunos()) {
                        novaLinha.append(aluno.getNome()).append(",");
                        System.out.println("aluno: " + aluno.getNome());
                    }

                    writer.write(novaLinha.toString());
                } else {
                    writer.write(linha);
                }
                writer.write("\n");
            }

        } catch (

        IOException e) {
            System.out.println("Erro ao atualizar disciplina: " + e.getMessage());
        }

        if (arquivoOriginal.delete()) {
            arquivoTemporario.renameTo(arquivoOriginal);
        } else {
            System.out.println("Erro ao substituir o arquivo original.");
        }
    }

    public Turma getTurmaByNum(Integer num) {

        try (Scanner leitor = new Scanner(new FileReader("csv_files/Turma.csv"))) {

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                String[] colunas = linha.split(",");

                try {
                    Integer numTurma = Integer.parseInt(colunas[0]);
                    String nomeProfessor = colunas[1];
                    Integer semestre = Integer.parseInt(colunas[2]);
                    Modalidade modalidade = Modalidade.valueOf(colunas[3]);
                    MetodoDeAvaliacao metodoDeAvaliacao = MetodoDeAvaliacao.valueOf(colunas[4]);
                    String dia = colunas[5];
                    Integer hora = Integer.parseInt(colunas[6]);
                    Integer min = Integer.parseInt(colunas[7]);
                    String sala = colunas[8].equalsIgnoreCase("null") ? null : colunas[8];
                    Integer capacidade = Integer.parseInt(colunas[9]);

                    if (numTurma.equals(num)) {
                        Professor professor = professorRepository.getProfessorByNome(nomeProfessor);

                        if (professor != null && nomeProfessor.equals(professor.getNome())) {
                            HorarioDeAula horarioDeAula = new HorarioDeAula();
                            horarioDeAula.setDia(dia);
                            horarioDeAula.setHora(hora);
                            horarioDeAula.setMinuto(min);

                            Turma turma = new Turma(numTurma, professor, semestre, metodoDeAvaliacao, modalidade,
                                    horarioDeAula, capacidade);
                            turma.setSala(sala);

                            if (colunas.length >= 10) {
                                String stringDisciplina = colunas[10];
                                Disciplina disciplina = disciplinaRepository.getByNome(stringDisciplina);
                                if (disciplina != null) {
                                    turma.setDisciplina(disciplina);
                                }
                            }

                            // começar a pegar alunos caso exista

                            if (colunas.length > 11) {

                                for (int i = 11; i < colunas.length; i++) {
                                    String nomeAluno = colunas[i].trim();
                                    if (!nomeAluno.isEmpty()) {
                                        Aluno aluno = alunoRepository.getAlunoByNome(nomeAluno);
                                        if (aluno != null) {
                                            turma.setAluno(aluno);
                                        }
                                    }
                                }

                            }

                            return turma;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Erro ao consultar turma por numero" + e.getMessage());
                }
            }

        } catch (IOException error) {
            System.out.println("Erro ao tentar ler o arquivo Turma.csv: " + error.getMessage());
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
