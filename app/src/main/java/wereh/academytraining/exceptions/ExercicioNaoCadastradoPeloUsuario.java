package wereh.academytraining.exceptions;

/**
 * Created by wellington on 25/05/17.
 */

public class ExercicioNaoCadastradoPeloUsuario extends RuntimeException {
    public ExercicioNaoCadastradoPeloUsuario(String msg){
        super(msg);
    }
}
