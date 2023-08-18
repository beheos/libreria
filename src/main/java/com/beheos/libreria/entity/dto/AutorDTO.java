package com.beheos.libreria.entity.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AutorDTO {
	
	private Long id;
	@NotEmpty(message = "{autor.nombre.vacio}")
	private String nombre;
	@NotEmpty(message = "{autor.nacionalidad.vacio}")
	private String nacionalidad;

}
