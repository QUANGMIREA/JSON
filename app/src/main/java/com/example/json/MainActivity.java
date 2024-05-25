package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import com.example.json.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tạo đối tượng User
        User user = new User("Alice", 25, "alice@example.com");

        // Lưu đối tượng User vào tệp JSON
        saveUserToJson(user, this);

        // Đọc đối tượng User từ tệp JSON
        User readUser = readUserFromJson(this);
        System.out.println("Name: " + readUser.getName());
        System.out.println("Age: " + readUser.getAge());
        System.out.println("Email: " + readUser.getEmail());
    }
        public void saveUserToJson (User user, Context context){
            Gson gson = new Gson();
            String jsonString = gson.toJson(user);

            try (FileOutputStream fos = context.openFileOutput("user.json", Context.MODE_PRIVATE)) {
                fos.write(jsonString.getBytes());
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public User readUserFromJson(Context context) {
            Gson gson = new Gson();
            StringBuilder jsonString = new StringBuilder();

            try (FileInputStream fis = context.openFileInput("user.json");
                 BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return gson.fromJson(jsonString.toString(), User.class);
        }


}