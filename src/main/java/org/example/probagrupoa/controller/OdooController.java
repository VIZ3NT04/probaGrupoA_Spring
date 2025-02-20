package org.example.probagrupoa.controller;

import org.example.probagrupoa.entity.Usuario;
import org.example.probagrupoa.repository.IUsuarioRepository;
import org.example.probagrupoa.service.IUsuarioService;
import org.example.probagrupoa.service.OdooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odoo")
public class OdooController {

    @Autowired
    private OdooService odooService;

    @Autowired
    private IUsuarioService service;

    @PostMapping("/create")
    public String createPartner(@RequestParam String name, @RequestParam String email) {
        System.out.println("-------------------" + email + "------------------" + name);
        Usuario usuario = service.getUsuarioByEmail(email);
        System.out.println("-------------------" + usuario.getEmail() + "------------------" + usuario.getName());
        if (usuario != null) {

            usuario.setAdmin(true);
            service.modificarUser(usuario);

        }

        try {

            String partnerId = odooService.createPartner(name, email);
            return "Nuevo partner creado con ID: " + partnerId;

        } catch (Exception e) {
            return "Error al crear el partner: " + e.getMessage();
        }

    }
}