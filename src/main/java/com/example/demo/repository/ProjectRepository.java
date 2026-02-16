package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    // Find featured projects
    List<Project> findByIsFeaturedTrue();
    
    // Find projects by technology
    List<Project> findByTechnologiesContaining(String technology);
    
    // Search projects by title or description
    List<Project> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        String title, String description
    );
}
