package com.ironman.restaurantmanagement.persistence.entity;

/*
 id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(200) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(200) COLLATE pg_catalog."default" NOT NULL,
    profile_id integer,
    state character(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 'A'::bpchar,
    created_at timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone,
*/

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// JPA annotation
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @Column(name = "last_name")
    public String lastName;

    public String email;
    public String password;

    @Column(name = "profile_id")
    public Long profileId;

    public String state;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
