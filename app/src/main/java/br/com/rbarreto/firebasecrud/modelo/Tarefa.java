package br.com.rbarreto.firebasecrud.modelo;

public class Tarefa {

    private String uid;
    private String nome;
    private boolean status;

    public Tarefa() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "uid='" + uid + '\'' +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                '}';
    }
}
