package com.myblog.myblog11.payload;

import com.myblog.myblog11.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;
    private String email;
    private String text;

}
