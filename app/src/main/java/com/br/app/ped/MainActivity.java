package com.br.app.ped;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter myAdapter;

    public static ArrayList<Employe> employeArrayList = new ArrayList<>();

    String url = "https://miniproje.000webhostapp.com/retrieve.php";

    Employe employe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listaTela);

        myAdapter = new MyAdapter(this, employeArrayList);

        listView.setAdapter(myAdapter);

        /*Chamando o metodo que pesquisa os dados no banco para o ListView*/
        retrieveData();

    }


    public void retrieveData() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        employeArrayList.clear();
                        try{
         /*Aqui ele acessa os comandos o php pra pesquisar e vai dizer se deu tudo certo, tipo ele consegue acessar la no php atraves do jobject*/
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(success.equals("1")){

                                for(int i=0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id  = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String contact = object.getString("contact");
                                    String address = object.getString("address");

                                    employe = new Employe(id,name,email,contact,address);
                                    employeArrayList.add(employe); /*Adicione os dados pegos do objeto no arraylist*/
                                    myAdapter.notifyDataSetChanged();
                                }


                            }


                        }catch(JSONException e){
                            e.printStackTrace();
                        }







                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    public void botaoMetodoChamarTelaCadastro(View view) {
        startActivity(new Intent(getApplicationContext(), Add_Data.class));
    }

}
