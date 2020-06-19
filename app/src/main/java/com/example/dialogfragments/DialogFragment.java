package com.example.dialogfragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends androidx.fragment.app.DialogFragment {

    private static final String ARG_TITLE="title";
    private static final String ARG_MESSAGE="message";
    private static final String ARG_ICON="icon";

    private String title;
    private String message;
    private int icon;

    private OnPositiveClickListener positiveClickListener;
    private OnNegativeClickListener negativeClickListener;
    private OnNeutralClickListener neutralClickListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnPositiveClickListener){
            positiveClickListener=(OnPositiveClickListener) context;
        }
        else
            throw new RuntimeException("Please implement listener positive");

        if(context instanceof OnNegativeClickListener){
            negativeClickListener=(OnNegativeClickListener) context;
        }
        else
            throw new RuntimeException("Please implement listener negative");

        if(context instanceof OnNeutralClickListener){
            neutralClickListener=(OnNeutralClickListener) context;
        }
        else
            throw new RuntimeException("Please implement listener neutral");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        positiveClickListener=null;
        negativeClickListener=null;
        neutralClickListener=null;
    }

    public static DialogFragment newInstance(String title, String message, int icon){
        Bundle bundle=new Bundle();
        bundle.putString(ARG_TITLE,title);
        bundle.putString(ARG_MESSAGE,message);
        bundle.putInt(ARG_ICON,icon);
        DialogFragment dialogFragment=new DialogFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    public DialogFragment() {
        // Required empty public constructor
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//        builder.setTitle(title);
//        builder.setIcon(icon);
//        builder.setMessage(message);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Toast.makeText(getActivity(),"yes clicked",Toast.LENGTH_LONG).show();
//                positiveClickListener.OnPositiveButtonClicked();
//
//            }
//        });
//
//        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//              //  Toast.makeText(getActivity(),"no clicked",Toast.LENGTH_LONG).show();
//                negativeClickListener.OnNegativeButtonClicked();
//            }
//        });
//
//        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //dismiss();
//                neutralClickListener.OnNeutralButtonClicked();
//            }
//        });
//        return builder.create();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.custom_dialog_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dialog_title= view.findViewById(R.id.dialog_title);
        TextView dialog_msg=view.findViewById(R.id.dialog_msg);
        final EditText dialog_edt=view.findViewById(R.id.dialog_edt);
        Button btn_ok=view.findViewById(R.id.btn_ok);

        dialog_title.setText(title);
        dialog_msg.setText(message);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positiveClickListener.OnPositiveButtonClicked(dialog_edt.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args=getArguments();
        if(args!=null){
            title=args.getString(ARG_TITLE);
            message=args.getString(ARG_MESSAGE);
            icon=args.getInt(ARG_ICON);
        }
    }

    public interface OnPositiveClickListener{
        //void OnPositiveButtonClicked();
        void OnPositiveButtonClicked(String txt);
    }

    public interface OnNegativeClickListener{
        void OnNegativeButtonClicked();
    }
    public interface OnNeutralClickListener{
        void OnNeutralButtonClicked();
    }
}
