package com.orm.io;

import com.orm.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    User user;
    Connection connection;

    /********************* WRITE FILES USING BUFFER WRITER PACKAGE **********************/
    public void writeToFile(List<User> userList, String fileLocation) throws IOException {
        try (BufferedWriter userFile = new BufferedWriter(new FileWriter(fileLocation))) {
            for (User eachUser : userList) {
                userFile.write(eachUser.toString() + "\n");
            }
        }
    }

    /********************* WRITE FILES USING nio PACKAGE **********************/
    public List<String> readFromFile() throws IOException {
        Path dataPath = FileSystems.getDefault().getPath("C:\\Users\\Shadow\\Desktop\\Projects\\custom-java-orm\\CustomORM\\log-file.txt");
        List<String> lines = Files.readAllLines(dataPath);
        for (String line : lines) {
            System.out.println(line);
        }
        return lines;
    }

}