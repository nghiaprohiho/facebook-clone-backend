package com.example.facebookclone.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comment_post")
public class Comment_Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private LocalDateTime create_time;

    @Column(name = "edit_time")
    private LocalDateTime edit_time;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_comment_id")
    private List<Comment_Post> answers;

    public Comment_Post() {
    }

    public Comment_Post(String content, LocalDateTime create_time) {
        this.content = content;
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(LocalDateTime edit_time) {
        this.edit_time = edit_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment_Post> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Comment_Post> answers) {
        this.answers = answers;
    }

    public void addAnswer(Comment_Post answer) {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        answers.add(answer);
    }
}