package com.sboot.crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.sboot.crud.model.Contact;

public interface ContactDataRepository extends CrudRepository<Contact, Long> {

}
