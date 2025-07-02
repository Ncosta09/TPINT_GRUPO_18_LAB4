package dominio.exceptions;

public class TipoCuentaExistenteException extends Exception {
    
    public TipoCuentaExistenteException(String tipoCuenta) {
        super("El cliente ya tiene una cuenta del tipo " + tipoCuenta + ".");
    }
    
    public TipoCuentaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
} 