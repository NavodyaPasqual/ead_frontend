package com.example.ead_frontend.ui.EndPoints;

// End points URLs
public class EndPoints {
    //Base URL
    public static String BASE_URL = "http://192.168.1.5:8081/api/";

    //User  EndPoints
    public static String USER_REGISTRATION_URL = BASE_URL + "user/register";
    public static String USER_LOGIN_URL = BASE_URL + "user/login";

    //Shed EndPoints
    public static String SHED_REGISTRATION_URL = BASE_URL + "shed/register";


    //Queue Endpoints
    public static String QUEUE_CREATE_URL = BASE_URL + "queue/create";



}
