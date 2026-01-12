package com.chris.authentication.auth.entities;

import com.chris.authentication.auth.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;

    private Integer progress;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany
    @JoinTable(
            name = "enrollment_completed_lessons",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"enrollment_id", "lesson_id"}
            )
    )
    private Set<Lesson> completedLessons = new HashSet<>();

    public Set<Lesson> getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(Set<Lesson> completedLessons) {
        this.completedLessons = completedLessons;
    }

    public Enrollment(){
    }

    @PrePersist()
    public void prePersist(){
        this.enrolledAt = LocalDateTime.now();
    }

    public void completeLesson(Lesson lesson){
        if (lesson == null) {
            throw new IllegalArgumentException("La lección no puede ser null");
        }

        if (!lesson.getCourse().getId().equals(this.course.getId())) {
            throw new IllegalStateException(
                    "La lección no pertenece al curso de esta inscripción"
            );
        }

        if (completedLessons.contains(lesson)) {
            return;
        }
        completedLessons.add(lesson);
    }

    public void recalculateProgress(){
        int totalLessons = this.course.getLessons().size();
        int completedLessons = this.completedLessons.size();

        this.progress = (completedLessons * 100) / totalLessons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
