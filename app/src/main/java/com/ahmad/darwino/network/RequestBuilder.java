package com.ahmad.darwino.network;

import com.ahmad.darwino.network.mdoels.AccountModel;
import com.ahmad.darwino.network.mdoels.Category;
import com.ahmad.darwino.network.mdoels.Course;
import com.ahmad.darwino.network.mdoels.InputForgotChangePassword;
import com.ahmad.darwino.network.mdoels.InputLoginModel;
import com.ahmad.darwino.network.mdoels.InputSmsModel;
import com.ahmad.darwino.network.mdoels.RegisterModel;
import com.ahmad.darwino.network.mdoels.SimpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestBuilder {


    @POST("Account/ChangePassword")
    AccountModel changePassword(@Body String oldPassword, @Body String newPassword, Callback<AccountModel> callback);

    @POST("Account/Login")
    Call<AccountModel> login(@Body InputLoginModel model);

    @POST("Account/Authenticate")
    Call<AccountModel> verifySms(@Header("Authorization") String token, @Body String smsCode);

    @POST("Account/IsTokenValid")
    Call<String> IsTokenValid(@Header("Authorization") String token);

    @POST("Account/Register")
    Call<AccountModel> register(@Body RegisterModel model);

    @POST("Account/ForgotPasswordSms")
    Call<AccountModel> forgotPasswordSms(@Body String phoneNumber);

    @POST("ACcount/ForgotPasswordCheckSms")
    Call<AccountModel> forgotPasswordCheckSms(@Body InputSmsModel model);

    @POST("ACcount/ForgotPasswordChangePassword")
    Call<AccountModel> forgotPasswordChangePassword(@Body InputForgotChangePassword model);







    @GET("Course/{id}")
    Call<Course> getCourse(@Path("id") int id);

    @GET("Course/top")
    Call<List<Course>> topCourse();

    @GET("Course/list")
    Call<List<Course>> getCourseList(@Query("count") int count, @Query("page") int page);

    @GET("Course/filter")
    Call<List<Course>> getCoursesByCategories(@Body int[] categories,@Query("count") int count, @Query("page") int page);

    @POST("Course/register/{id}")
    Call<SimpleResponse> courseRegister(@Header("Athorization") @Part("id") int id);




    @GET("Categories/list")
    Call<List<Category>> getAllCategories();

}
