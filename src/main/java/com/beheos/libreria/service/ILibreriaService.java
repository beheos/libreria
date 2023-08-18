package com.beheos.libreria.service;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.beheos.libreria.entity.Autor;
import com.beheos.libreria.entity.Libro;
import com.beheos.libreria.entity.dto.AutorDTO;
import com.beheos.libreria.entity.dto.AutorRespuesta;
import com.beheos.libreria.entity.dto.LibroDTO;
import com.beheos.libreria.entity.dto.LibroRespuesta;

public interface ILibreriaService {
	
	public AutorDTO guardarAutor(AutorDTO autorDTO);
	
	public LibroDTO guardarLibro(LibroDTO libroDTO);
	
	public AutorDTO getAutor(Long idAutor);
	
	public LibroDTO getLibro(Long idLibro);
	
	public void  eliminarAutor(Long idAutor);
	
	public void  eliminarLibro(Long idLibro);
	
	public AutorRespuesta obtenerTodoslosAutores(int numPaginas, int medidaPagina, String ordenarPor, String orden);
	
	public LibroRespuesta obtenerTodoslosLibros(int numPaginas, int medidaPagina, String ordenarPor, String orden);
	
	
	
	
	

}
