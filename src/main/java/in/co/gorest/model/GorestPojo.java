package in.co.gorest.model;

public class GorestPojo {
    private String name;
    private String email;
    private String gender;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public static GorestPojo getUserPojo(String name, String email, String gender, String status){
        GorestPojo gorestPojo = new GorestPojo();
        gorestPojo.setName(name);
        gorestPojo.setEmail(email);
        gorestPojo.setGender(gender);
        gorestPojo.setStatus(status);
        return gorestPojo;
    }
}
