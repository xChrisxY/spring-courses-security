package com.chris.authentication.auth.entities;

import com.chris.authentication.auth.enums.Level;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String description;
    private Level level;
    private LocalDateTime published;

    @Embedded
    private Audit audit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User profesor;

}
