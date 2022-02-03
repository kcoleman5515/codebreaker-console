package edu.cnm.deepdive.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.model.Game;
import java.io.IOException;
import java.util.Properties;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface CodebreakerServiceProxy {

  String ISO_8601_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  @POST("games")
  Call<Game> startGame(@Body Game game);

  static CodebreakerServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }
  class InstanceHolder {

    private static final String PROPERTIES_FILE = "local.properties";
    private static final String BASE_URL_KEY = "base_url";
    private static final CodebreakerServiceProxy INSTANCE;


    static {
      try {
        Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat(ISO_8601_DATETIME_FORMAT)
            .create();
        Properties props = new Properties();
        props.load(InstanceHolder.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(props.getProperty(BASE_URL_KEY))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
        INSTANCE = retrofit.create(CodebreakerServiceProxy.class);
      } catch (IOException e) {
       throw new RuntimeException(e);
      }
    }

  }

}
