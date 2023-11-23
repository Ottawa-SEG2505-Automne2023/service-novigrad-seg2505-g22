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

public class ExampleDialog4 extends AppCompatDialogFragment {

    private CheckBox firstName;
    private CheckBox LastName;
    private CheckBox DateOfBirth;
    private CheckBox Adress;
    private CheckBox LicenseType;
    private CheckBox AdressProof;
    private CheckBox ProofOfStatus;
    private CheckBox Photo;
    private Button deleteBtn;

    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog4, null);

        builder.setView(view)
                .setTitle("Update Or Delete the service")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.DeleteItem();
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean IsFirstNameChecked = firstName.isChecked();
                        boolean IsLastNameChecked = LastName.isChecked();
                        boolean IsDateOfBirthChecked = DateOfBirth.isChecked();
                        boolean IsAdressChecked = Adress.isChecked();
                        boolean IsLicenseTypeChecked = LicenseType.isChecked();
                        boolean IsAdressProofChecked= AdressProof.isChecked();
                        boolean IsProofOfStatusChecked =ProofOfStatus.isChecked();
                        boolean IsPhotoChecked =Photo.isChecked();

                        listener.whoIsChecked(IsFirstNameChecked, IsLastNameChecked, IsDateOfBirthChecked, IsAdressChecked, IsLicenseTypeChecked,IsAdressProofChecked,IsProofOfStatusChecked,IsPhotoChecked);

                    }
                });

        firstName = view.findViewById(R.id.firstName);
        LastName = view.findViewById(R.id.name);
        DateOfBirth = view.findViewById(R.id.dateOfBirth);
        Adress = view.findViewById(R.id.Adress);
        LicenseType = view.findViewById(R.id.licenseType);
        AdressProof =view.findViewById(R.id.AdressProof);
        ProofOfStatus =view.findViewById(R.id.ProofOfStatus);
        Photo =view.findViewById(R.id.Photo);

        return builder.create();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must Implement Example Dialog Listener");
        }
    }

    public interface ExampleDialogListener {
        void whoIsChecked(boolean IsFirstNameChecked, boolean IsLastNameChecked, boolean IsDateOfBirthChecked, boolean IsAdressChecked, boolean IsLicenseTypeChecked,boolean IsAdressProofChecked, boolean IsProofOfStatusChecked, boolean  IsPhotoChecked);
        void  DeleteItem();
    }
}
