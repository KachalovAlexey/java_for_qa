package kachalov.javaforqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import kachalov.javaforqa.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    private String firstname [] = {"Petr", "Pavel", "Ivan"};
    private String lastname [] = {"Petrov", "Ivanov", "Sidorov"};
    private String address [] = {"Moscow", "London", "Berlin"};;
    private String mobilePhone [] = {"+7 (923) 123-23-45", "+102 (4124) 234-2342", "+96 (23) 12-123-12"};;
    private String workPhone [] = {"3245", "98761", "35987"};;
    private String homePhone [] = {"214 54 1", "12 21 54", "78878 78"};;
    private String email_1 [] = {"test@acme.com", "acme@info.ru", "info@test.org"};;
    private String photoPath [] = {"src/test/resources/avatars/1.png", "src/test/resources/avatars/2.png", "src/test/resources/avatars/3.png"};


    public static void  main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }

        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
         if (format.equals("xml")){
            saveAsXML(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJSON(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }

    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            Random generate = new Random();
            contacts.add(new ContactData()
                    .withFirstname(firstname[generate.nextInt(3)])
                    .withLastname(lastname[generate.nextInt(3)])
                    .withAddress(address[generate.nextInt(3)])
                    .withHomePhone(homePhone[generate.nextInt(3)])
                    .withMobilePhone(mobilePhone[generate.nextInt(3)])
                    .withWorkPhone(workPhone[generate.nextInt(3)])
                    .withEmail_1(email_1[generate.nextInt(3)])
                    .withGroup("[none]")
                    .withPhotoPath(photoPath[generate.nextInt(3)]));
        }
        return contacts;
    }

}
