package com.example.kepoapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kepoapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.zip.Inflater;

public class BottomSheet extends BottomSheetDialogFragment {


    public int flag;

    public BottomSheet(int flag){
        this.flag = flag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View views = inflater.inflate(R.layout.bottom_dialog,container,false);
        TextView error = views.findViewById(R.id.errordesc);
        Button ok = views.findViewById(R.id.okBtn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if(flag == 1){
            error.setText("Username and Password field must be filled");
        }
        else if(flag == 2){
            error.setText("Username already exist");
        }

        return views;
    }
}
