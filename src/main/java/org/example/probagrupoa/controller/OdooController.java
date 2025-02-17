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

    private final String ODOO_URL = "http://192.168.8.214:8069/simarropopgrupoa/api";

    @PostMapping("/request")
    public ResponseEntity<String> sendRequestToOdoo() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Obtener la cookie de sesión y agregarla a los headers
        String sessionCookie = obtenerSesionOdoo();
        if (sessionCookie != null) {
            headers.set("Cookie", sessionCookie);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error: No se pudo obtener la sesión de Odoo");
        }

        // JSON que se enviará a Odoo
        Map<String, Object> data = new HashMap<>();
        data.put("campo1", "teta1");
        data.put("campo2", "valor2");

        try {
            // Convertir a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(data);

            // Enviar la petición con la cookie de sesión
            HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://192.168.8.214:8069/simarropopgrupoa/api",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error comunicando con Odoo: " + e.getMessage());
        }
    }

    @PostMapping("/request/admins")
    public ResponseEntity<String> sendAdminsToOdoo() {
        // Paso 1: Obtener los usuarios desde localhost:8080/usuarios
        RestTemplate restTemplate = new RestTemplate();
        String usuariosUrl = "http://localhost:8080/usuarios";
        ResponseEntity<List> usuariosResponse = restTemplate.exchange(
                usuariosUrl,
                HttpMethod.GET,
                null,
                List.class
        );

        if (usuariosResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener usuarios desde localhost:8080/usuarios");
        }

        // Paso 2: Filtrar los usuarios con admin: true
        List<Map<String, Object>> admins = new ArrayList<>();
        List<Map<String, Object>> usuarios = usuariosResponse.getBody();
        for (Map<String, Object> usuario : usuarios) {
            if (usuario.get("admin") != null && (boolean) usuario.get("admin")) {
                admins.add(usuario);
            }
        }

        // Paso 3: Enviar los usuarios admin a Odoo
        if (!admins.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String sessionCookie = obtenerSesionOdoo();
            if (sessionCookie != null) {
                headers.set("Cookie", sessionCookie);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Error: No se pudo obtener la sesión de Odoo");
            }

            // Crear la carga útil con los usuarios admin
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String requestJson = objectMapper.writeValueAsString(admins);

                // Enviar la petición con los usuarios admin
                HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
                ResponseEntity<String> response = restTemplate.exchange(
                        "http://192.168.8.214:8069/simarropopgrupoa/api",
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );

                return ResponseEntity.ok(response.getBody());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error comunicando con Odoo: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No se encontraron usuarios admin");
        }
    }

    // Metodo opcional para obtener sesión en Odoo
    private String obtenerSesionOdoo() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // JSON correcto para autenticación JSON-RPC en Odoo
        Map<String, Object> authPayload = new HashMap<>();
        authPayload.put("jsonrpc", "2.0");
        authPayload.put("method", "call");
        authPayload.put("id", 1);

        Map<String, Object> params = new HashMap<>();
        params.put("db", "simarropopdb");
        params.put("login", "admin");
        params.put("password", "admin");

        authPayload.put("params", params);

        // Convertir a JSON y enviar la petición
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(authPayload, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://192.168.8.214:8069/web/session/authenticate",
                request,
                String.class
        );

        // Obtener la cookie de sesión de los headers
        List<String> cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        if (cookies != null && !cookies.isEmpty()) {
            System.out.println("Cookie obtenida: " + cookies.get(0)); // Debug
            return cookies.get(0); // Retorna la cookie completa
        }
        return null; // Si no hay cookie, devuelve null
    }

}