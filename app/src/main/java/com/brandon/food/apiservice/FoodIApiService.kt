package com.brandon.food.apiservice

import com.brandon.food.inventory.api.DeleteIngredientRequest
import com.brandon.food.inventory.api.DeleteIngredientResponse
import com.brandon.food.signin.api.LoginResponse
import com.brandon.food.singup.api.RegisterResponse
import com.brandon.food.inventory.api.IngredientResponse
import com.brandon.food.main.api.MainRecipeResponse
import com.brandon.food.mainRecipeDetail.api.DetailResponse
import com.brandon.food.mainRecipeDetail.model.Ingredient
import com.brandon.food.signin.api.SignInRequest
import com.brandon.food.singup.api.SignUpRequest
import retrofit2.Response
import retrofit2.http.*

interface FoodIApiService {
        @GET("recipe/mainrecipes")
        suspend fun getMainRecipes(@Query("email", encoded = true) email: String): Response<MainRecipeResponse>

        @GET("user/ingredients")
        suspend fun getIngredients(@Query("email", encoded = true) email: String): Response<IngredientResponse>

        @GET("recipe/seewhatyougot")
        suspend fun seeWhatYouGot(@Query("email", encoded = true) email: String, @Query("recipe", encoded = true) name: String): Response<DetailResponse>

        @POST("user/signup")
        suspend fun signUpUser(@Body request : SignUpRequest): Response<RegisterResponse>

        @POST("user/login")
        suspend fun signInUser(@Body request : SignInRequest): Response<LoginResponse>

        @POST("user/deleteingredient")
        suspend fun deleteIngredient(@Body request : DeleteIngredientRequest): Response<DeleteIngredientResponse>


}



