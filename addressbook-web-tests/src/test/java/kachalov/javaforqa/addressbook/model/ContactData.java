package kachalov.javaforqa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.File;

@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String address;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    @Expose
    private String homePhone;
    @Expose
    private String email_1;
    private String email_2;
    private String email_3;
    private String allEmails;
    @Expose
    private String group;
    private String allPhones;
    @Expose
    private String photoPath;
    private File photo;


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail_1() {
        return email_1;
    }

    public String getEmail_2() {
        return email_2;
    }

    public String getEmail_3() {
        return email_3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getGroup() {
        return group;
    }

    public String getAddress() {
        return address;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withEmail_1(String email_1) {
        this.email_1 = email_1;
        return this;
    }

    public ContactData withEmail_2(String email_2) {
        this.email_2 = email_2;
        return this;
    }

    public ContactData withEmail_3(String email_3) {
        this.email_3 = email_3;
        return this;
    }

    public ContactData withAllEmails (String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.homePhone = home;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", email_1='" + email_1 + '\'' +
                ", email_2='" + email_2 + '\'' +
                ", email_3='" + email_3 + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", group='" + group + '\'' +
                ", allPhones='" + allPhones + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
