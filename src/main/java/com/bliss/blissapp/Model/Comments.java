package com.bliss.blissapp.Model;

import org.springframework.data.annotation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@Document(collection = "comments")
public class Comments {
    @Id
    private UUID id;
    private String text;
    private String author;
    private Date commentDate;
    private String time;
}
