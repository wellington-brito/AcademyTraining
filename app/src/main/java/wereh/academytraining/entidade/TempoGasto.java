package wereh.academytraining.entidade;

import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by wellington on 01/07/17.
 */
@DatabaseTable(tableName = "TempoGasto")
public class TempoGasto {

    public TempoGasto(){}

    int id;
    String nomePlnejamento;
    Date diaTreino;
    String tempo;
}
