package co.com.ceiba.restaurantapp.domain.strategies;

import co.com.ceiba.restaurantapp.domain.exception.ExceptionsForRstrictions;

public class ArgumentsValidator {

	String fictionalData;

	public ArgumentsValidator(String fictionalData) {
		this.fictionalData = fictionalData;
	}

	public static void restrictionForNull(Object valor, String mensaje) {
		if (valor == null) {
			throw new ExceptionsForRstrictions(mensaje);
		}
	}

	public static void restrictionForValueEmpty(Object valor, String mensaje) {
		if (valor == "") {
			throw new ExceptionsForRstrictions(mensaje);
		}
	}

	public static void restrictionForValueZero(int valor, String mensaje) {
		if (valor == 0) {
			throw new ExceptionsForRstrictions(mensaje);
		}
	}

	public static void restrictionForValueZero(float valor, String mensaje) {
		if (valor == 0) {
			throw new ExceptionsForRstrictions(mensaje);
		}
	}

}
