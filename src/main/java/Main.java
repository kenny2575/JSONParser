import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static String readString(String fileName){
        StringBuilder outString = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = br.readLine()) != null)
            {
                outString.append(s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return outString.toString();
    }

    static List<Employee> jsonToList(String json){
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(json);
            JSONArray jsonObject = (JSONArray) obj;

            for(Object employer : jsonObject){
                JSONObject emloyerRec = (JSONObject) employer;

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Employee employeeObject = gson.fromJson(emloyerRec.toJSONString(), Employee.class);
                list.add(employeeObject);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for(Employee employee : list) {
            System.out.println(employee);
        }
    }
}
