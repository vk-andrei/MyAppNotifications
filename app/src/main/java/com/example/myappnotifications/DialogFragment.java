package com.example.myappnotifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogFragment extends androidx.fragment.app.DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireContext())
                .setTitle("Alert Dialog")
                .setMessage("Alert Dialog Message")
                .setIcon(R.drawable.ic_alert)
                .setPositiveButton("Yes", ((dialogInterface, i) -> showToast("Yes!")))
                .setNegativeButton("No", ((dialogInterface, i) -> showToast("NO!")))
                .setNeutralButton("I don't know...", ((dialogInterface, i) -> showToast("I don't know...")))
                .show();
        //return super.onCreateDialog(savedInstanceState);
    }

    private void showToast(String string) {
        Toast.makeText(requireActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
