package com.example.springboot.app.security.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.springboot.app.security.entity.Rol;
import com.example.springboot.app.security.enums.RolNombre;
import com.example.springboot.app.security.service.RolService;

// Comentar o borrar clase despues si se crea una conexion a una BD que no sea h2, y que este fija
// Srive para crear los roles en la tabla de rol
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        Optional<Rol> radm = rolService.getByRolNombre(RolNombre.ROLE_ADMIN);
        Optional<Rol> ruse = rolService.getByRolNombre(RolNombre.ROLE_USER);

        if(radm.isPresent()) {
        	System.out.println("Ya esta registrado el rol de admin");
        }else {
        	rolService.save(rolAdmin);
        }
        if(ruse.isPresent()) {
        	System.out.println("Ya esta registrado el rol de user");
        }else {
        	rolService.save(rolUser);
        }
    }
}
