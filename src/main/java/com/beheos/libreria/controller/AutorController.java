package com.beheos.libreria.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beheos.libreria.entity.dto.AutorDTO;
import com.beheos.libreria.entity.dto.AutorRespuesta;
import com.beheos.libreria.service.ILibreriaService;
import com.beheos.libreria.util.Paginado;

@RestController
@RequestMapping("/libreria/autor")
public class AutorController {

	@Autowired
	ILibreriaService iLibreriaService;
	
	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody AutorDTO autorDTO, BindingResult result){
		if (result.hasErrors()) {
	        Map<String, String> errores = new HashMap<>();
	        for (ObjectError error : result.getAllErrors()) {
	            if (error instanceof FieldError) {
	                FieldError fieldError = (FieldError) error;
	                errores.put(fieldError.getField(), fieldError.getDefaultMessage());
	            } else {
	                errores.put(error.getObjectName(), error.getDefaultMessage());
	            }
	        }
	        
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
	    }

		return new ResponseEntity<>(iLibreriaService.guardarAutor(autorDTO), HttpStatus.OK);
	}
	
	@GetMapping("/busquedaAutor/{id}/")
	public ResponseEntity<AutorDTO>busquedaAutor(@PathVariable(value = "id")Long id){
		AutorDTO autorDTO = iLibreriaService.getAutor(id);
		return new ResponseEntity<>(autorDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}/")
	public ResponseEntity<String> eliminar(@PathVariable(value = "id")Long id){
		iLibreriaService.eliminarAutor(id);
		return new ResponseEntity<>("Autor Eliminado", HttpStatus.OK); 
	}
	
	@GetMapping
	public AutorRespuesta listaAutores(
			@RequestParam(value = "pagina", defaultValue = Paginado.NUMERO_DE_PAGINA_POR_DEFECTO, required = false)int numPagina,
			@RequestParam(value = "ePagina", defaultValue = Paginado.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false)int medidaPagina,
			@RequestParam(value = "ordenado", defaultValue = Paginado.ORDENAR_POR_DEFECTO, required = false)String ordenarPor,
			@RequestParam(value = "dir", defaultValue = Paginado.ORDER_DIRECCION_POR_DEFECTO, required = false)String dir) {
		return iLibreriaService.obtenerTodoslosAutores(numPagina, medidaPagina, ordenarPor, ordenarPor);
	}
}
