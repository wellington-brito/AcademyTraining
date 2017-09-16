package wereh.academytraining.apresentacao.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import wereh.academytraining.R;

/**
 * Created by wellington on 15/08/17.
 */

public class DialogBoxSistemaTreino extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Lembre-se de respeitar seu condicionamento físico e nível de\ntreino quando for usar qualquer\nsistema ou protocolo de treino!")
                .setPositiveButton("Entendi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                }).setTitle("Atenção!");
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
