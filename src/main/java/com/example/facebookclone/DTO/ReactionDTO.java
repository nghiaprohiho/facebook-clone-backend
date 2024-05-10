package com.example.facebookclone.DTO;

import com.example.facebookclone.entity.Reaction_Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionDTO {
    private String type;
    private Integer id_account;
    private Integer id_post;
    public ReactionDTO() {}

    public ReactionDTO(Reaction_Post reactionPost) {
        this.type = reactionPost.getType();
        this.id_account = reactionPost.getAccount().getId();
        this.id_post = reactionPost.getPost().getId();
    }
}
