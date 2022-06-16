package com.sebastian.allstream.model.DAO;

import javax.persistence.*;

@Entity
@Table(name = "user_favorite")
public class UserFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_favorite_id")
    private int userFavoriteId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title_no", nullable = false)
    private int titleNo;

    public int getUserFavoriteId() {
        return userFavoriteId;
    }

    public void setUserFavoriteId(int userFavoriteId) {
        this.userFavoriteId = userFavoriteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTitleNo() {
        return titleNo;
    }

    public void setTitleNo(int titleNo) {
        this.titleNo = titleNo;
    }
}
