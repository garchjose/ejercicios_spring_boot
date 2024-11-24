package com.ejercicio.estructuras.controlador;

import org.springframework.http.MediaType; //Especificamos el tipo de contenido de respuesta (texto plano)
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

	/**
	 * Generamos la tabla de multiplicar de un número del 1 al 10.
	 * 
	 * Este método procesa una solicitud HTTP POST en la ruta `/tablaMultiplicar`.
	 * Recibe un número como parámetro y construye una tabla de multiplicar en
	 * formato de texto plano. El resultado incluye las multiplicaciones desde 1
	 * hasta 10 para el número proporcionado.
	 * 
	 * @param numero El número entero del cual se generará la tabla de multiplicar.
	 * @return La tabla de multiplicar del número recibido, como texto plano.
	 */

	@PostMapping(value = "/tablaMultiplicar", produces = MediaType.TEXT_PLAIN_VALUE)

	public String tabla(@RequestParam int numero) {
		String resultado = "Tabla de multiplicar del " + numero + ":\n"; // Inicializamos la variable resultado con el
																			// encabezado correspondiente
		resultado += "--------------------\n";

		for (int i = 1; i <= 10; i++) { // Generamos las multiplicaciones del 1 al 10 con for
			resultado += numero + " x " + i + " = " + (numero * i) + "\n";
		}

		return resultado; // Devolvemos la tabla como texto plano
	}

}
