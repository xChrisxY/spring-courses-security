package com.chris.authentication.auth.entities;

import com.chris.authentication.auth.enums.Level;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "courses_categories",
            joinColumns =  @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "category_id"})
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    public Course(){
        this.lessons = new HashSet<>();
        this.categories = new HashSet<>();
        this.enrollments = new HashSet<>();
        audit = new Audit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public User getProfesor() {
        return profesor;
    }

    public void setProfesor(User profesor) {
        this.profesor = profesor;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}