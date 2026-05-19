package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AtendimentoTest {

    private Tutor tutor;
    private Animal animalAdotado;
    private Animal animalNaoAdotado;
    private ServicoVeterinario servico;
    private Atendimento atendimento;

    @BeforeEach
    public void setUp() {
        tutor = new Tutor("Felipe");
        animalAdotado = new Animal("Rex", true);
        animalNaoAdotado = new Animal("Mel", false);
        servico = new ServicoVeterinario("Consulta de Rotina");
        atendimento = new Atendimento(tutor, animalAdotado, servico, 100.0);
    }

    @Test
    public void deveIniciarAtendimentoAgendado() {
        assertEquals("Agendado", atendimento.getEstado().getEstado());
        assertTrue(atendimento.iniciar());
        assertEquals("EmAtendimento", atendimento.getEstado().getEstado());
    }

    @Test
    public void deveCancelarAtendimentoAgendado() {
        assertEquals("Agendado", atendimento.getEstado().getEstado());
        assertTrue(atendimento.cancelar());
        assertEquals("Cancelado", atendimento.getEstado().getEstado());
    }

    @Test
    public void deveFinalizarAtendimentoEmAtendimento() {
        atendimento.iniciar();
        assertEquals("EmAtendimento", atendimento.getEstado().getEstado());
        assertTrue(atendimento.finalizar());
        assertEquals("Finalizado", atendimento.getEstado().getEstado());
    }

    @Test
    public void naoDeveFinalizarAtendimentoAgendado() {
        assertFalse(atendimento.finalizar());
        assertEquals("Agendado", atendimento.getEstado().getEstado());
    }

    @Test
    public void naoDeveCancelarAtendimentoEmAtendimento() {
        atendimento.iniciar();
        assertFalse(atendimento.cancelar());
        assertEquals("EmAtendimento", atendimento.getEstado().getEstado());
    }

    @Test
    public void naoDeveCancelarAtendimentoFinalizado() {
        atendimento.iniciar();
        atendimento.finalizar();
        assertEquals("Finalizado", atendimento.getEstado().getEstado());
        assertFalse(atendimento.cancelar());
        assertEquals("Finalizado", atendimento.getEstado().getEstado());
    }

    @Test
    public void naoDeveIniciarAtendimentoCancelado() {
        atendimento.cancelar();
        assertEquals("Cancelado", atendimento.getEstado().getEstado());
        assertFalse(atendimento.iniciar());
        assertEquals("Cancelado", atendimento.getEstado().getEstado());
    }

    @Test
    public void deveAvisarTutorQuandoAtendimentoForIniciado() {
        atendimento.addObserver(tutor);
        assertNull(tutor.getNotificacao());

        atendimento.iniciar();

        assertEquals("Olá Felipe, o atendimento do seu animal Rex foi iniciado!", tutor.getNotificacao());
    }

    @Test
    public void deveAvisarVeterinarioQuandoAtendimentoForCancelado() {
        Veterinario veterinario = new Veterinario("Dr. Carlos");
        atendimento.addObserver(veterinario);
        assertNull(veterinario.getNotificacao());

        atendimento.cancelar();

        assertEquals("Veterinário Dr. Carlos, o atendimento do animal Rex foi cancelado.", veterinario.getNotificacao());
    }

    @Test
    public void deveAvisarRecepcaoQuandoAtendimentoForFinalizado() {
        Recepcao recepcao = new Recepcao();
        atendimento.addObserver(recepcao);
        assertNull(recepcao.getNotificacao());

        atendimento.iniciar();
        atendimento.finalizar();

        assertEquals("Recepção, o atendimento do animal Rex foi finalizado. Valor a pagar: R$100.0", recepcao.getNotificacao());
    }

    @Test
    public void deveRetornarValorBaseSemDecoradores() {
        assertEquals(100.0, atendimento.getValorFinal());
    }

    @Test
    public void deveAplicarDescontoAnimalAdotado() {
        atendimento.setPagamento(new DescontoAdotado(atendimento.getPagamento(), animalAdotado));
        assertEquals(90.0, atendimento.getValorFinal());
    }

    @Test
    public void naoDeveAplicarDescontoSeAnimalNaoForAdotado() {
        Atendimento atendimentoNaoAdotado = new Atendimento(tutor, animalNaoAdotado, servico, 100.0);
        atendimentoNaoAdotado.setPagamento(new DescontoAdotado(atendimentoNaoAdotado.getPagamento(), animalNaoAdotado));
        assertEquals(100.0, atendimentoNaoAdotado.getValorFinal());
    }

    @Test
    public void deveAplicarTaxaAtendimentoDomiciliar() {
        atendimento.setPagamento(new TaxaDomiciliar(atendimento.getPagamento()));
        assertEquals(150.0, atendimento.getValorFinal());
    }

    @Test
    public void deveAplicarServicoBanhoPosConsulta() {
        atendimento.setPagamento(new Banho(atendimento.getPagamento()));
        assertEquals(130.0, atendimento.getValorFinal());
    }

    @Test
    public void deveAplicarMultiplasRegrasCombinadas() {
        atendimento.setPagamento(new Banho(
            new TaxaDomiciliar(atendimento.getPagamento())
        ));
        assertEquals(180.0, atendimento.getValorFinal());

        atendimento.setPagamento(new PagamentoBase(100.0));
        atendimento.setPagamento(new DescontoAdotado(
            new Banho(
                new TaxaDomiciliar(atendimento.getPagamento())
            ),
            animalAdotado
        ));
        assertEquals(162.0, atendimento.getValorFinal());
    }

    @Test
    public void deveNotificarValorCorretoComDecoradores() {
        Recepcao recepcao = new Recepcao();
        
        atendimento.setPagamento(new DescontoAdotado(
            new Banho(
                new TaxaDomiciliar(atendimento.getPagamento())
            ),
            animalAdotado
        ));
        
        atendimento.addObserver(recepcao);
        
        atendimento.iniciar();
        atendimento.finalizar();

        assertEquals("Recepção, o atendimento do animal Rex foi finalizado. Valor a pagar: R$162.0", recepcao.getNotificacao());
    }
}
