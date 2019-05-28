package edu.upc.dsa.exam2;

import java.util.List;

import edu.upc.dsa.exam2.models.Element;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiMuseos {
    //consultas al servidor
    @GET("dataset/museus/format/json/pag-ini/1/pag-fi/1")
    Call<List<Element>> getElements( );
}
