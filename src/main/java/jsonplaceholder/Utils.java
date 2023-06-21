package jsonplaceholder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.*;
import jsonplaceholder.object.User;
import jsonplaceholder.object.info.Todos;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Utils {

    private static final String JSON_REQUEST = String.valueOf(fileRead("JsonUser.json"));

    private static final Gson GSON = new Gson();

    private static final String URL = "https://jsonplaceholder.typicode.com/";

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    private static final RequestBody REQUEST_BODY = RequestBody.create(MEDIA_TYPE_JSON, JSON_REQUEST);


    public static User createUser() throws IOException {

        Request reqastBuilder = new Request.Builder()
                .url(URL + "users")
                .post(REQUEST_BODY)
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            System.out.println("Stasus code " + response.code());
            System.out.println("A new object has been created");
            return GSON.fromJson(response.body().string(), User.class);
        }
        return null;
    }

    public static User updateUser(int id) throws IOException {

        Request reqastBuilder = new Request.Builder()
                .url(URL + "users/" + id)
                .put(REQUEST_BODY)
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            System.out.println("Stasus code " + response.code());
            System.out.println("Updating the object");
            return GSON.fromJson(response.body().string(), User.class);
        }
        return null;
    }

    public static String deleteUser(int id) throws IOException {

        Request reqastBuilder = new Request.Builder()
                .url(URL + "users/" + id)
                .delete()
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            System.out.println("Deleting an object by id "  + id);
        }
        return "Stasus code " + response.code();
    }

    public static List<User> getAllUser() throws IOException {

        Request reqastBuilder = new Request.Builder()
                .url(URL + "users")
                .get()
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            System.out.println("Getting information about all users");
            System.out.println("Stasus code " + response.code());
            return GSON.fromJson(responseBody, new TypeToken<List<User>>(){}.getType());
        }
        return null;
    }

    public static User getUserById(int id) throws IOException {

        Request reqastBuilder = new Request.Builder()
                .url(URL + "users/" + id)
                .get()
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            System.out.println("Getting information about the user by id " + id);
            System.out.println("Stasus code " + response.code());
            return GSON.fromJson(responseBody, User.class);
        }
        return null;
    }

    public static Optional<User> getUserByUsername(String username) throws IOException {

        Request reqastBuilder = new Request.Builder()
                .url(URL + "users/?username=" + username)
                .get()
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            System.out.println("Getting information about the user by username " + username);
            System.out.println("Stasus code " + response.code());
            List<User> users = GSON.fromJson(responseBody, new TypeToken<List<User>>() {
            }.getType());
            if (!users.isEmpty()) {
                return Optional.of(users.get(0));
            }
        }

        return null;
    }

    public static String getUserCommentsById(int id) throws IOException {
        String file = "UserComments/user-" + id + "-post-" + id + "-comments.json";

        FileOutputStream out = new FileOutputStream(file);

        Request reqastBuilder = new Request.Builder()
                .url(URL + "posts/" +  id + "/comments")
                .get()
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            System.out.println("Stasus code " + response.code());

            out.write(responseBody.getBytes(StandardCharsets.UTF_8));
            out.close();
        }
        return "A new file has been created: " + file;
    }

    public static List<Todos> getUserTodosById(int id) throws IOException {

        Request reqastBuilder = new Request.Builder()
                .url(URL + "users/" +  id + "/todos/?completed=false")
                .get()
                .build();

        Response response = CLIENT.newCall(reqastBuilder).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            System.out.println("Getting information todos");
            System.out.println("Stasus code " + response.code());
            return GSON.fromJson(responseBody, new TypeToken<List<Todos>>(){}.getType());
        }
        return null;
    }

    public static StringBuilder fileRead(String readFile)  {

        FileInputStream raed = null;
        try {
            raed = new FileInputStream(readFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(raed);
        StringBuilder result = new StringBuilder();

        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine());
        }

        return result;
    }

}
