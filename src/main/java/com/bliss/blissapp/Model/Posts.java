package com.bliss.blissapp.Model;

import org.springframework.data.annotation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Posts {
    @Id
    private Object id;
    private String author;
    private String text;
}