package edu.sitengvirginia.stockpricechecker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;


/**

 Assignment Notes: This helper class is provided if you decide to use
 the Retrofit REST API.  It is not required to use Retrofit.  If you choose to use this,
 you do not need to edit the code below, but you can if desired.

 */

public interface LousListAPIInterface {

   // @GET("prices?identifier=AAPL&api_key=OjQxNmI3YWI5MDhiYjU5ZDllMTk4MTBmZDM0YjQzZDU2")
    @GET("prices")

    Call<stockkey> get(@Query("identifier") String comp, @Query("api_key") String key);
  //  Call<stockkey> sectionList();


}
