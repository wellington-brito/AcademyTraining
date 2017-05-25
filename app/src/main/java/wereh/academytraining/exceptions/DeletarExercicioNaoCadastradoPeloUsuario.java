package wereh.academytraining.exceptions;

/**
 * Created by wellington on 25/05/17.
 */

public class DeletarExercicioNaoCadastradoPeloUsuario extends RuntimeException {
    public DeletarExercicioNaoCadastradoPeloUsuario(String msg){
        super(msg);
    }
}
