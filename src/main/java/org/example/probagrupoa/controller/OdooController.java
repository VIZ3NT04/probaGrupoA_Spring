package org.example.probagrupoa.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

@RestController
@RequestMapping("/odoo")
public class OdooController {

    private final String ODOO_URL = "http://192.168.8.214:8069/jsonrpc"; // URL de Odoo

    @PostMapping("/request")
    public ResponseEntity<String> sendRequestToOdoo() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construcción del JSON-RPC internamente
        Map<String, Object> jsonRpc = new HashMap<>();
        jsonRpc.put("jsonrpc", "2.0");
        jsonRpc.put("method", "call");
        jsonRpc.put("id", 1);

        Map<String, Object> params = new HashMap<>();
        params.put("service", "object");
        params.put("method", "execute_kw");

        List<Object> args = new ArrayList<>();
        args.add("proyectoABP");  // Nombre de la base de datos
        args.add(2);              // ID del usuario autenticado
        args.add("1234");         // Contraseña
        args.add("res.partner");  // Modelo de Odoo
        args.add("search_read");  // Método
        args.add(Collections.emptyList()); // Sin filtros

        Map<String, Object> kwargs = new HashMap<>();
        kwargs.put("fields", Arrays.asList("id", "name"));

        args.add(kwargs);
        params.put("args", args);

        jsonRpc.put("params", params);

        try {
            // Convertir el mapa a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(jsonRpc);

            // Enviar la petición a Odoo
            HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    ODOO_URL, HttpMethod.POST, requestEntity, String.class);

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error comunicando con Odoo: " + e.getMessage());
        }
    }
}