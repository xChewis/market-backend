package com.tecnm.merida.market.backend.web.controller;

import com.tecnm.merida.market.backend.domain.Purchase;
import com.tecnm.merida.market.backend.domain.dto.PurchaseRequestDTO;
import com.tecnm.merida.market.backend.domain.dto.PurchaseUpdateDTO;
import com.tecnm.merida.market.backend.domain.service.PurchaseService;
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
@RequestMapping("/purchases")
@Tag(name = "Purchase", description = "Manage purchases (master-detail)")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("")
    @Operation(summary = "Get all purchases",
            description = "Return all purchases with their products")
    @ApiResponse(responseCode = "200",
            description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500",
            description = "Internal Server Error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get purchase by ID",
            description = "Return a purchase with its products if it exists")
    @ApiResponse(responseCode = "200",
            description = "Purchase found")
    @ApiResponse(responseCode = "404",
            description = "Purchase not found")
    public ResponseEntity<Purchase> getPurchase(
            @Parameter(description = "ID of the purchase", example = "1", required = true)
            @PathVariable("id") int purchaseId) {
        return purchaseService.getPurchase(purchaseId)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/client/{idCliente}")
    @Operation(summary = "Get purchases by client",
            description = "Return all purchases of a client")
    @ApiResponse(responseCode = "200",
            description = "Purchase(s) found")
    @ApiResponse(responseCode = "404",
            description = "The client has no purchases")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "ID of the client", example = "CLI-001", required = true)
            @PathVariable("idCliente") String clientId) {
        return purchaseService.getByClient(clientId)
                .filter(purchases -> !purchases.isEmpty())
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    @Operation(summary = "Create a purchase",
            description = "Receive the header (client, payment method, comment) and the list of "
                    + "purchased products in one JSON, and save everything in one transaction")
    @ApiResponse(responseCode = "201",
            description = "Purchase and its products saved")
    @ApiResponse(responseCode = "409",
            description = "Invalid client or product; nothing was saved (rollback)")
    public ResponseEntity<Purchase> save(@RequestBody PurchaseRequestDTO request) {
        return new ResponseEntity<>(purchaseService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a purchase",
            description = "Update the header of a purchase: payment method, comment and state")
    @ApiResponse(responseCode = "200",
            description = "Purchase updated")
    @ApiResponse(responseCode = "404",
            description = "Purchase not found")
    public ResponseEntity<Purchase> update(
            @Parameter(description = "ID of the purchase", example = "1", required = true)
            @PathVariable("id") int purchaseId,
            @RequestBody PurchaseUpdateDTO request) {
        return purchaseService.update(purchaseId, request)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a purchase",
            description = "Delete a purchase and, by cascade, all of its products")
    @ApiResponse(responseCode = "200",
            description = "Purchase deleted")
    @ApiResponse(responseCode = "404",
            description = "Purchase not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the purchase", example = "1", required = true)
            @PathVariable("id") int purchaseId) {
        if (purchaseService.delete(purchaseId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
