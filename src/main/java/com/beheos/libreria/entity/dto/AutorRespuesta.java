package com.beheos.libreria.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class AutorRespuesta {
	
	private List<AutorDTO> contenido;
	private int numPaginas;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultimas;

}
