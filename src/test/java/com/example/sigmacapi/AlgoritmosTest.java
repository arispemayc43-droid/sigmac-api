package com.example.sigmacapi;

import com.example.sigmacapi.algoritmos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.example.sigmacapi.algoritmos.KnapsackSolver;
import com.example.sigmacapi.algoritmos.TspSolver;
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
    // ── KNAPSACK TESTS ──
    @Test
    void testKnapsack_DeberiaSeleccionarMejoresPOIs() {
        int[] pesos   = {2, 3, 4, 5};
        int[] valores = {3, 4, 5, 6};
        int resultado = KnapsackSolver.resolver(pesos, valores, 8);
        assertEquals(10, resultado);
    }

    @Test
    void testKnapsack_CapacidadCero_DeberiaRetornarCero() {
        int[] pesos   = {1, 2, 3};
        int[] valores = {10, 20, 30};
        int resultado = KnapsackSolver.resolver(pesos, valores, 0);
        assertEquals(0, resultado);
    }

    @Test
    void testKnapsack_UnSoloItem_QueCabe() {
        int[] pesos   = {3};
        int[] valores = {7};
        int resultado = KnapsackSolver.resolver(pesos, valores, 5);
        assertEquals(7, resultado);
    }

    @Test
    void testKnapsack_UnSoloItem_NoQuePuede() {
        int[] pesos   = {10};
        int[] valores = {7};
        int resultado = KnapsackSolver.resolver(pesos, valores, 5);
        assertEquals(0, resultado);
    }
    // ── TSP TESTS ──
    @Test
    void testTSP_DeberiaEncontrarRutaOptima() {
        double[][] dist = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int resultado = TspSolver.resolver(dist);
        assertEquals(80, resultado);
    }

    @Test
    void testTSP_DosNodos_DeberiaRetornarIdayVuelta() {
        double[][] dist = {
                {0, 5},
                {5, 0}
        };
        int resultado = TspSolver.resolver(dist);
        assertEquals(10, resultado);
    }

    @Test
    void testTSP_TresNodos_DeberiaRetornarCicloMinimo() {
        double[][] dist = {
                {0, 1, 2},
                {1, 0, 3},
                {2, 3, 0}
        };
        int resultado = TspSolver.resolver(dist);
        assertEquals(6, resultado);
    }
}