package com.example.springhw1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Entity
@Table(schema = "users")
public class Document {
    @Id
    private Integer id;
    private String content;
}
