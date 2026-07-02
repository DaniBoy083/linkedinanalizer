package br.com.unipe;

import java.util.*;

public class LinkedInAnalyzer {

    private final Grafo grafo;

    public LinkedInAnalyzer(Grafo grafo) {
        this.grafo = grafo;
    }

    public Map<String, Integer> sugereConexoes(String nome) {
        Vertice usuario = grafo.encontraVertice(nome).orElseThrow(
                () -> new IllegalArgumentException("Vertice " + nome + " não encontrado."));

        Set<Vertice> diretos = new HashSet<>(usuario.getAdjacencias());
        Map<Vertice, Integer> emComum = new HashMap<>();

        for (Vertice amigo : usuario.getAdjacencias()) {
            for (Vertice candidato : amigo.getAdjacencias()) {
                if (candidato.equals(usuario) || diretos.contains(candidato)) {
                    continue;
                }
                emComum.merge(candidato, 1, Integer::sum);
            }
        }

        LinkedHashMap<String, Integer> resultado = new LinkedHashMap<>();
        emComum.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry<Vertice, Integer>::getValue).reversed())
                .forEach(e -> resultado.put(e.getKey().getNome(), e.getValue()));
        return resultado;
    }

    public int grauDeSeparacao(String origem, String destino) {
        Vertice inicio = grafo.encontraVertice(origem).orElseThrow(
                () -> new IllegalArgumentException("Vertice " + origem + " não encontrado."));
        Vertice fim = grafo.encontraVertice(destino).orElseThrow(
                () -> new IllegalArgumentException("Vertice " + destino + " não encontrado."));

        if (inicio.equals(fim)) {
            return 0;
        }

        Set<Vertice> visitados = new HashSet<>();
        Queue<Vertice> fila = new LinkedList<>();
        Map<Vertice, Integer> passos = new HashMap<>();
        visitados.add(inicio);
        fila.add(inicio);
        passos.put(inicio, 0);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            for (Vertice vizinho : atual.getAdjacencias()) {
                if (visitados.add(vizinho)) {
                    passos.put(vizinho, passos.get(atual) + 1);
                    if (vizinho.equals(fim)) {
                        return passos.get(vizinho);
                    }
                    fila.add(vizinho);
                }
            }
        }
        return -1;
    }

    public Grafo.Caminho rotaMaiorAfinidade(String origem, String destino) {
        return grafo.dijkstra(origem, destino);
    }

    public List<List<String>> mapeiaGruposIsolados() {
        List<List<String>> grupos = new ArrayList<>();
        Set<Vertice> visitados = new HashSet<>();

        for (Vertice inicio : grafo.getVertices()) {
            if (!visitados.add(inicio)) {
                continue;
            }
            List<String> grupo = new ArrayList<>();
            Queue<Vertice> fila = new LinkedList<>();
            fila.add(inicio);
            while (!fila.isEmpty()) {
                Vertice atual = fila.poll();
                grupo.add(atual.getNome());
                for (Vertice vizinho : atual.getAdjacencias()) {
                    if (visitados.add(vizinho)) {
                        fila.add(vizinho);
                    }
                }
            }
            grupos.add(grupo);
        }
        return grupos;
    }
}
