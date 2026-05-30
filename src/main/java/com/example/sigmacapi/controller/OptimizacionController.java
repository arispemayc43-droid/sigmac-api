package com.example.sigmacapi.controller;

import com.example.sigmacapi.algoritmos.KnapsackSolver;
import com.example.sigmacapi.algoritmos.TspSolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Controller para endpoints de optimización combinatoria.
 * Expone Knapsack (mochila) y TSP para selección de POIs.
 */
@RestController
@RequestMapping("/api/optimizacion")
@CrossOrigin(origins = "*")
public class OptimizacionController {

    /**
     * POST /api/optimizacion/mochila
     * Body: { "pesos": [2,3,4], "valores": [3,4,5], "capacidad": 8 }
     */
    @PostMapping("/mochila")
    public ResponseEntity<?> mochila(@RequestBody Map<String, Object> body) {
        List<Integer> pesosL   = (List<Integer>) body.get("pesos");
        List<Integer> valoresL = (List<Integer>) body.get("valores");
        int capacidad = ((Number) body.get("capacidad")).intValue();

        int[] pesos   = pesosL.stream().mapToInt(Integer::intValue).toArray();
        int[] valores = valoresL.stream().mapToInt(Integer::intValue).toArray();

        int valorMax = KnapsackSolver.resolver(pesos, valores, capacidad);

        Map<String, Object> result = new HashMap<>();
        result.put("valorMaximo", valorMax);
        result.put("capacidadUsada", capacidad);
        result.put("algoritmo", "Knapsack 0/1 - Programación Dinámica");
        return ResponseEntity.ok(result);
    }

    /**
     * POST /api/optimizacion/tsp
     * Body: { "distancias": [[0,10,15],[10,0,35],[15,35,0]] }
     */
    @PostMapping("/tsp")
    public ResponseEntity<?> tsp(@RequestBody Map<String, Object> body) {
        List<List<Number>> matrix = (List<List<Number>>) body.get("distancias");
        int n = matrix.size();
        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = matrix.get(i).get(j).doubleValue();

        int costoMin = TspSolver.resolver(dist);

        Map<String, Object> result = new HashMap<>();
        result.put("costoMinimo", costoMin);
        result.put("nodos", n);
        result.put("algoritmo", "TSP - Programación Dinámica con Bitmask");
        return ResponseEntity.ok(result);
    }
}