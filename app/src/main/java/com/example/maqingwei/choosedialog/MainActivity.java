package com.example.maqingwei.choosedialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button selectPhoto = (Button) findViewById(R.id.btn_choose);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog();
            }
        });
    }

    private  void  initDialog(){
        ChooseDialog dialog = new ChooseDialog(this, new ChooseDialog.ChooseListener() {
            @Override
            public void choosePhoto() {
                Toast.makeText(MainActivity.this,"点击了选择相册",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void chooseCamer() {
                Toast.makeText(MainActivity.this,"点击了选择相机",Toast.LENGTH_SHORT).show();

            }
        });
        dialog.show();
    }

}
