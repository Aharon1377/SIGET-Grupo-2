package exceptions;

public class AccessNotGrantedException extends Exception {

	public AccessNotGrantedException() {
		// No se necesita hacer nada en el contructor
	}
	@Override
	public String getMessage() {
		return "Acceso no permitido";
	}

}