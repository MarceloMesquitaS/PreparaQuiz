 import java.util.regex.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario {
    private static Set<String> nomesRegistrados = new HashSet<>();

    private String nome;
    private String senha;
    private String email;

    public Usuario(String nome, String senha, String email) {
        try {
            if (nomesRegistrados.contains(nome)) {
                throw new IllegalArgumentException("O nome já está em uso.");
            }

            this.nome = nome;
            if (validarSenha(senha)) {
                this.senha = senha;
            } else {
                throw new IllegalArgumentException(validarSenhaErro(senha));
            }
            if (validarEmail(email)) {
                this.email = email;
            } else {
                throw new IllegalArgumentException("O email não é válido ou já está em uso.");
            }

            nomesRegistrados.add(nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    private boolean validarSenha(String senha) {
        if (senha.length() < 8) {
            return false;
        } else if (senha.length() > 50) {
            return false;
        }
        if (!senha.matches(".*[A-Z].*")) {
            return false;
        }
        if (!senha.matches(".*[a-z].*")) {
            return false;
        }
        if (!senha.matches(".*\\d.*")) {
            return false;
        }
        if (!senha.matches(".*[@#$%^&+=].*")) {
            return false;
        }
        return true;
    }

    private String validarSenhaErro(String senha) {
        StringBuilder mensagemErro = new StringBuilder("A senha não atende aos requisitos mínimos:");
        if (senha.length() < 8) {
            mensagemErro.append("\n- A senha deve ter pelo menos 8 caracteres.");
        }
        if (senha.length() > 50) {
            mensagemErro.append("\n- A senha não pode ter mais de 50 caracteres.");
        }
        if (!senha.matches(".*[A-Z].*")) {
            mensagemErro.append("\n- A senha deve conter pelo menos uma letra maiúscula.");
        }
        if (!senha.matches(".*[a-z].*")) {
            mensagemErro.append("\n- A senha deve conter pelo menos uma letra minúscula.");
        }
        if (!senha.matches(".*\\d.*")) {
            mensagemErro.append("\n- A senha deve conter pelo menos um dígito numérico.");
        }
        if (!senha.matches(".*[@#$%^&+=].*")) {
            mensagemErro.append("\n- A senha deve conter pelo menos um caractere especial.");
        }
        return mensagemErro.toString();
    }

    private boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        try {
            if (nomesRegistrados.contains(nome)) {
                throw new IllegalArgumentException("O nome já está em uso.");
            }
            nomesRegistrados.remove(this.nome);
            this.nome = nome;
            nomesRegistrados.add(nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao alterar nome do usuário: " + e.getMessage());
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            if (validarSenha(senha)) {
                this.senha = senha;
            } else {
                throw new IllegalArgumentException(validarSenhaErro(senha));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao alterar senha do usuário: " + e.getMessage());
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        try {
            if (validarEmail(email)) {
                this.email = email;
            } else {
                throw new IllegalArgumentException("O email não é válido ou já está em uso.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao alterar email do usuário: " + e.getMessage());
        }
    }

    public void adicionarPontuacao(Pontuacao pontuacao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarPontuacao'");
    }

    public void aumentarPontos(int pontos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aumentarPontos'");
    }

    public List<Pontuacao> getPontuacoes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPontuacoes'");
    }
}

