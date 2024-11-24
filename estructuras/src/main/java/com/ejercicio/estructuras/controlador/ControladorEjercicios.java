package com.ejercicio.estructuras.controlador;

import org.springframework.web.bind.annotation.PostMapping; //Anotacion para mapear peticiones HTTP POST a los metodos del Controlador
import org.springframework.web.bind.annotation.RequestParam; //Anotacion para extraer parametros de la solicitud HTTP
import org.springframework.web.bind.annotation.RestController; //Marcamos la clase como un controlador REST que devuelve datos directamente en el cuerpo de la respuesta

@RestController // Con esta anotacion manejamos solicitudes HTTP
public class ControladorEjercicios {

	/**
	 * Determinamos si un número es par o impar.
	 * 
	 * Este método procesa una solicitud HTTP POST en la ruta `/parImpar`. Recibe un
	 * número como parámetro, evalúa si es par o impar y devuelve una respuesta
	 * indicando el resultado.
	 * 
	 * @param numero El número entero que se deseamos evaluar.
	 * @return Un mensaje indicando si el número es par o impar.
	 */

	@PostMapping("/parImpar")
	public String parImpar(@RequestParam int numero) {
		String mensaje;

		if (numero % 2 == 0) { // Si es divisible por dos, es decir, es par
			mensaje = "El número " + numero + " es par";
		} else { // Si no es impar
			mensaje = "El número " + numero + " es impar";
		}
		return mensaje;
	}

}
