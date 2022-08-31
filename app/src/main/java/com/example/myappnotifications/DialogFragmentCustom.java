package com.example.myappnotifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class DialogFragmentCustom extends androidx.fragment.app.DialogFragment {

    private MyDialogListener myDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        myDialogListener = (MainActivity) getActivity();

        View customAlertView = getLayoutInflater().inflate(R.layout.dialog_custom, null);

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("Alert Dialog")
                .setMessage("Alert Dialog Message")
                .setView(customAlertView)
                .setIcon(R.drawable.ic_alert)
                .show();

        customAlertView.findViewById(R.id.btn_alertDialogCustom_showToast).setOnClickListener(v -> {
            EditText editText = customAlertView.findViewById(R.id.et_dialogCustom);

            //((MainActivity) requireActivity()).showMyDialogResult(editText.getText().toString());

            myDialogListener.onMyDialogResult(editText.getText().toString());

            dismiss();                      // CLOSE customAlertView
        });

        return alertDialog;
    }
}