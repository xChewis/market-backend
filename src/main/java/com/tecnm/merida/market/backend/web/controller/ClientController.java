package com.tecnm.merida.market.backend.web.controller;

import com.tecnm.merida.market.backend.domain.Client;
import com.tecnm.merida.market.backend.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "Manage your clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Operation(summary = "Get all clients",
            description = "Return a list of all clients")
    @ApiResponse(responseCode = "200",
            description = "Successful retrieval of clients")
    @ApiResponse(responseCode = "500",
            description = "Internal Server Error")
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID",
            description = "Return a client by its ID if it exists")
    @ApiResponse(responseCode = "200",
            description = "Client found")
    @ApiResponse(responseCode = "404",
            description = "Client not found")
    public ResponseEntity<Client> getClient(
            @Parameter(description = "ID of the client to be retrieved",
                    example = "CLI-001",
                    required = true)
            @PathVariable("id") String clientId) {
        return clientService.getClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    @Operation(summary = "Create a client",
            description = "Save a new client. The client ID is sent in the body")
    @ApiResponse(responseCode = "201",
            description = "Client created")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a client",
            description = "Replace the data of an existing client")
    @ApiResponse(responseCode = "200",
            description = "Client updated")
    @ApiResponse(responseCode = "404",
            description = "Client not found")
    public ResponseEntity<Client> update(
            @Parameter(description = "ID of the client to be updated",
                    example = "CLI-001",
                    required = true)
            @PathVariable("id") String clientId,
            @RequestBody Client client) {
        return clientService.update(clientId, client)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client",
            description = "Delete a client by its ID if it exists")
    @ApiResponse(responseCode = "200",
            description = "Client deleted")
    @ApiResponse(responseCode = "404",
            description = "Client not found")
    @ApiResponse(responseCode = "409",
            description = "The client still has purchases")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the client to be deleted",
                    example = "CLI-001",
                    required = true)
            @PathVariable("id") String clientId) {
        if (clientService.delete(clientId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
