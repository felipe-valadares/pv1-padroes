package org.example;

public class Banho extends PagamentoDecorator {
    private double taxa;

    public Banho(Pagamento pagamentoDecorado) {
        this(pagamentoDecorado, 30.0);
    }

    public Banho(Pagamento pagamentoDecorado, double taxa) {
        super(pagamentoDecorado);
        this.taxa = taxa;
    }

    @Override
    public double getValorIncremento() {
        return this.taxa;
    }
}
