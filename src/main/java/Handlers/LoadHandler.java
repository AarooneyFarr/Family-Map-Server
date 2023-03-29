package Handlers;

import Service.LoadService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.LoadRequest;
import request.RegisterRequest;
import result.GenericResponse;
import result.RegisterResponse;

import java.io.*;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler{

    Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException{
        try{
            if(exchange.getRequestMethod().toLowerCase().equals("post")){
                Reader reqBody = new InputStreamReader(exchange.getRequestBody());

                LoadRequest request = (LoadRequest) gson.fromJson(reqBody, LoadRequest.class);

                LoadService service = new LoadService();
                GenericResponse response = service.load(request);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
                gson.toJson(response, resBody);
                resBody.close();

            }

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_IMPLEMENTED, 0);
            exchange.getResponseBody().close();

        } catch(IOException e){

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }
}

