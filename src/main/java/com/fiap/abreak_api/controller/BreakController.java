package com.fiap.abreak_api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.abreak_api.dto.BreakDTO;
import com.fiap.abreak_api.dto.RequestBreakDTO;
import com.fiap.abreak_api.service.BreakService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/breaks")
@RequiredArgsConstructor
public class BreakController {

    private final BreakService service;

    @PostMapping
    public ResponseEntity<BreakDTO> registrar(@Valid @RequestBody RequestBreakDTO dto) {
        BreakDTO pausa = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pausa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreakDTO> buscarPorId(@PathVariable Long id) {
        BreakDTO pausa = service.findById(id);
        return ResponseEntity.ok(pausa);
    }

    @GetMapping("/hoje/{usuarioId}")
    public ResponseEntity<List<BreakDTO>> listarPausasHoje(@PathVariable Long usuarioId) {
        List<BreakDTO> pausas = service.getBreaksToday(usuarioId);
        return ResponseEntity.ok(pausas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<BreakDTO>> listarPausasUsuario(
            @PathVariable Long usuarioId,
            @PageableDefault(size = 20, sort = "dataHora", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BreakDTO> pausas = service.getUserBreaks(usuarioId, pageable);
        return ResponseEntity.ok(pausas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
