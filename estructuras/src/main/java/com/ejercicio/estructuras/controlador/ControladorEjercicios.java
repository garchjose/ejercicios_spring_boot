package com.ejercicio.estructuras.controlador;

import java.util.Random;
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

	/**
	 * Calculamos el promedio de una lista de calificaciones.
	 * 
	 * Este método procesa una solicitud HTTP POST en la ruta
	 * `/promedioCalificaciones`. Recibe una lista de calificaciones en formato de
	 * cadena separada por comas, calcula su promedio y determina si la calificación
	 * promedio es suficiente para aprobar (>= 5). Si ocurre un error en el formato
	 * de las calificaciones, devuelve un mensaje de error.
	 * 
	 * @param calificaciones Una cadena con calificaciones separadas por comas. Cada
	 *                       calificación debe ser un número válido.
	 * @return El promedio de las calificaciones acompañado de un mensaje que indica
	 *         si el promedio es "Aprobado" o "Suspenso". Si hay un error de
	 *         formato, devuelve un mensaje de error explicativo.
	 */

	@PostMapping(value = "/promedioCalificaciones", produces = MediaType.TEXT_PLAIN_VALUE)

	public String promedioCalificaciones(@RequestParam String calificaciones) {

		// Dividimos la cadena de calificaciones en un array usando la como de separador

		String[] calificacionesArray = calificaciones.split(",");

		double suma = 0; // Inicializamos la suma de todas las calificaciones obtenidas (notas)
		int cantidad = 0; // Inicializamos la cantidad de calificaciones (numero de modulos)

		try {
			// Iteramos sobre el array de las calificaciones separadas por comas
			for (String calificacion : calificacionesArray) {
				// Convertimos cada calificacion a un double quitando mediante trim los espacios
				// en blanco
				double valor = Double.parseDouble(calificacion.trim());
				// Sumamos el valor con la suma de notas pasadas por el usuario para actualizar
				// del contenido de la suma
				suma += valor;
				// Incrementamos en una unidad su valor
				cantidad++;
			}

			/*
			 * Calculamos el promedio diviendo la suma de calificaciones (notas) entre la
			 * cantidad de calificaciones que se han sumado (modulos)
			 */

			double promedio = suma / cantidad;

			// Mostramos por pantalla un mensaje en funcion de la nota obtenida
			String resultado = "El promedio es: " + promedio + ".\n";
			if (promedio >= 5) {
				resultado += "Aprobado";
			} else {
				resultado += "Suspenso";
			}

			return resultado;

		} catch (NumberFormatException e) { // Capturamos la excepcion si algun valor no es valido

			return "Error: Asegúrate de introducir solo números separados por comas.";
		}
	}

	/**
	 * Calculamos el Índice de Masa Corporal (IMC) y determinamos la categoría
	 * correspondiente.
	 * 
	 * Este método procesa una solicitud HTTP POST en la ruta `/calcularIMC`. Recibe
	 * como parámetros el peso (en kilogramos) y la altura (en metros) del usuario,
	 * calcula el valor del IMC y devuelve un mensaje indicando el IMC y la
	 * categoría a la que pertenece.
	 * 
	 * Categorías del IMC:
	 * 
	 * - Bajo peso: IMC < 18.5 - Normal: 18.5 ≤ IMC < 25 - Sobrepeso: 25 ≤ IMC ≤ 30
	 * - Obesidad: IMC > 30
	 * 
	 * @param peso   El peso del usuario en kilogramos (double).
	 * @param altura La altura del usuario en metros (double).
	 * @return Un mensaje de texto en formato plano indicando el IMC calculado (con
	 *         dos decimales) y la categoría correspondiente.
	 */

	@PostMapping(value = "/calcularIMC", produces = MediaType.TEXT_PLAIN_VALUE)

	public String calcularIMC(@RequestParam double peso, @RequestParam double altura) {

		altura = altura / 100; // Convertimos la altura de centímetros a metros (dividimos por 100).

		double valorImc = peso / (altura * altura); // Calculamos el Índice de Masa Corporal (IMC) usando la fórmula:IMC
													// = peso / (altura^2)

		String mensajeCalculo = "Tu índice de masa corporal es de " + String.format("%.2f", valorImc)
				+ ", que corresponde a una categoría ";

		// Añadimos al mensaje la categoria en funcion del valor del IMC
		if (valorImc < 18.5) {

			mensajeCalculo += "bajo peso";

		} else if (valorImc > 18.5 && valorImc < 25) {

			mensajeCalculo += "normal";

		} else if (valorImc >= 25 && valorImc <= 30) {

			mensajeCalculo += "sobrepeso";

		} else {

			mensajeCalculo += "Obesidad";
		}
		// Devolvemos el mensaje completo, indicando el IMC y su categoria
		return mensajeCalculo;
	}

	/**
	 * Capturamos la puntuación de satisfacción y devuelve un mensaje personalizado
	 * según la puntuación. Este método procesa una solicitud HTTP POST en la ruta
	 * `/encuesta`.
	 *
	 * @param satisfaccion La puntuación de satisfacción enviada desde el formulario
	 *                     (1-5).
	 * @return Un mensaje de agradecimiento personalizado según la puntuación.
	 */
	@PostMapping("/encuesta")
	public String procesarEncuesta(@RequestParam int satisfaccion) {

		String mensaje; // Generamos un mensaje según la puntuación

		switch (satisfaccion) {
		case 1:
		case 2:
			mensaje = "Lamentamos mucho que no estés satisfecho. Gracias por participar en la encuesta.";
			break;
		case 3:
			mensaje = "Gracias por tu opinión. Seguiremos trabajando para mejorar.";
			break;
		case 4:
		case 5:
			mensaje = "¡Nos alegra saber que estás satisfecho! Gracias por tu apoyo.";
			break;
		default:
			mensaje = "Error inesperado.";
		}

		return mensaje;

	}

	/**
	 * Generamos una contraseña aleatoria de la longitud especificada por el
	 * usuario. Este método procesa una solicitud HTTP POST en la ruta
	 * `/generarContrasena`. Recibe un número entero que indica la longitud deseada
	 * para la contraseña. Utiliza un conjunto de caracteres (letras mayúsculas,
	 * minúsculas, números y símbolos) para construir la contraseña aleatoria y la
	 * devuelve como texto plano.
	 * 
	 * @param longitud La longitud de la contraseña que se desea generar. Debe ser
	 *                 un entero positivo.
	 * @return Un mensaje de texto plano que contiene la contraseña generada.
	 */

	@PostMapping(value = "/generarContrasena", produces = MediaType.TEXT_PLAIN_VALUE)
	public String generarContrasena(@RequestParam int longitud) {

		// Definimos los caracteres que pueden formar parte de la contraseña.
		char[] caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();

		// Usamos StringBuilder para construir la contraseña.
		StringBuilder contrasena = new StringBuilder(longitud);
		// Creamos un objeto Random para seleccionar caracteres al azar.
		Random caracterAleatorio = new Random();

		// Generamos una contraseña de la longitud solicitada.
		for (int i = 0; i < longitud; i++) {
			// Generamos en cada iteracion un caracter aleatorio entre 0 y el numero de
			// catacteres
			// y lo agregamos a la contraseña seleccionando un indice aleatorio dentro del
			// array de caracteres
			contrasena.append(caracteres[caracterAleatorio.nextInt(caracteres.length)]);
		}

		// Devolvemos la contraseña generada como texto plano.
		return "Contraseña generada: " + contrasena.toString();
	}

	/**
	 * Generamos la sumatoria desde 1 hasta el numero indicado por el usuario.
	 * 
	 * Este método procesa una solicitud HTTP POST en la ruta `/sumatoria. Recibe
	 * como parámetro un número entero que indica hasta que numero sumamos, y
	 * devuelve la suma desde 1 hasta el numero introducido por el usuario.
	 * 
	 * @param numero el número entero hasta el cual se deseamos calcular la suma
	 *               acumulativa.
	 * @return una cadena de texto indicando el resultado de la sumatoria
	 *         acumulativa.
	 */

	@PostMapping(value = "/sumatoria", produces = MediaType.TEXT_PLAIN_VALUE)

	public String sumatoria(@RequestParam int numero) {
		// Inicializamos la variable suma para acumular el total de la sumatoria.
		int suma = 0;
		// Variable que almacenará el mensaje final a devolver.
		String mensaje = " ";
		// Recorremos desde 1 hasta el número ingresado, sumando cada valor a la
		// variable suma.
		for (int i = 1; i <= numero; i++) {
			suma += i;
		}
		// Construimos el mensaje final con el resultado de la sumatoria acumulativa.
		return mensaje = "La suma acumulativa de 1 a " + numero + " es: " + suma;

	}

}
