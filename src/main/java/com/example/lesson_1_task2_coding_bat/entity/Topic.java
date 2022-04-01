package com.example.lesson_1_task2_coding_bat.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ordinal","language_id"})})
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Language language;

    private int ordinal;

    @Column(unique = true)
    private String name;

    private String definition;
    private int starNumbers;
}


