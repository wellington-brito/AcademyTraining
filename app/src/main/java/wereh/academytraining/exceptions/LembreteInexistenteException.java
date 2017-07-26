package wereh.academytraining.exceptions;

/**
 * Created by wellington on 24/07/17.
 */

public class LembreteInexistenteException extends RuntimeException {
    public LembreteInexistenteException(String msg){
        super("Você ainda não ativou este Lembrete"+msg);
    }
}

