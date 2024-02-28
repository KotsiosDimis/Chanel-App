package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class FilesWalkExample {

  public static void main(String[] args)
    throws IOException {
  
    // Create a try-catch block and
    // provide the directory path of local machine
    try (Stream<Path> filepath = Files.walk(Paths.get("../App/src/"))) {
      // Print the entire path of directories and files
      filepath.forEach(System.out::println);
    }
    // Throw an if directory doesn't exists 
    catch (IOException e) {
      throw new IOException("Directory not found!");
    }
  }
}