package org.example;

public class PagamentoBase implements Pagamento {
    private double valor;

    public PagamentoBase(double valor) {
        this.valor = valor;
    }

    @Override
    public double getValorFinal() {
        return valor;
    }
}
