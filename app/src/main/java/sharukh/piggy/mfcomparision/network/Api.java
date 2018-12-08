package sharukh.piggy.mfcomparision.network;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;
import sharukh.piggy.mfcomparision.data.MFDetails;
import sharukh.piggy.mfcomparision.data.SearchResult;

public interface Api {

    @GET("v1/mf")
    Call<GenericResponse<MFDetails>> getMFDetails(@Query("key") String key);

    @POST("v2/mf/search/")
    Call<GenericResponse<SearchResult>> performSearch(@Body JsonObject data);

}
