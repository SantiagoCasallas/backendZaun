package com.social.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "AMIGO")
public class Amigo {

    @EmbeddedId
    private AmigoId id = new AmigoId();

    public AmigoId getId() {
        return id;
    }

    public void setId(AmigoId id) {
        this.id = id;
    }

    public String getConsecUser() {
        return id != null ? id.getConsecUser() : null;
    }

    public void setConsecUser(String consecUser) {
        if (this.id == null) {
            this.id = new AmigoId();
        }
        this.id.setConsecUser(consecUser);
    }

    public String getConsecUser2() {
        return id != null ? id.getConsecUser2() : null;
    }

    public void setConsecUser2(String consecUser2) {
        if (this.id == null) {
            this.id = new AmigoId();
        }
        this.id.setConsecUser2(consecUser2);
    }
}