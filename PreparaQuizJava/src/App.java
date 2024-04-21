import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App extends Application {
    private List<Usuario> usuarios;
    private List<Questionario> questionarios;
    private List<Ranking> ranking;

    @Override
    public void start(Stage primaryStage) {
        usuarios = new ArrayList<>();
        questionarios = new ArrayList<>();
        ranking = new ArrayList<>();

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        Button criarPerfilBtn = new Button("Criar Perfil");
        criarPerfilBtn.setOnAction(e -> exibirJanelaCriarPerfil());

        Button criarQuestionarioBtn = new Button("Criar Questionário");
        criarQuestionarioBtn.setOnAction(e -> {
            // Aqui você poderia abrir uma nova janela para o usuário criar o questionário
            // e, em seguida, usar o método criarQuestionario da classe App
        });

        Button exibirRankingBtn = new Button("Exibir Ranking");
        exibirRankingBtn.setOnAction(e -> exibirRanking());

        Button exibirHistoricoBtn = new Button("Exibir Histórico de Pontuação");
        exibirHistoricoBtn.setOnAction(e -> {
            // Aqui você poderia abrir uma nova janela para exibir o histórico de pontuação do usuário
            // e, em seguida, usar o método exibirHistoricoPontuacao da classe App
        });

        Button sairBtn = new Button("Sair");
        sairBtn.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(criarPerfilBtn, criarQuestionarioBtn, exibirRankingBtn, exibirHistoricoBtn, sairBtn);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Menu Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void exibirJanelaCriarPerfil() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Criar Perfil");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        TextField nomeField = new TextField();
        TextField senhaField = new TextField();
        TextField emailField = new TextField();

        gridPane.add(new Label("Nome:"), 0, 0);
        gridPane.add(nomeField, 1, 0);
        gridPane.add(new Label("Senha:"), 0, 1);
        gridPane.add(senhaField, 1, 1);
        gridPane.add(new Label("Email:"), 0, 2);
        gridPane.add(emailField, 1, 2);

        Button criarBtn = new Button("Criar");
        criarBtn.setOnAction(e -> {
            criarPerfil(nomeField.getText(), senhaField.getText(), emailField.getText());
            stage.close();
        });

        gridPane.add(criarBtn, 0, 3, 2, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void criarPerfil(String nome, String senha, String email) {
        Usuario novoUsuario = new Usuario(nome, senha, email);
        usuarios.add(novoUsuario);
    }

    public void criarQuestionario(List<Pergunta> perguntas) {
        Questionario novoQuestionario = new Questionario();
        perguntas.forEach(novoQuestionario::adicionarPergunta);
        questionarios.add(novoQuestionario);
    }

    public void editarQuestionario(Questionario questionario, List<Pergunta> perguntas) {
        questionario.limparPerguntas();
        perguntas.forEach(questionario::adicionarPergunta);
    }

    public void excluirQuestionario(Questionario questionario) {
        questionarios.remove(questionario);
    }

    public void responderQuestionario(Questionario questionario, Usuario usuario, List<Resposta> respostas) {
        int pontos = 0;
        for (Resposta resposta : respostas) {
            if (resposta.estaCorreta()) {
                pontos += 100;
                usuario.adicionarPontuacao(new Pontuacao(100, LocalDate.now()));
            }
        }
        usuario.aumentarPontos(pontos);
        atualizarRanking(usuario);
    }

    private void atualizarRanking(Usuario usuario) {
        for (Ranking entrada : ranking) {
            if (entrada.getUsuario().equals(usuario)) {
                entrada.aumentarPontos();
                return;
            }
        }
        ranking.add(new Ranking(ranking.size() + 1, usuario));
    }

    public void atribuirPontuacao(Usuario usuario, int pontos) {
        usuario.aumentarPontos(pontos);
    }

    public void exibirRanking() {
        ranking.sort(Comparator.comparingInt(Ranking::getPontos).reversed());
        for (Ranking entrada : ranking) {
            System.out.println("Posição: " + entrada.getPosicao());
            System.out.println("Usuário: " + entrada.getUsuario().getNome());
            System.out.println("Pontuação: " + entrada.getPontos());
        }
    }

    public void fornecerFeedback(Resposta resposta) {
        if (resposta.estaCorreta()) {
            System.out.println("Resposta correta!");
        } else {
            System.out.println("Resposta incorreta!");
        }
    }

    public List<Pontuacao> exibirHistoricoPontuacao(Usuario usuario) {
        return usuario.getPontuacoes();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
