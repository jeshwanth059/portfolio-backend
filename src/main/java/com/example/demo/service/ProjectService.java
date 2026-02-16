package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    // Create new project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
    
    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    // Get project by ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    
    // Get featured projects
    public List<Project> getFeaturedProjects() {
        return projectRepository.findByIsFeaturedTrue();
    }
    
    // Update project
    public Project updateProject(Long id, Project projectDetails) {
        Optional<Project> projectOpt = projectRepository.findById(id);
        if (projectOpt.isPresent()) {
            Project project = projectOpt.get();
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setTechnologies(projectDetails.getTechnologies());
            project.setGithubUrl(projectDetails.getGithubUrl());
            project.setLiveUrl(projectDetails.getLiveUrl());
            project.setImageUrl(projectDetails.getImageUrl());
            project.setHighlights(projectDetails.getHighlights());
            project.setIsFeatured(projectDetails.getIsFeatured());
            return projectRepository.save(project);
        }
        throw new RuntimeException("Project not found with id: " + id);
    }
    
    // Delete project
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
    
    // Search projects
    public List<Project> searchProjects(String query) {
        return projectRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            query, query
        );
    }
    
    // Get projects by technology
    public List<Project> getProjectsByTechnology(String technology) {
        return projectRepository.findByTechnologiesContaining(technology);
    }
}
