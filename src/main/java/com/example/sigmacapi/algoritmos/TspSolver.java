package com.example.sigmacapi.algoritmos;

import java.util.Arrays;

/**
 * Resuelve el problema del viajante (TSP) mediante programación dinámica con bitmask.
 * Aplicado a rutas múltiples entre POIs seleccionados.
 */
public class TspSolver {

    /**
     * Calcula el costo mínimo del ciclo hamiltoniano.
     *
     * @param dist matriz de distancias entre nodos
     * @return costo mínimo del recorrido completo
     */
    public static int resolver(double[][] dist) {
        int n = dist.length;
        int full = (1 << n) - 1;
        int[][] dp = new int[1 << n][n];

        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        dp[1][0] = 0;

        for (int mask = 1; mask <= full; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0) continue;
                if (dp[mask][u] == Integer.MAX_VALUE / 2) continue;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) != 0) continue;
                    int newMask = mask | (1 << v);
                    int newCost = dp[mask][u] + (int) dist[u][v];
                    if (newCost < dp[newMask][v]) {
                        dp[newMask][v] = newCost;
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int u = 1; u < n; u++) {
            int cost = dp[full][u] + (int) dist[u][0];
            if (cost < min) min = cost;
        }
        return min;
    }
}