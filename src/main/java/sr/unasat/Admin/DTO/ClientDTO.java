package sr.unasat.Admin.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

/*@JsonIgnoreProperties(ignoreUnknown = true)*/
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
//    private LocalDate dob;
    private String gender;
    private String email;
    private String phoneNumber;

   /* @JsonCreator
    public ClientDTO(@JsonProperty("firstname") String firstName) {
        this.firstName = firstName;
    }

    @JsonCreator
    public ClientDTO(@JsonProperty("id")Long id,
                     @JsonProperty("firstName")String firstName,
                     @JsonProperty("lastName")String lastName,
                     @JsonProperty("dob")String dob,
                     @JsonProperty("gender")String gender,
                     @JsonProperty("email")String email,
                     @JsonProperty("phoneNumber")String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
*/
    public ClientDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public LocalDate getDob() {
//        return dob;
//    }

//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
