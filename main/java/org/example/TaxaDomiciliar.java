package org.example;

public class TaxaDomiciliar extends PagamentoDecorator {
    private double taxa;

    public TaxaDomiciliar(Pagamento pagamentoDecorado) {
        this(pagamentoDecorado, 50.0);
    }

    public TaxaDomiciliar(Pagamento pagamentoDecorado, double taxa) {
        super(pagamentoDecorado);
        this.taxa = taxa;
    }

    @Override
    public double getValorIncremento() {
        return this.taxa;
    }
}
