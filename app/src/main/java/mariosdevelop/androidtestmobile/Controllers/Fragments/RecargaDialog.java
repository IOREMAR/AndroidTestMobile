package mariosdevelop.androidtestmobile.Controllers.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import mariosdevelop.androidtestmobile.R;

/**
 * Created by marioaguilar on 22/01/18.
 */

public class RecargaDialog extends DialogFragment implements View.OnClickListener {

    private TextView Text;
    Button ButtonCancel;
    Button ButtonAccept;

    public RecargaDialog() {
        // Empty constructor required for DialogFragment
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.recarga_dialog_fragment,container);
        ButtonCancel = (Button) view.findViewById(R.id.CancelarDialog);
        ButtonAccept = (Button) view.findViewById(R.id.AceptarDialog);
        ButtonAccept.setOnClickListener(this);
        ButtonCancel.setOnClickListener(this);

        getDialog().setCancelable(false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);


        return view;

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.CancelarDialog:
            {
                getDialog().dismiss();

                break;
            }

            case R.id.AceptarDialog:

                getActivity().finish();


                break;
        }

    }
}
