import java.util.ArrayList;
import java.util.List;

public class Questionario {
    private List<Pergunta> perguntas;
    private String titulo;

    public Questionario() {
        this.perguntas = new ArrayList<>();
    }

    public List<Pergunta> getPerguntas() {
        return new ArrayList<>(perguntas); 
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = new ArrayList<>(perguntas);
    }

    public void adicionarPergunta(Pergunta pergunta) {
        perguntas.add(pergunta);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
