package com.beheos.libreria.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beheos.libreria.entity.Libro;

public interface ILibroDao extends JpaRepository<Libro, Long> {

}
