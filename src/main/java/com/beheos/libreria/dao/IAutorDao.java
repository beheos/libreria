package com.beheos.libreria.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beheos.libreria.entity.Autor;

public interface IAutorDao extends JpaRepository<Autor, Long>{

}
