import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.IOException;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.*;

// import com.google.gson.Gson;

public class PostServer {
    
    static final int port = 18002;

    public static  List<String> AsonCommands = Arrays.asList(
//	"{\"sensors\":[],\"command_type\":\"start\",\"compress\":false,\"freq\":\"2\",\"command\":\"\"}",
//	"{\"sensors\":[],\"command_type\":\"null\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
 //	"{\"sensors\":[],\"command_type\":\"null\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
//	"{\"sensors\":[],\"command_type\":\"stop\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
/*	"{\"sensors\":[\"battery\",\"compass\"],\"command_type\":\"start\",\"compress\":false,\"freq\":\"10\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[\"accelerometer\"],\"command_type\":\"remove\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[],\"command_type\":\"stop\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[\"accelerometer\"],\"command_type\":\"add\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[],\"command_type\":\"send\",\"compress\":false,\"freq\":\"\",\"command\":\"last\"}",
	"null",
	"{\"sensors\":[\"battery\",\"compass\"],\"command_type\":\"start\",\"compress\":false,\"freq\":\"10\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[\"accelerometer\"],\"command_type\":\"remove\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[],\"command_type\":\"stop\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[\"accelerometer\"],\"command_type\":\"add\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[],\"command_type\":\"send\",\"compress\":false,\"freq\":\"\",\"command\":\"last\"}",
	"null",
	"{\"sensors\":[],\"command_type\":\"send\",\"compress\":false,\"freq\":\"\",\"command\":\"all\"}",
	"null",
	"{\"sensors\":[\"Phone Microphone\"],\"command_type\":\"remove\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[\"battery\",\"compass\"],\"command_type\":\"start\",\"compress\":false,\"freq\":\"10\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[],\"command_type\":\"stop\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"null"*/
	"{\"sensors\":[\"battery\",\"compass\"],\"command_type\":\"start\",\"compress\":false,\"freq\":\"10\",\"command\":\"\"}",
	"null",
	"{\"sensors\":[],\"command_type\":\"stop\",\"compress\":false,\"freq\":\"\",\"command\":\"\"}",
	"{\"sensors\":[\"compass\"],\"command_type\":\"send\",\"compress\":false,\"freq\":\"\",\"command\":\"time series\"}"
	);
    public static Iterator<String> it = AsonCommands.iterator();

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started at " + port);
        server.createContext("/", new PostHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
    
    public static class PostHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("\n--------------------------------------------");
            System.out.println("Got new POST request -");
            System.out.println("Sender: " + he.getRemoteAddress().toString());
            System.out.println("Protocol: " + he.getProtocol().toString());
            System.out.println("\n** Begin Request body ** \n");

            // read full request into a String object
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String fullrequest = "";
            String line;

            while ((line = br.readLine()) != null) {
            System.out.println(line);
            fullrequest += line;
            }

            br.close();

            System.out.println("\n** End Request body **\n");

	    System.out.println("Please enter a response");

	String response="";
	if (it.hasNext()){
		response = it.next();
	}
	else{
		response = "Hello";
	}
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();

            System.out.println("Sent response.");
            System.out.println("--------------------------------------------\n");
        }
    }
}
