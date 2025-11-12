package com.fiap.abreak_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.abreak_api.dto.RequestUserDTO;
import com.fiap.abreak_api.dto.UserDTO;
import com.fiap.abreak_api.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody RequestUserDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        service.create(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@RequestParam Long id) {
        return ResponseEntity
                .ok(
                        service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAll(
            @PageableDefault(size = 10, sort = "user_name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity
                .ok(
                        service.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody RequestUserDTO dto) {
        return ResponseEntity
                .ok(
                        service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
