package com.polina.bookstore.domain;

import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Order
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String name;

    @Nullable
    String middliname;

    String surname;
    Date bithday;
    Date deathday;
}
