import java.util.ArrayList;
import java.util.List;

public class Questionario {
    private List<Pergunta> perguntas;

    public Questionario() {
        this.perguntas = new ArrayList<>();
    }

    public List<Pergunta> getPerguntas() {
        return new ArrayList<>(perguntas); // Retorna uma cópia da lista para evitar modificações externas
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = new ArrayList<>(perguntas);
    }

    public void adicionarPergunta(Pergunta pergunta) {
        perguntas.add(pergunta);
    }
}
