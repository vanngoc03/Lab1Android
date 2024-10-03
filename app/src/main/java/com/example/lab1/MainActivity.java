package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtHoTen, txtSDT;
    Button btn;

    CheckBox cb1,cb2, cb3;
    ImageView imageView;

    Spinner spinnerHometown;

    RadioGroup group;

    RadioButton male, female;
    ListView listView;
    ArrayList<String> data;

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoTen = findViewById(R.id.editTextText);
        txtSDT = findViewById(R.id.editTextText2);
        btn = findViewById(R.id.btnAdd);
        imageView = findViewById(R.id.imageView);
        male = findViewById(R.id.rb_nam);
        female = findViewById(R.id.rb_Nu);
        spinnerHometown = findViewById(R.id.cb_QueQuan);
        listView = findViewById(R.id.listView2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);

        String[] queQuan = {"Quê quán", "Hà Nội", "Nam Định", "Bắc Giang", "Hà Nam"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, queQuan);
        spinnerHometown.setAdapter(spinnerAdapter);


        data = new ArrayList<>();
        data.add("Nguyễn Văn An - Nam - 0963113494 - Hà Nội");
        data.add("Trần Thị Bích - Nữ - 0963113494 - Nam Định");
        data.add("Mai Văn Chiến - Nam - 0963113494 - Hà Nam");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = "";
                if(txtHoTen.getText().toString().isEmpty() || txtSDT.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ họ tên và số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                } else if (txtSDT.getText().toString().matches(reg) == false) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đúng số điện thoại", Toast.LENGTH_SHORT).show();
                    txtSDT.setFocusable(true);
                    return;
                }
                else {
                    info = txtHoTen.getText().toString() + " - " + txtSDT.getText().toString() + " - ";
                }

                if((male.isChecked() == false) && (female.isChecked() == false)) {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    info += (male.isChecked() ? "Nam" : "Nữ") + " - ";
                }

                if(cb1.isChecked()) {
                    info += cb1.getText().toString() +  " - ";
                }

                if(cb2.isChecked()) {
                    info += cb2.getText().toString() + " - ";
                }

                if(cb3.isChecked()) {
                    info += cb3.getText().toString() + " - ";
                }

                if(spinnerHometown.getSelectedItem().toString().equals("Quê quán")) {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn quê quán", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    info += spinnerHometown.getSelectedItem().toString();
                }
                data.add(info);
                adapter.notifyDataSetChanged();
                txtHoTen.setText("");
                txtSDT.setText("");
                male.setChecked(false);
                female.setChecked(false);
                spinnerHometown.setSelection(0);
//                imageView.setImageResource(R.drawable.ic_launcher_background);
            }
        });


    }
}