import java.util.ArrayList;
import java.util.List;

public class Questionario {
    private List<Pergunta> perguntas;

    public Questionario() {
        this.perguntas = new ArrayList<>();
    }

    public void adicionarPergunta(Pergunta pergunta) {
        perguntas.add(pergunta);
    }

    public void limparPerguntas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'limparPerguntas'");
    }

}
