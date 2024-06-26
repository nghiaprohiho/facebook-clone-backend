package com.example.facebookclone.DTO;

import com.example.facebookclone.entity.Account;
import com.example.facebookclone.entity.Comment_Post;
import com.example.facebookclone.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommentPostDTO {
    private Integer id;
    private String content;
    private LocalDateTime create_time;
    private LocalDateTime edit_time;
    private String image;
    private UserProfileDTO account_user;
    private UserProfileDTO account_tag;
    private Integer id_post;
    private Integer reaction_quantity;
    private List<CommentPostDTO> answers;
    private String reaction;

    public CommentPostDTO() {
    }

    public CommentPostDTO(Comment_Post commentPost) {
        this.id = commentPost.getId();
        this.content = commentPost.getContent();
        this.create_time = commentPost.getCreate_time();
        this.edit_time = commentPost.getEdit_time();
        this.image = commentPost.getImage();
        this.account_user = new UserProfileDTO(commentPost.getAccount());
        this.reaction_quantity = commentPost.getReaction_quantity();
        this.account_tag = (commentPost.getAccount_tag() != null) ? new UserProfileDTO(commentPost.getAccount_tag()) : null;
        this.id_post = (commentPost.getPost() != null) ? commentPost.getPost().getId() : null;
        this.answers = (commentPost.getAnswers() != null) ? commentPost.getAnswers().stream().map(CommentPostDTO::
                new).collect(Collectors.toList()) : List.of();
    }
}
