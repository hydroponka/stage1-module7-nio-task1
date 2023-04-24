package com.epam.mjc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) throws IOException {
        String fileContent;
        try (InputStream inputStream = new FileInputStream(file)){
            fileContent = Files.readString(Paths.get(file.getPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException(e);
        }
        Map<String, String> profileData = parseProfileData(fileContent);
        String name = profileData.get("Name");
        Integer age = Integer.parseInt(profileData.get("Age"));
        String email = profileData.get("Email");
        Long phone = Long.parseLong(profileData.get("Phone"));
        return new Profile(name,age,email,phone);
    }

    private Map<String, String> parseProfileData(String fileContent) {
        Map<String, String> profileData = new HashMap<>();
        String[] lines = fileContent.split("\n");
        for (String line: lines) {
            String[] keyValue = line.split(": ");
            profileData.put(keyValue[0], keyValue[1].trim());
        }
        return profileData;
    }
}
