public class Ranking {
    private int posicao;
    private Usuario usuario;
    private int pontos;

    public Ranking(int posicao, Usuario usuario) {
        this.posicao = posicao;
        this.usuario = usuario;
        this.pontos = 0; 
    }

    public int getPosicao() {
        return posicao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getPontos() {
        return pontos;
    }

    public void aumentarPontos() {
        pontos += 100;
    }

}
