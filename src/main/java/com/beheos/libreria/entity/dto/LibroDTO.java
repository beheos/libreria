package com.beheos.libreria.entity.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LibroDTO {
	
	private Long id;
	@NotEmpty(message = "{libro.titulo.vacio}")
	private String titulo;
	@NotNull
	private AutorDTO autor;

}
