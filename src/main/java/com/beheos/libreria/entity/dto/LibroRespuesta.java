package com.beheos.libreria.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class LibroRespuesta {
	
	private List<LibroDTO> contenido;
	private int numPaginas;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultimas;

}
