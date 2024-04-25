public class Respostas {
    private String respostaCorreta;
    private String respostaUsuario;

    public Respostas(String respostaCorreta, String respostaUsuario) {
        this.respostaCorreta = respostaCorreta;
        this.respostaUsuario = respostaUsuario;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public String getRespostaUsuario() {
        return respostaUsuario;
    }

    public boolean estaCorreta() {
        return respostaCorreta.equals(respostaUsuario);
    }
}
