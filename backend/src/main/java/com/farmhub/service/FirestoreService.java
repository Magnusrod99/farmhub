// Author: Magnus Rodrigues
// Roll No: 2628
package com.farmhub.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service
public class FirestoreService {
    private final Firestore db;

    public FirestoreService(Firestore db) {
        this.db = db;
    }

    public Map<String, Object> create(
        String collection,
        Map<String, Object> data,
        String uid
    ) throws ExecutionException, InterruptedException {
        data.put("createdBy", uid);
        data.put("createdAt", FieldValue.serverTimestamp());

        DocumentReference reference = db.collection(collection).add(data).get();
        Map<String, Object> result = new HashMap<>(data);
        result.put("id", reference.getId());
        return result;
    }

    public void createWithId(
        String collection,
        String id,
        Map<String, Object> data
    ) throws ExecutionException, InterruptedException {
        data.put("createdAt", FieldValue.serverTimestamp());
        db.collection(collection).document(id).set(data).get();
    }

    public List<Map<String, Object>> list(
        String collection
    ) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> results = new ArrayList<>();

        for (DocumentSnapshot document :
            db.collection(collection).get().get().getDocuments()) {
            Map<String, Object> data = document.getData();
            if (data == null) data = new HashMap<>();
            data.put("id", document.getId());
            results.add(data);
        }
        return results;
    }

    public Map<String, Object> get(
        String collection,
        String id
    ) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection(collection).document(id).get().get();
        if (!document.exists()) {
            throw new NoSuchElementException("Record not found");
        }

        Map<String, Object> data = document.getData();
        if (data == null) data = new HashMap<>();
        data.put("id", document.getId());
        return data;
    }

    public void update(
        String collection,
        String id,
        Map<String, Object> data
    ) throws ExecutionException, InterruptedException {
        data.remove("id");
        data.remove("createdBy");
        data.remove("createdAt");
        data.put("updatedAt", FieldValue.serverTimestamp());
        db.collection(collection).document(id).update(data).get();
    }

    public void delete(
        String collection,
        String id
    ) throws ExecutionException, InterruptedException {
        db.collection(collection).document(id).delete().get();
    }
}
