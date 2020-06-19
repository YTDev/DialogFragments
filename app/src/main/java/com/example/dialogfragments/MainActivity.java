package com.example.dialogfragments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        DialogFragment.OnPositiveClickListener,
        DialogFragment.OnNegativeClickListener,
        DialogFragment.OnNeutralClickListener {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog dialog=new AlertDialog.Builder(MainActivity.this).
//                        setTitle("Confirmation").setIcon(R.drawable.ic_launcher_background).setMessage("xxxxxxxxxxxxxxx").create();
//                        dialog.show();
                DialogFragment fragment=DialogFragment.newInstance("Confirmation",
                        "Are you sure",
                        R.drawable.ic_assignment_turned_in_black_24dp);
                fragment.show(getSupportFragmentManager(),null);
            }
        });
    }

//    @Override
//    public void OnPositiveButtonClicked() {
//        Toast.makeText(this,"yes clicked",Toast.LENGTH_LONG).show();
//
//    }

    @Override
    public void OnPositiveButtonClicked(String txt) {
        Toast.makeText(this,"You Clicked : "+txt,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void OnNegativeButtonClicked() {
        Toast.makeText(this,"no clicked",Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnNeutralButtonClicked() {

    }


}
