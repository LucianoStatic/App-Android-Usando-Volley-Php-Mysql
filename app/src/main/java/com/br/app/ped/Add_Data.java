package com.br.app.ped;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_Data extends AppCompatActivity {
    EditText cpName, cpEmail, cpContact, cpAddress;
    Button btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__data);
        cpName = findViewById(R.id.campoNome);
        cpAddress = findViewById(R.id.campoEndereco);
        cpContact = findViewById(R.id.campoContato);
        cpEmail = findViewById(R.id.campoEmail);
        btn_insert = findViewById(R.id.botaoInserir);

        /*Metodo click botão*/
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Chamando metodo inserir*/
                insertData();
            }

        });

    }

    private void insertData() {
        /*Instanciando os campos em uma variavel pra pegar os dados*/
        final String name = cpName.getText().toString().trim();
        final String email = cpEmail.getText().toString().trim();
        final String contact = cpContact.getText().toString().trim();
        final String address = cpAddress.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");


        if (name.isEmpty()) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show();
            return;
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show();
            return;
        } else if (contact.isEmpty()) {
            Toast.makeText(this, "Enter Contact", Toast.LENGTH_LONG).show();
            return;
        } else if (address.isEmpty()) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_LONG).show();
            return;
        }


        /*Se os campos não tiverem vazios cai aqui*/
        else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://miniproje.000webhostapp.com/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*Se no caso for igual a dados inseridos manda uma mensagem dizendo que deu certo*/
                            if (response.equalsIgnoreCase("Data Inserted")) {
                                Toast.makeText(Add_Data.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            } else { /*Caso nao tenha os dados inseridos*/
                                Toast.makeText(Add_Data.this, response, Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Add_Data.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    /*acho que é aqui onde ele pega os dados dos campos de texto e joga pra salvar no mysql*/
                    params.put("name",name);
                    params.put("email",email);
                    params.put("contact",contact);
                    params.put("address",address);


                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Add_Data.this);
            requestQueue.add(request);



        }

    }


}
