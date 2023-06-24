package com.example.springhw1.Service;

import com.example.springhw1.Entity.Document;
import com.example.springhw1.Repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Document CreateDocument(Document document) {
        try {
            return documentRepository.save(document);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Already Existed"
            );
        }
    }

    public Document GetDocument(Integer id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not existed"));
    }

    public List<Document> ListDocuments() {
        return documentRepository.findAll();
    }

    public Document UpdateDocument(Integer id, String content) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not existed"));

        document.setContent(content);
        return documentRepository.save(document);
    }

    public void DeleteDocument(Integer id) {
        if (!documentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not existed");
        }

        documentRepository.deleteById(id);
    }
}

