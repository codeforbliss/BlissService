package com.bliss.blissapp.DTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class PostsDTO {
  private UUID id;
  private String text;
  private String author;
  private Instant date;
  private List<CommentsDTO> comments;
}
