package com.dylanhelps.modernartapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by HJ Chen on 1/23/17.
 */
public class MoreInfoDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_menu_moreoverflow_normal_holo_light);
        DialogInterface.OnClickListener onClickListener=new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        startActivity(Intent.createChooser(
                            new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org/")),
                            "View in MoMA website"
                    )); // end startactivity
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialogInterface.dismiss();
                }
            }

        };
        builder.setMessage("View in MoMA website").setPositiveButton("Yes",onClickListener)
                .setNegativeButton("No",onClickListener);
        return builder.create();
    }

}
