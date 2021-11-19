package booklibrary.models;

import booklibrary.entities.User;

import java.util.UUID;

public class UsersResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private int age;
    private String username;
    private String roles;

    public UsersResponseDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.username = user.getUsername();
        this.roles = user.getRoles();

    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
