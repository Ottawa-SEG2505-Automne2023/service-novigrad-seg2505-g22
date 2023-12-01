package com.example.segprojet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog2 extends AppCompatDialogFragment {
    private CheckBox AdressProof;
    private CheckBox ProofOfStatus;
    private CheckBox Photo;

    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater  inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate (R.layout.layout_dialog2,null);

        builder.setView(view)
                .setTitle("Select All the Required Documents for this Service")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean IsAdressProofChecked= AdressProof.isChecked();
                        boolean IsProofOfStatusChecked =ProofOfStatus.isChecked();
                        boolean IsPhotoChecked =Photo.isChecked();

                        listener.whoIsChecked( IsAdressProofChecked, IsProofOfStatusChecked,IsPhotoChecked);
                    }
                });

       AdressProof =view.findViewById(R.id.AdressProof);
      ProofOfStatus =view.findViewById(R.id.ProofOfStatus);
        Photo =view.findViewById(R.id.Photo);


        return builder.create();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString()+"Must Implement Example Dialog Listener");
        }
    }

    public interface ExampleDialogListener{
        void  whoIsChecked(boolean IsFirstNameChecked, boolean IsLastNameChecked, boolean IsDateOfBirthChecked);
    }
}
