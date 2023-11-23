package com.example.segprojet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog3 extends AppCompatDialogFragment  {

    private Button deleteBtn;
    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater  inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate (R.layout.layout_dialog3,null);

        builder.setView(view)
                .setTitle("WARNING")
                .setMessage("Etes vous sur de vouloir supprimer cet élément?")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        listener.DeleteItem();
                    }
                });


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
        void  DeleteItem();
    }
}

