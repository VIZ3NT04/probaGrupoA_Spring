package org.example.probagrupoa.controller;

import org.springframework.http.*;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

@RestController
@RequestMapping("/odoo")
public class OdooController {

    private final String ODOO_URL = "http://192.168.8.214:8069/simarropopgrupoa/api";

    @PostMapping("/request")
    public ResponseEntity<String> sendRequestToOdoo() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Si usas autenticación por sesión, obtienes la sesión aquí (opcional)
        headers.set("Cookie", obtenerSesionOdoo());

        // JSON que se enviará a Odoo
        Map<String, Object> data = new HashMap<>();
        data.put("campo1", "teta1");
        data.put("campo2", "valor2");

        try {
            // Convertir a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(data);

            // Enviar la petición
            HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    ODOO_URL, HttpMethod.POST, requestEntity, String.class);

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error comunicando con Odoo: " + e.getMessage());
        }
    }

    // Metodo opcional para obtener sesión en Odoo
    private String obtenerSesionOdoo() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> params = new HashMap<>();
        params.put("db", "simarropopdb");
        params.put("login", "admin");
        params.put("password", "admin");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://192.168.8.214:8069/web/session/authenticate", request, String.class);

        return response.getHeaders().getFirst(HttpHeaders.SET_COOKIE); // Devuelve la sesión
    }
}
