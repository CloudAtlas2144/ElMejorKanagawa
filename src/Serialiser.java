import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        Diploma diploma1 = new Diploma(new int[UVCategory.length], new int[Skill.length], 10, true);
        Diploma diploma2 = new Diploma(new int[UVCategory.length], new int[Skill.length], 10, true);
        Diploma diploma3 = new Diploma(new int[UVCategory.length], new int[Skill.length], 10, true);

        ArrayList<Diploma> diplomaList = new ArrayList<Diploma>();
        diplomaList.add(diploma1);
        diplomaList.add(diploma2);
        diplomaList.add(diploma3);

        DiplomaGroup diplomaGroup1 = new DiplomaGroup("Goupe1", diplomaList);

        // ...
        DiplomaGroup diplomaGroup2 = new DiplomaGroup("Goupe2", diplomaList);
        DiplomaGroup diplomaGroup3 = new DiplomaGroup("Goupe2", diplomaList);

        ArrayList<DiplomaGroup> list = new ArrayList<DiplomaGroup>();
        list.add(diplomaGroup1);
        list.add(diplomaGroup2);
        list.add(diplomaGroup3);

        serialize(list);

    }

    public static void serialize(ArrayList<DiplomaGroup> list) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type diplomaType = new TypeToken<ArrayList<DiplomaGroup>>() {
        }.getType();

        String json = gson.toJson(list, diplomaType);

        File file = new File("./diplomas.json");

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
