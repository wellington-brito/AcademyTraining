package wereh.academytraining.exceptions;

/**
 * Created by wellington on 01/05/17.
 */

public class CampoObrigatorioException extends RuntimeException {
    public CampoObrigatorioException(String msg){
        super("Campos com * são obrigatórios - "+msg);
    }
}
