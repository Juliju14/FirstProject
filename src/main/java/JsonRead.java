import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonRead {

         public static List<Language> jsonRead(){

          ObjectMapper objectMapper = new ObjectMapper();
          try {
              List<Language> read =
                      objectMapper.readValue(new File("src\\main\\resources\\language.json"), new TypeReference<List<Language>>(){});
              return read;
          } catch (IOException e) {
               System.out.println(e.getMessage());
          }
return null;
     }
     }
