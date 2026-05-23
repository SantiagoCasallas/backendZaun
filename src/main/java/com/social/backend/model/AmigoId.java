package com.social.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AmigoId implements Serializable {

    @Column(name = "consecuser", nullable = false, length = 5)
    private String consecUser;

    @Column(name = "consecuser2", nullable = false, length = 5)
    private String consecUser2;

    public AmigoId() {
    }

    public AmigoId(String consecUser, String consecUser2) {
        this.consecUser = consecUser;
        this.consecUser2 = consecUser2;
    }

    public String getConsecUser() {
        return consecUser;
    }

    public void setConsecUser(String consecUser) {
        this.consecUser = consecUser;
    }

    public String getConsecUser2() {
        return consecUser2;
    }

    public void setConsecUser2(String consecUser2) {
        this.consecUser2 = consecUser2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmigoId amigoId)) return false;
        return Objects.equals(consecUser, amigoId.consecUser)
                && Objects.equals(consecUser2, amigoId.consecUser2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consecUser, consecUser2);
    }
}