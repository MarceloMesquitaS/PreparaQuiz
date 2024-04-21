import java.util.ArrayList;
import java.util.List;

public class Pergunta {
    private String texto;
    private List<String> opcoes;
    private int opcaoCorretaIndex;

    public Pergunta(String texto, List<String> opcoes, int opcaoCorretaIndex) {
        if (opcoes.size() < 2 || opcoes.size() > 6) {
            throw new IllegalArgumentException("Uma pergunta deve ter entre 2 e 6 opções.");
        }
        if (opcaoCorretaIndex < 0 || opcaoCorretaIndex >= opcoes.size()) {
            throw new IllegalArgumentException("O índice da opção correta está fora dos limites.");
        }
        
        this.texto = texto;
        this.opcoes = new ArrayList<>(opcoes); 
        this.opcaoCorretaIndex = opcaoCorretaIndex;
    }

    public String getTexto() {
        return texto;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public int getOpcaoCorretaIndex() {
        return opcaoCorretaIndex;
    }

    public String getOpcaoCorreta() {
        return opcoes.get(opcaoCorretaIndex);
    }
}
