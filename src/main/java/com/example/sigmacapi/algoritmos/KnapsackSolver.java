package com.example.sigmacapi.algoritmos;

/**
 * Resuelve el problema de la mochila 0/1 (Knapsack) mediante programación dinámica.
 * Aplicado a selección óptima de POIs con restricción de capacidad/tiempo.
 */
public class KnapsackSolver {

    /**
     * Calcula el valor máximo seleccionable sin exceder la capacidad.
     *
     * @param pesos     arreglo de pesos de cada ítem
     * @param valores   arreglo de valores de cada ítem
     * @param capacidad capacidad máxima de la mochila
     * @return valor máximo alcanzable
     */
    public static int resolver(int[] pesos, int[] valores, int capacidad) {
        int n = pesos.length;
        int[][] dp = new int[n + 1][capacidad + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacidad; w++) {
                dp[i][w] = dp[i - 1][w];
                if (pesos[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w],
                            dp[i - 1][w - pesos[i - 1]] + valores[i - 1]);
                }
            }
        }
        return dp[n][capacidad];
    }
}