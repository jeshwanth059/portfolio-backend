package com.example.demo.repository;

import com.example.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    // Find all unread messages
    List<Contact> findByIsReadFalse();
    
    // Find contacts by email
    List<Contact> findByEmailContainingIgnoreCase(String email);
    
    // Count unread messages
    long countByIsReadFalse();
}
