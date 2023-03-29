package Handlers;

import Service.PersonService;
import Service.PersonsService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import result.GenericResponse;
import result.PersonResponse;
import result.PersonsResponse;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler{

    Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException{
        try{
            if(exchange.getRequestMethod().toLowerCase().equals("get")){

                String urlPath = exchange.getRequestURI().toString();

                String[] paths = urlPath.split("/");

                //Get single person
                if(paths.length == 3){
                    PersonService service = new PersonService();

                    PersonResponse response = service.person(exchange.getRequestHeaders().getFirst("Authorization"),
                                                             paths[2]);

                    if(response.isSuccess()){
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else{
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
                    gson.toJson(response, resBody);
                    resBody.close();
                } else{ // Get all persons
                    PersonsService service = new PersonsService();

                    PersonsResponse response = service.persons(exchange.getRequestHeaders().getFirst("Authorization"));

                    if(response.isSuccess()){
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else{
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
                    gson.toJson(response, resBody);
                    resBody.close();
                }

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
