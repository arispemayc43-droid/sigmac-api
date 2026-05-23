package com.example.sigmacapi;

import com.example.sigmacapi.algoritmos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AlgoritmosTest {

    private Grafo grafo;

    @BeforeEach
    void setUp() {
        grafo = new Grafo();
        Nodo A = new Nodo("A", "Nodo A", -17.39, -66.15);
        Nodo B = new Nodo("B", "Nodo B", -17.38, -66.16);
        Nodo C = new Nodo("C", "Nodo C", -17.37, -66.17);
        Nodo D = new Nodo("D", "Nodo D", -17.36, -66.18);

        grafo.agregarNodo(A);
        grafo.agregarNodo(B);
        grafo.agregarNodo(C);
        grafo.agregarNodo(D);

        grafo.agregarArista("A", "B", 10);
        grafo.agregarArista("A", "C", 5);
        grafo.agregarArista("B", "D", 10);
        grafo.agregarArista("C", "D", 5);
    }

    // ── BFS TESTS ──
    @Test
    void testBFS_DeberiaEncontrarRuta() {
        List<String> ruta = BFS.calcular(grafo, "A", "D");
        assertNotNull(ruta);
        assertFalse(ruta.isEmpty());
        assertEquals("A", ruta.get(0));
        assertEquals("D", ruta.get(ruta.size() - 1));
    }

    @Test
    void testBFS_CuandoNoHayConexion_DeberiaRetornarVacio() {
        List<String> ruta = BFS.calcular(grafo, "D", "A");
        assertTrue(ruta.isEmpty());
    }

    // ── DFS TESTS ──
    @Test
    void testDFS_DeberiaEncontrarRuta() {
        List<String> ruta = DFS.calcular(grafo, "A", "D");
        assertNotNull(ruta);
        assertFalse(ruta.isEmpty());
        assertEquals("A", ruta.get(0));
        assertEquals("D", ruta.get(ruta.size() - 1));
    }

    @Test
    void testDFS_CuandoNoHayConexion_DeberiaRetornarVacio() {
        List<String> ruta = DFS.calcular(grafo, "D", "A");
        assertTrue(ruta.isEmpty());
    }

    // ── DIJKSTRA TESTS ──
    @Test
    void testDijkstra_DeberiaEncontrarRutaMasCorta() {
        List<String> ruta = CostoUniforme.calcular(grafo, "A", "D");
        assertNotNull(ruta);
        assertFalse(ruta.isEmpty());
        assertEquals("A", ruta.get(0));
        assertEquals("D", ruta.get(ruta.size() - 1));
    }

    @Test
    void testAEstrella_DeberiaEncontrarRuta() {
        List<String> ruta = AEstrella.calcular(grafo, "A", "D");
        assertNotNull(ruta);
        assertFalse(ruta.isEmpty());
    }
}