package com.example.myappnotifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogBottomSheetFragment extends BottomSheetDialogFragment {

    private MyDialogListener myDialogListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_custom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDialogListener = (MainActivity) getActivity();

        initView(view);
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_alertDialogCustom_showToast).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.et_dialogCustom);
            //((MainActivity) requireActivity()).showMyDialogResult(editText.getText().toString());

            myDialogListener.onMyDialogResult(editText.getText().toString());

            dismiss();
        });
    }

}
