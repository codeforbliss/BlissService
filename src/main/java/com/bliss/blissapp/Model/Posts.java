package com.bliss.blissapp.Model;
import org.springframework.data.annotation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
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
    private Date postDate;
    private ArrayList<UUID> commentIds = new ArrayList<>();

    public void setCommentIds(ArrayList<UUID> commentIds) {
        this.commentIds = commentIds != null ? commentIds : new ArrayList<>();
    }
}