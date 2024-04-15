import java.time.LocalDate;

public class Pontuacao {
    private int pontos;
    private LocalDate data;

    public Pontuacao(int pontos, LocalDate data) {
        this.pontos = pontos;
        this.data = data;
    }

    public int getPontos() {
        return pontos;
    }

    public LocalDate getData() {
        return data;
    }
}
 