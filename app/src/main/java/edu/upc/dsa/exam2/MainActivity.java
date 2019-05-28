package edu.upc.dsa.exam2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import edu.upc.dsa.exam2.models.Element;
import edu.upc.dsa.exam2.models.Museums;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ApiMuseos ApiMuseos;
    private MyAdapter recyclerAdapter;
    private Museums listaMuseos;
    private ProgressDialog dialog;
    private AlertDialog alerta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Loader
        dialog = ProgressDialog.show(MainActivity.this, "", "Loading...", true);

        //RecycleView
        RecyclerView recyclerView = findViewById(R.id.idRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new MyAdapter(this, listaMuseos);
        recyclerView.setAdapter(recyclerAdapter);

        //Api connection
        Gson gson = new GsonBuilder().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://do.diba.cat/api/") //dirección del servidor o ip. (desde la raiz)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        ApiMuseos = retrofit.create(ApiMuseos.class);
        getMuseums();
    }

        private void getMuseums (){
            Call<Museums> call = ApiMuseos.getMuseums();

            call.enqueue(new Callback <Museums>() {
                @Override
                public void onResponse(Call<Museums> call, Response<Museums>response) {
                    if (!response.isSuccessful()){
                        Log.e("Code", Integer.toString(response.code())); //este codigo se refiere a 200,404,...
                        alertshow();
                        return; //el return es como un else
                    }

                    listaMuseos = response.body();
                    recyclerAdapter.setListaMuseos(listaMuseos);

                    dialog.dismiss();

                }

                @Override
                public void onFailure(Call<Museums> call, Throwable t) {
                    Log.e("ERROR", t.getMessage());
                    alertshow();
                }
            });

        }


    private void alertshow() {
        dialog.dismiss();

        alerta = new AlertDialog.Builder(MainActivity.this).create();
        alerta.setTitle("Error");
        alerta.setMessage("Connection error");
        alerta.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alerta.show();
    }






    }

