package com.example.demo.service;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;
    
    // Save new contact message
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    
    // Get all contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
    // Get contact by ID
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }
    
    // Get unread contacts
    public List<Contact> getUnreadContacts() {
        return contactRepository.findByIsReadFalse();
    }
    
    // Mark contact as read
    public Contact markAsRead(Long id) {
        Optional<Contact> contactOpt = contactRepository.findById(id);
        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            contact.setIsRead(true);
            return contactRepository.save(contact);
        }
        throw new RuntimeException("Contact not found with id: " + id);
    }
    
    // Delete contact
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
    
    // Get unread count
    public long getUnreadCount() {
        return contactRepository.countByIsReadFalse();
    }
    
    // Search contacts by email
    public List<Contact> searchByEmail(String email) {
        return contactRepository.findByEmailContainingIgnoreCase(email);
    }
}
