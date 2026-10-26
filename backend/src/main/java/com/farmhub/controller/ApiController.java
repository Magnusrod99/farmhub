// Author: Magnus Rodrigues
// Roll No: 2628
package com.farmhub.controller;

import com.farmhub.service.FirestoreService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {
    private final FirestoreService store;

    private static final Set<String> COLLECTIONS = Set.of(
        "users",
        "equipment",
        "products",
        "bookings",
        "orders",
        "rentalAgreements",
        "orderItems"
    );

    public ApiController(FirestoreService store) {
        this.store = store;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok", "app", "FarmHub API");
    }

    @PostMapping("/profile")
    public Map<String, Object> saveProfile(
        @RequestBody Map<String, Object> body,
        HttpServletRequest request
    ) throws Exception {
        String uid = (String) request.getAttribute("uid");
        body.put("uid", uid);

        try {
            store.get("users", uid);
            store.update("users", uid, body);
        } catch (NoSuchElementException exception) {
            store.createWithId("users", uid, body);
        }

        return store.get("users", uid);
    }

    @GetMapping("/profile")
    public Map<String, Object> profile(HttpServletRequest request) throws Exception {
        return store.get("users", (String) request.getAttribute("uid"));
    }

    @GetMapping("/{collection}")
    public List<Map<String, Object>> list(@PathVariable String collection) throws Exception {
        check(collection);
        return store.list(collection);
    }

    @PostMapping("/{collection}")
    public Map<String, Object> create(
        @PathVariable String collection,
        @RequestBody Map<String, Object> body,
        HttpServletRequest request
    ) throws Exception {
        check(collection);
        return store.create(
            collection,
            body,
            (String) request.getAttribute("uid")
        );
    }

    @GetMapping("/{collection}/{id}")
    public Map<String, Object> get(
        @PathVariable String collection,
        @PathVariable String id
    ) throws Exception {
        check(collection);
        return store.get(collection, id);
    }

    @PutMapping("/{collection}/{id}")
    public ResponseEntity<Void> update(
        @PathVariable String collection,
        @PathVariable String id,
        @RequestBody Map<String, Object> body
    ) throws Exception {
        check(collection);
        store.update(collection, id, body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{collection}/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable String collection,
        @PathVariable String id
    ) throws Exception {
        check(collection);
        store.delete(collection, id);
        return ResponseEntity.noContent().build();
    }

    private void check(String collection) {
        if (!COLLECTIONS.contains(collection)) {
            throw new IllegalArgumentException("Unsupported collection");
        }
    }
}
