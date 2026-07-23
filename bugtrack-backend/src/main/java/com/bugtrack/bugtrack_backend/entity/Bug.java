package com.bugtrack.bugtrack_backend.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bugs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
public void prePersist() {
    this.createdAt = LocalDateTime.now();
}

    @ManyToOne
    @JoinColumn(name="created_by",nullable = false)
    private User createdBy;

    @ManyToOne
@JoinColumn(name = "assigned_to")
private User assignedTo;


    
}
