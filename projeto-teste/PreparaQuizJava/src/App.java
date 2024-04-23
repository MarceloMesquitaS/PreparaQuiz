import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;


public class App {
    private List<Usuario> usuarios;
    private List<Questionario> questionarios;
    private List<Ranking> ranking;
    private Scanner scanner;

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
        System.out.println("5 - Sair");
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
                    // Criar questionário
                    break;
                case 3:
                    exibirRanking();
                    break;
                case 4:
                    // Exibir histórico de pontuação
                    break;
                case 5:
                    System.out.println("Saindo do PreparaQuiz...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (opcao != 5);
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
    
        // Criar o usuário após a verificação
        Usuario novoUsuario = new Usuario(nome, senha, email);
        usuarios.add(novoUsuario);
        System.out.println("Usuário criado com sucesso!");
    }
    
    public void exibirRanking() {
        // Implementar exibição do ranking
        System.out.println("Exibindo ranking...");
    }

    public static void main(String[] args) {
        App app = new App();
        app.iniciar();
    }
}
