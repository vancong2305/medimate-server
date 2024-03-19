package com.example.medimateserver.controller.api;

import com.example.medimateserver.model.Role; // Assuming you have a Role model
import com.example.medimateserver.service.RoleService; // Assuming you have a RoleService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable BigInteger roleId) {
        Role role = roleService.findById(roleId);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<String> updateRole(@PathVariable Long roleId, @RequestBody Role roleDetails) {
        return ResponseEntity.ok("updatedRole");
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable BigInteger roleId) {
        // ... (Implement delete logic with RoleService)
        return ResponseEntity.noContent().build();
    }
}
