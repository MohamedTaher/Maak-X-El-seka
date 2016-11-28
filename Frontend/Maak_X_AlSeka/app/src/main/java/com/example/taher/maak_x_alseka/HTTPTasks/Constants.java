package com.example.taher.maak_x_alseka.HTTPTasks;

/**
 * Created by taher on 25/11/16.
 */
public class Constants {
    //public final static String HOST = "http://10.0.2.2:8080/WebService/rest";
    public final static String HOST = "http://maakxelseka-localarea.rhcloud.com/WebService/rest";
    public final static String LOGIN = HOST + "/TaxiServices/login";
    public final static String SIGNUP = HOST + "/TaxiServices/signup";
    public final static String GET_REQUEST_BY_USER_ID = HOST + "/TaxiServices/getRequestsByUserId";
    public final static String GET_CONFIRM_BY_USER_ID = HOST + "/TaxiServices/getConfirmsByUser";
    public final static String GET_REQUEST_BY_ID = HOST + "/TaxiServices/getRequestById";
    public final static String GET_REQUESTS = HOST + "/TaxiServices/getRequests";
    public final static String GET_USER_BY_ID = HOST + "/TaxiServices/getUserById";
    public final static String ADD_REQUEST = HOST + "/TaxiServices/request";
    public final static String REQUEST_TO_REQUEST = HOST + "/TaxiServices/confirm";
    public final static String GET_CONFIRMS_BY_REQUEST = HOST + "/TaxiServices/getConfirmsByRequest";
    public final static String REFUSED_REQUEST = HOST + "/TaxiServices/refusedRequest";
    public final static String ACCEPT_REQUEST = HOST + "/TaxiServices/acceptRequest";

}
