package com.ignite.bookclubapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data @AllArgsConstructor
@Entity
public class Member {

    @Id
    private String id;

    private String name;

    public Member(String name) {
        this(UUID.randomUUID().toString(), name);
    }
}
