package edu.escuelaing.parcialfinal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.escuelaing.parcialfinal.model.Client;

public class ConcurrentController implements Runnable {

    private static final JSONParser parser = new JSONParser();
    private static final List<String> urls = new ArrayList<>();
    private static final Client client = new Client();
    private static final List<Thread> threads = new ArrayList<>();
    private static final List<String> responses = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        fetchData();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new ConcurrentController());
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void run() {
        Random rand = new Random();
        int n = rand.nextInt(urls.size());
        try {
            String res = client.TestInvoke(urls.get(n));
            JSONObject json = (JSONObject) parser.parse(res);
            System.out.println(json.toJSONString() + "\n");
            responses.add(res);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void fetchData() {
        try (FileReader reader = new FileReader("src/main/resources/templates/MOCK_DATA.json")) {
            JSONArray array = (JSONArray) parser.parse(reader);
            for (Object obj : array) {
                JSONObject jsonObject = (JSONObject) obj;
                String name = (String) jsonObject.get("Name");
                String type = (String) jsonObject.get("type");
                urls.add(String.format("http://localhost:8080/rest?quer=%s&type=%s", name, type));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
