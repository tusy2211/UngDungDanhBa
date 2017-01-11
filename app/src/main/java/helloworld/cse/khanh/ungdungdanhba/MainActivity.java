package helloworld.cse.khanh.ungdungdanhba;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import helloworld.cse.khanh.ungdungdanhba.adapter.ContactAdapter;
import helloworld.cse.khanh.ungdungdanhba.model.Contact;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Contact> arrayContact;
    private ContactAdapter adapter;
    private EditText edtName;
    private EditText edtNumber;
    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;
    private Button btnAddContact;
    private ListView lvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayContact = new ArrayList<>();
        adapter = new ContactAdapter(this,arrayContact);
        setWidget();
        lvContact.setAdapter(adapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                showDialogConfirm(i);
            }
        });
    }

    public void setWidget(){
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_number);
        rbtnMale = (RadioButton) findViewById(R.id.rbtn_male);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female);
        btnAddContact = (Button) findViewById(R.id.btn_add_contact);
        lvContact = (ListView) findViewById(R.id.lv_contact);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_contact:
                //Todo
                String name = edtName.getText().toString().trim();
                String number  = edtNumber.getText().toString().trim();
                boolean isMale = true;
                if(rbtnMale.isChecked()){
                    isMale = true;
                }else {
                    isMale = false;
                }
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number)){
                    Toast.makeText(this, "Please input number or name", Toast.LENGTH_SHORT).show();
                }else{
                    Contact contact = new Contact(isMale,name,number);
                    arrayContact.add(contact);

                }
                adapter.notifyDataSetChanged();

                break;
        }
    }

    // Tạo Dialog
    public void showDialogConfirm(final int i){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btnCall = (Button) dialog.findViewById(R.id.btnCall);
        Button btnSendMessage = (Button) dialog.findViewById(R.id.btnSendMessage);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCall(i);
            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSendMessage(i);
            }
        });

        dialog.show();
    }

    private void intentSendMessage(int i) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+ arrayContact.get(i).getmNumber()));
        startActivity(intent);
    }

    private void intentCall(int i) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+ arrayContact.get(i).getmNumber()));
        startActivity(intent);
    }
}
