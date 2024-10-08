package com.bliss.blissapp.Model;
import org.springframework.data.annotation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Posts {
    @Id
    private UUID id;
    private String author;
    private String text;
    private Instant postDate;
    private List<UUID> comments = new ArrayList<>();
}