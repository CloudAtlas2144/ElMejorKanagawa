import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;

import kanagawa.models.Diploma;
import kanagawa.models.DiplomaGroup;
import kanagawa.models.enums.Skill;
import kanagawa.models.enums.UVCategory;

/**
 * Temporary class used to serialize objects to JSON
 */
public class Serialiser {

    public static void main(String[] args) {

        int[] uVArray = { 4, 3, 6, 2 };
        int[] skillArray = { 0, 2, 0, 1, 0, 1, 2, 1 };
        Diploma diploma1 = new Diploma(uVArray, skillArray, 9, true);

        int[] uVArray2 = { 5, 2, 6, 9 };
        int[] skillArray2 = { 2, 0, 0, 1, 1, 1, 0, 1 };
        Diploma diploma2 = new Diploma(uVArray2, skillArray2, 3, true);

        int[] uVArray3 = new int[UVCategory.length];
        int[] skillArray3 = new int[Skill.length];

        uVArray3[UVCategory.CS.toInt()] = 3;
        skillArray3[Skill.LANGUAGE.toInt()] = 5;
        Diploma diploma3 = new Diploma(uVArray3, skillArray3, 9, true);

        ArrayList<Diploma> list1 = new ArrayList<Diploma>();
        list1.add(diploma1);
        list1.add(diploma2);
        list1.add(diploma3);

        DiplomaGroup group = new DiplomaGroup("Groupe0", list1);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type diplomaType = new TypeToken<DiplomaGroup>() {
        }.getType();

        String json = gson.toJson(group, diplomaType);
        JsonElement jsonElement = gson.toJsonTree(group, diplomaType);

        File file = new File("./diploma.json");

        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(json);
            fw.close();

            // JsonReader jsonReader = new JsonReader(new FileReader(file));
            // jsonReader.setLenient(true);

            // do {
            // cardTarget = gson.fromJson(jsonReader, Card.class);
            // cards.add(cardTarget);
            // } while (cardTarget != null);

            // cards = gson.fromJson(jsonReader, cardListType);
            // System.out.println(gson.toJson(cards, cardListType));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);

        // launch(args);
    }
}
