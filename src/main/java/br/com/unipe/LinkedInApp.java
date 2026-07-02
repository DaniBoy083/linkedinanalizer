package br.com.unipe;

import java.util.List;
import java.util.Map;

public class LinkedInApp {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(false, true);

        grafo.adicionaVertices("Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda",
                "Gabriel", "Hugo", "Igor", "Juliana");

        grafo.addAresta("Ana", "Bruno", 1);
        grafo.addAresta("Ana", "Carlos", 2);
        grafo.addAresta("Ana", "Daniela", 8);
        grafo.addAresta("Bruno", "Eduardo", 1);
        grafo.addAresta("Carlos", "Eduardo", 1);
        grafo.addAresta("Daniela", "Fernanda", 5);
        grafo.addAresta("Eduardo", "Fernanda", 1);
        grafo.addAresta("Gabriel", "Hugo", 1);
        grafo.addAresta("Igor", "Juliana", 1);

        LinkedInAnalyzer analyzer = new LinkedInAnalyzer(grafo);

        System.out.println("=== Missão 2: Sugestão de conexões para Ana ===");
        Map<String, Integer> sugestoes = analyzer.sugereConexoes("Ana");
        sugestoes.forEach((nome, comuns) -> System.out.println(nome + " (" + comuns + " amigos em comum)"));

        System.out.println("\n=== Missão 3: Grau de separação ===");
        System.out.println("Ana -> Fernanda: " + analyzer.grauDeSeparacao("Ana", "Fernanda"));
        System.out.println("Ana -> Igor (isolados): " + analyzer.grauDeSeparacao("Ana", "Igor"));

        System.out.println("\n=== Missão 4: Rota de maior afinidade Ana -> Fernanda ===");
        Grafo.Caminho rota = analyzer.rotaMaiorAfinidade("Ana", "Fernanda");
        System.out.println("Caminho: " + String.join(" -> ", rota.nomes()) + " | custo: " + rota.custo());

        System.out.println("\n=== Missão 5: Grupos isolados (sub-redes) ===");
        List<List<String>> grupos = analyzer.mapeiaGruposIsolados();
        for (int i = 0; i < grupos.size(); i++) {
            System.out.println("Sub-rede " + (i + 1) + ": " + grupos.get(i));
        }
    }
}
