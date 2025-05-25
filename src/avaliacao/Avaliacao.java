package avaliacao;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private double p1, p2, p3, l, s; 
    private double frequencia; 
    private int formaAvaliacao; 

    public Avaliacao(int formaAvaliacao) {
        this.formaAvaliacao = formaAvaliacao;
    }

    public void setNotas(double p1, double p2, double p3, double l, double s) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.l = l;
        this.s = s;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

    public double calcularMedia() {
        if (formaAvaliacao == 1) {
            return (p1 + p2 + p3 + l + s) / 5.0;
        } else {
            return (p1 + p2 * 2 + p3 * 3 + l + s) / 8.0;
        }
    }

    public double getFrequencia() {
        return frequencia;
    }

    public String getSituacao() {
        if (frequencia < 75) return "Reprovado por falta";
        double media = calcularMedia();
        if (media >= 5.0) return "Aprovado";
        else return "Reprovado por nota";
    }
}
