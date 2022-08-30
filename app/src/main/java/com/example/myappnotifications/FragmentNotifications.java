package com.example.myappnotifications;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class FragmentNotifications extends Fragment {

     public static FragmentNotifications newInstance() {
        FragmentNotifications fragment = new FragmentNotifications();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_toast).setOnClickListener(v -> showToast());
        view.findViewById(R.id.btn_snackBar).setOnClickListener(v -> showSnackBar(view));
        view.findViewById(R.id.btn_snackBarWithAction).setOnClickListener(v -> showSnackBarWithAction(view));
        view.findViewById(R.id.btn_alertDialog).setOnClickListener(v -> showAlertDialog());

    }

    private void showToast() {
        Toast.makeText(requireActivity(), "Simple Toast", Toast.LENGTH_SHORT).show();
    }

    private void showToast(String string) {
        Toast.makeText(requireActivity(), string, Toast.LENGTH_SHORT).show();
    }

    private void showSnackBar(View view) {
        Snackbar.make(view, "Simple SnackBar", Snackbar.LENGTH_LONG).show();
    }

    private void showSnackBarWithAction(View view) {
         Snackbar.make(view, "SnackBarAction", Snackbar.LENGTH_LONG).setAction("TRY ANOTHER", view1 -> showToast()).show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Alert Dialog")
                .setMessage("Alert Dialog Message")
                .setIcon(R.drawable.ic_alert)
                .setPositiveButton("Yes", ((dialogInterface, i) -> showToast("Yes!")))
                .setNegativeButton("No", ((dialogInterface, i) -> showToast("NO!")))
                .setNeutralButton("I don't know...", ((dialogInterface, i) -> showToast("I don't know...")))
                .show();
    }


}