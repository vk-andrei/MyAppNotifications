package com.example.myappnotifications;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
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
        view.findViewById(R.id.btn_alertDialogCustom).setOnClickListener(v -> showAlertDialogCustom());
        view.findViewById(R.id.btn_dialogFragment).setOnClickListener(v -> showDialogFragment());
        view.findViewById(R.id.btn_dialogFragmentCustom).setOnClickListener(v -> showDialogFragmentCustom());
        view.findViewById(R.id.btn_bottomSheetDialogFragment).setOnClickListener(v -> showBottomSheetDialogFragment());
        view.findViewById(R.id.btn_pushNotification).setOnClickListener(v -> showPushNotification());
        view.findViewById(R.id.btn_alertDialogCustomList).setOnClickListener(v -> showAlertDialogCustomList());
        view.findViewById(R.id.btn_alertDialogCustomListSingleChoice).setOnClickListener(v -> showAlertDialogCustomListSingleChoice());
        view.findViewById(R.id.btn_alertDialogCustomListMultiChoice).setOnClickListener(v -> showAlertDialogCustomListSMultiChoice());

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

    private void showAlertDialogCustom() {

        View customAlertView = getLayoutInflater().inflate(R.layout.dialog_custom, null);

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("Alert Dialog")
                .setMessage("Alert Dialog Message")
                .setView(customAlertView)
                .setIcon(R.drawable.ic_alert)
                .show();

        customAlertView.findViewById(R.id.btn_alertDialogCustom_showToast).setOnClickListener(v -> {
            EditText editText = customAlertView.findViewById(R.id.et_dialogCustom);
            showToast(editText.getText().toString());
            alertDialog.dismiss();                      // CLOSE customAlertView
        });
    }


    // Сохраняет свое состояния при поворте экрана. Плавает в воздухе
    private void showDialogFragment() {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(requireActivity().getSupportFragmentManager(), "sdfsdf");
    }

    private void showDialogFragmentCustom() {
        DialogFragmentCustom dialogFragment = new DialogFragmentCustom();
        dialogFragment.show(requireActivity().getSupportFragmentManager(), "sdfsdf");
    }

    private void showBottomSheetDialogFragment() {
        DialogBottomSheetFragment dialogBottomSheetFragment = new DialogBottomSheetFragment();
        dialogBottomSheetFragment.show(requireActivity().getSupportFragmentManager(), "sdfsdf");
    }

    public final String CHANNEL_ID_ONE = "1";

    public final String CHANNEL_ID_TWO = "2";

    private void showPushNotification() {
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        // Это для новых версий - где предусмотрено несколько каналов для пушей. Раньше был ОДИН канал.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel_1 = new NotificationChannel(CHANNEL_ID_ONE, "Channel One", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel_1.setDescription("This is a channel for something interesting!");
            notificationManager.createNotificationChannel(notificationChannel_1);

            NotificationChannel notificationChannel_2 = new NotificationChannel(CHANNEL_ID_TWO, "Channel Two", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel_2.setDescription("This is a channel for advertisement!");
            notificationManager.createNotificationChannel(notificationChannel_2);
        }

        Notification notification = new NotificationCompat.Builder(requireContext(), CHANNEL_ID_ONE)
                .setContentTitle("Push Title")
                .setContentText("Push Text")
                .setSmallIcon(R.drawable.ic_alert)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        notificationManager.notify(1, notification);

    }

    private void showAlertDialogCustomList() {
        String[] cities = getResources().getStringArray(R.array.Cities);
        new AlertDialog.Builder(requireContext())
                .setTitle("Choose city")
                .setItems(cities, (dialogInterface, i) -> {
                    showToast("You choose " + cities[i] + ".");
                })
                .show();
    }

    private int chosen = -1; // Chosen city

    private void showAlertDialogCustomListSingleChoice() {
        String[] cities = getResources().getStringArray(R.array.Cities);

        // Create builder
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(requireContext());

        myBuilder
                .setTitle("Choose city")
                // Add list of cities. If no one chosen then chosen = -1
                .setSingleChoiceItems(cities, chosen, (dialogInterface, i) -> chosen = i)
                .setNegativeButton("Cancel", (dialogInterface, i) -> showToast("You didn't choose"))
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    if (chosen == -1) {
                        showToast("Ok, you didn't choose");
                    } else {
                        showToast("You choose " + cities[chosen]);
                    }
                });
        myBuilder.show();
    }

    private void showAlertDialogCustomListSMultiChoice() {
        final String[] cities = getResources().getStringArray(R.array.Cities);
        final boolean[] chosen = {false, false, false, false, false};


        // Create builder
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(requireContext());

        myBuilder
                .setTitle("Choose cities:")
                .setMultiChoiceItems(cities, chosen, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        chosen[i] = b;
                    }
                })
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder sb = new StringBuilder("Ok, you choose: ");
                        for (int index = 0; index < chosen.length; index++) {
                            if (chosen[index]) {
                                sb.append(cities[index]);
                                sb.append("; ");
                            }
                        }
                        showToast(sb.toString());
                    }
                });

        myBuilder.show();
    }

}
