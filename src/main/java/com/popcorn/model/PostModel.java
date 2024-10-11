package com.popcorn.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostModel {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
