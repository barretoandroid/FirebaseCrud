package br.com.rbarreto.firebasecrud.modelo;

public class Tarefa {

    private String uid;
    private String nome;
    private String imageSrc = "https://olhardigital.com.br/uploads/acervo_imagens/2013/08/r16x9/20130820184205_1200_675_-_android_logo.jpg";
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


    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
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
