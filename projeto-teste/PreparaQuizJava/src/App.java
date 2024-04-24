import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;


public class App {
    private List<Usuario> usuarios;
    private static List<Questionario> questionarios;
    private List<Ranking> ranking;
    private static Scanner scanner = new Scanner(System.in);

    public App() {
        this.usuarios = new ArrayList<>();
        this.questionarios = new ArrayList<>();
        this.ranking = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        System.out.println("Bem-vindo ao PreparaQuiz!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Criar Perfil");
        System.out.println("2 - Criar Questionário");
        System.out.println("3 - Exibir Ranking");
        System.out.println("4 - Exibir Histórico de Pontuação");
        System.out.println("5 - Responder Questionário");
        System.out.println("6 - Sair");
        System.out.print("Opção: ");
    }

    public void iniciar() {
        int opcao = 0;
        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                scanner.nextLine(); 
                continue;
            }
    
            switch (opcao) {
                case 1:
                    criarPerfil();
                    break;
                case 2:
                    criarQuestionario();
                    break;
                case 3:
                    exibirRanking();
                    break;
                case 4:
                    // Exibir histórico de pontuação
                    break;
                case 5:
                    responderQuestionario();
                    break;
                case 6:
                    System.out.println("Saindo do PreparaQuiz...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 6);
        scanner.close();
    }

    public void criarPerfil() {
        String nome;
        String senha;
        String email;
    
        do {
            try {
                System.out.println("Digite o nome do usuário:");
                nome = scanner.nextLine();
                if (nome.isEmpty()) {
                    throw new IllegalArgumentException("Nome não pode ser vazio.");
                }
    
                System.out.println("Digite a senha do usuário:");
                senha = scanner.nextLine();
                if (senha.length() < 8 || senha.length() > 50) {
                    throw new IllegalArgumentException("A senha deve ter entre 8 e 50 caracteres.");
                }
                if (!senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                    throw new IllegalArgumentException("A senha deve conter pelo menos uma letra minúscula, uma letra maiúscula, um número e um caractere especial.");
                }
    
                String finalEmail;
                do {
                    System.out.println("Digite o email do usuário:");
                    finalEmail = scanner.nextLine();
                    if (finalEmail.isEmpty()) {
                        throw new IllegalArgumentException("Email não pode ser vazio.");
                    }
                    if (usuarios.stream().anyMatch(u -> u.getEmail().equals(finalEmail))) {
                        throw new IllegalArgumentException("Email já cadastrado.");
                    }
                } while (false);
    
                email = finalEmail;
                break; 
    
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao criar perfil: " + e.getMessage());
                System.out.println("Por favor, corrija o problema e tente novamente.");
            }
        } while (true);
    
        Usuario novoUsuario = new Usuario(nome, senha, email);
        usuarios.add(novoUsuario);
        System.out.println("Usuário criado com sucesso!");
    }
    
    public void exibirRanking() {
        System.out.println("Exibindo ranking...");
    }

    public static void criarQuestionario() {
        System.out.println("Criando um novo questionário:");

        Questionario questionario = new Questionario();

        System.out.println("Digite o título do questionário:");
        String titulo = scanner.nextLine();
        questionario.setTitulo(titulo);

        int numeroPerguntas;
        while (true) {
            try {
                System.out.println("Digite o número de perguntas do questionário:");
                numeroPerguntas = Integer.parseInt(scanner.nextLine());
                if (numeroPerguntas <= 0) {
                    throw new IllegalArgumentException("O número de perguntas deve ser maior que zero.");
                }
                break; 
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < numeroPerguntas; i++) {
            System.out.println("Pergunta " + (i + 1) + ":");

            System.out.println("Digite o texto da pergunta:");
            String textoPergunta = scanner.nextLine();

            List<String> opcoes = new ArrayList<>();
            while (true) {
                System.out.println("Digite uma opção (ou digite 'fim' para terminar):");
                String opcao = scanner.nextLine();
                if (opcao.equals("fim")) {
                    break;
                }
                opcoes.add(opcao);
            }

            if (opcoes.size() < 2 || opcoes.size() > 6) {
                System.out.println("Uma pergunta deve ter entre 2 e 6 opções. Por favor, tente novamente.");
                i--; 
                continue; 
            }

            int opcaoCorretaIndex;
            while (true) {
                try {
                    System.out.println("Digite o índice da opção correta (de 1 a " + opcoes.size() + "):");
                    opcaoCorretaIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (opcaoCorretaIndex < 0 || opcaoCorretaIndex >= opcoes.size()) {
                        throw new IllegalArgumentException("O índice da opção correta está fora dos limites.");
                    }
                    break; 
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, digite um número válido.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            Pergunta pergunta = new Pergunta(textoPergunta, opcoes, opcaoCorretaIndex);

            questionario.adicionarPergunta(pergunta);
        }

        questionarios.add(questionario);

        System.out.println("Questionário criado com sucesso!");
    }

    public void responderQuestionario() {
        if (questionarios.isEmpty()) {
            System.out.println("Não há questionários disponíveis para responder.");
            return;
        }

        System.out.println("Questionários disponíveis para responder:");
        for (int i = 0; i < questionarios.size(); i++) {
            System.out.println((i + 1) + ". " + questionarios.get(i).getTitulo());
        }

        System.out.println("Digite o número do questionário que deseja responder:");
        int numeroQuestionario = scanner.nextInt();
        scanner.nextLine(); 

        if (numeroQuestionario < 1 || numeroQuestionario > questionarios.size()) {
            System.out.println("Número de questionário inválido.");
            return;
        }

        Questionario questionarioSelecionado = questionarios.get(numeroQuestionario - 1);

        int pontos = 0;

        for (Pergunta pergunta : questionarioSelecionado.getPerguntas()) {
            System.out.println("Pergunta: " + pergunta.getTexto());
            List<String> opcoes = pergunta.getOpcoes();
            for (int i = 0; i < opcoes.size(); i++) {
                System.out.println((i + 1) + ". " + opcoes.get(i));
            }

            System.out.println("Digite o número da sua resposta:");
            int resposta = scanner.nextInt();
            scanner.nextLine(); 

            if (resposta == pergunta.getOpcaoCorretaIndex() + 1) {
                pontos += 100;
                System.out.println("Resposta correta! Você ganhou 100 pontos.");
            } else {
                System.out.println("Resposta incorreta. A resposta correta era: " + pergunta.getOpcaoCorreta());
            }
        }

        System.out.println("Você fez " + pontos + " pontos neste questionário.");
    }
    public static void main(String[] args) {
        App app = new App();
        app.iniciar();
    }
}
