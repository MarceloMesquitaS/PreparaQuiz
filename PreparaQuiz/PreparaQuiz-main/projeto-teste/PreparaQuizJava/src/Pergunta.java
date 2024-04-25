import java.util.ArrayList;
import java.util.List;

public class Pergunta {
    private String texto;
    private List<String> opcoes;
    private int opcaoCorretaIndex;

    public Pergunta(String texto, List<String> opcoes, int opcaoCorretaIndex) {
        this.texto = texto;
        this.opcoes = new ArrayList<>(opcoes);
        this.opcaoCorretaIndex = opcaoCorretaIndex;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getOpcoes() {
        return new ArrayList<>(opcoes); // Retorna uma cópia da lista para evitar modificações externas
    }

    public void setOpcoes(List<String> opcoes) {
        this.opcoes = new ArrayList<>(opcoes);
    }

    public int getOpcaoCorretaIndex() {
        return opcaoCorretaIndex;
    }

    public void setOpcaoCorretaIndex(int opcaoCorretaIndex) {
        this.opcaoCorretaIndex = opcaoCorretaIndex;
    }

    public String getOpcaoCorreta() {
        return opcoes.get(opcaoCorretaIndex);
    }
}
