package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PropondoLanceSteps {

    private Lance lance;
    private Leilao leilao;
    private List<Lance> lista;

    @Before
    public void setup() {
        lista = new ArrayList<>();
        leilao = new Leilao("Tablet XPTO");
    }

    @Dado("um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @Quando("propoe ao leilao")
    public void quando_propoe_ao_leilao() {
        leilao.propoe(lance);
    }

    @Entao("o lance eh aceito")
    public void entao_o_lance_eh_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario(Double valor, String usuario) {
        Lance lance = new Lance(new Usuario(usuario), new BigDecimal(valor));
        lista.add(lance);
    }

    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        lista.forEach(lance -> leilao.propoe(lance));
    }

    @Entao("os lances sao aceitos")
    public void os_lances_sao_aceitos() {
        assertEquals(lista.size(), leilao.getLances().size());
        assertEquals(lista.get(0).getValor(), leilao.getLances().get(0).getValor());
        assertEquals(lista.get(1).getValor(), leilao.getLances().get(1).getValor());
    }

    @Dado("um lance de {double} reais e do usuario {string}")
    public void um_lance_de_reais_e_do_usuario(Double valor, String usuario) {
//        System.out.println(usuario);
        lance = new Lance(new BigDecimal(valor));
    }

    @Entao("o lance nao eh aceito")
    public void o_lance_nao_eh_aceito() {
        assertEquals(0, leilao.getLances().size());
    }

    @Dado("dois lances")
    public void dois_lances(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps();
        maps.forEach(map -> {
            lance = new Lance(new Usuario(map.get("usuario")), new BigDecimal(map.get("valor")));
            lista.add(lance);
        });
    }

    @Entao("o segundo lance nao eh aceito")
    public void o_segundo_lance_nao_eh_aceito() {
        assertEquals(1, leilao.getLances().size());
        assertEquals(lista.get(0).getValor(), leilao.getLances().get(0).getValor());
    }
}
