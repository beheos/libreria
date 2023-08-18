package com.beheos.libreria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.beheos.libreria.dao.IAutorDao;
import com.beheos.libreria.dao.ILibroDao;
import com.beheos.libreria.entity.Autor;
import com.beheos.libreria.entity.Libro;
import com.beheos.libreria.entity.dto.AutorDTO;
import com.beheos.libreria.entity.dto.AutorRespuesta;
import com.beheos.libreria.entity.dto.LibroDTO;
import com.beheos.libreria.entity.dto.LibroRespuesta;

@Service
public class LibreriaServiceImpl implements ILibreriaService{

	@Autowired
	ILibroDao iLibroDao;
	@Autowired
	IAutorDao iAutorDao;
	@Autowired
	ModelMapper modelMapper; 
	
	@Override
	public AutorDTO guardarAutor(AutorDTO autorDTO) {
		Autor autor = mapearAutorEntidad(autorDTO); 
		autor = iAutorDao.save(autor);
		autorDTO = mapearAutorDTO(autor);
		return autorDTO;
	}

	@Override
	public LibroDTO guardarLibro(LibroDTO libroDTO) {
		Libro libro = mapearLibroEntidad(libroDTO);
		libro = iLibroDao.save(libro);
		libroDTO = mapearLibroDTO(libro);
		return libroDTO;
	}

	@Override
	public AutorDTO getAutor(Long idAutor) {
		Autor autor = iAutorDao.findById(idAutor).orElse(null);
		return mapearAutorDTO(autor);
	}

	@Override
	public LibroDTO getLibro(Long idLibro) {
		Libro libro = iLibroDao.findById(idLibro).orElse(null);
		return mapearLibroDTO(libro);
	}

	@Override
	public void eliminarAutor(Long idAutor) {
		iAutorDao.deleteById(idAutor);
	}

	@Override
	public void eliminarLibro(Long idLibro) {
		iLibroDao.deleteById(idLibro);
	}
	
	private AutorDTO mapearAutorDTO(Autor autor) {
		AutorDTO autorDTO = modelMapper.map(autor, AutorDTO.class);
		return autorDTO;
	}
	
	private Autor mapearAutorEntidad(AutorDTO autorDTO) {
		Autor autor = modelMapper.map(autorDTO, Autor.class);
		return autor;
	}
	
	private LibroDTO mapearLibroDTO(Libro libro) {
		LibroDTO libroDTO = modelMapper.map(libro, LibroDTO.class);
		return libroDTO;
	}
	
	private Libro mapearLibroEntidad(LibroDTO libroDTO) {
		Libro libro = modelMapper.map(libroDTO, Libro.class);
		return libro;
	}

	@Override
	public AutorRespuesta obtenerTodoslosAutores(int numPaginas, int medidaPagina, String ordenarPor, String orden) {
		Sort sort = orden.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numPaginas, medidaPagina, sort);
		Page<Autor>autor = iAutorDao.findAll(pageable);
		List<Autor> listaAutores = autor.getContent();
		List<AutorDTO> contenido = listaAutores.stream().map(aut -> mapearAutorDTO(aut)).collect(Collectors.toList());
		AutorRespuesta autorRespuesta = new AutorRespuesta();
		autorRespuesta.setContenido(contenido);
		autorRespuesta.setNumPaginas(autor.getNumber());
		autorRespuesta.setMedidaPagina(autor.getSize());
		autorRespuesta.setTotalElementos(autor.getTotalElements());
		autorRespuesta.setTotalPaginas(autor.getTotalPages());
		autorRespuesta.setUltimas(autor.isLast());
		return autorRespuesta;
	}

	@Override
	public LibroRespuesta obtenerTodoslosLibros(int numPaginas, int medidaPagina, String ordenarPor, String orden) {
		Sort sort = orden.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numPaginas, medidaPagina, sort);
		Page<Libro>libro = iLibroDao.findAll(pageable);
		List<Libro> listaLibros = libro.getContent();
		List<LibroDTO> contenido = listaLibros.stream().map(lib -> mapearLibroDTO(lib)).collect(Collectors.toList());
		LibroRespuesta libroRespuesta = new LibroRespuesta();
		libroRespuesta.setContenido(contenido);
		libroRespuesta.setNumPaginas(libro.getNumber());
		libroRespuesta.setMedidaPagina(libro.getSize());
		libroRespuesta.setTotalElementos(libro.getTotalElements());
		libroRespuesta.setTotalPaginas(libro.getTotalPages());
		libroRespuesta.setUltimas(libro.isLast());
		return libroRespuesta;
	}
	

}
