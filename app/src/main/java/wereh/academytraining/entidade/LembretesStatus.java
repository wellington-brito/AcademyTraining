package wereh.academytraining.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wellington on 17/07/17.
 */

@DatabaseTable(tableName = "LembretesStatus")
public class LembretesStatus {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private boolean flag;
    @DatabaseField
    private  int tipo;                  // 1 para consumo alimentar e 2 para consumo de água
}
