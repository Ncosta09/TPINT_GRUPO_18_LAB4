package dominio.exceptions;

public class UsuarioExistenteException extends Exception {
    
    public UsuarioExistenteException(String nombreUsuario) {
        super("Ya existe un usuario con el nombre '" + nombreUsuario + "'.");
    }
    
    public UsuarioExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
} 