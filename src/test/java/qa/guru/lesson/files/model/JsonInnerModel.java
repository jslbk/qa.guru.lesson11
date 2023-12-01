package qa.guru.lesson.files.model;

import com.google.gson.annotations.SerializedName;

public class JsonInnerModel {

    @SerializedName("first_name")
    public String firstName;
    private String email;
    private String gender;
    @SerializedName("ip_address")
    private String ipAddress;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
