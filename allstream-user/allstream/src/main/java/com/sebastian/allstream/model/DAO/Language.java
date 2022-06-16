package com.sebastian.allstream.model.DAO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int languageId;

    @Column(name = "language_name", nullable = false)
    private String languageName;

    @OneToMany(mappedBy = "language", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<User> users;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isValid() {
        return !languageName.isEmpty();
    }
}
