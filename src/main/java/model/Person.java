package model;

public abstract class Person {
    private final  String id;
    private String name;
    private String email;
    private String password;

    public Person(String id, String name, String email,String password) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty!");
        }
        this.id = id;
       setName(name);
       setEmail(email);
       setPassword(password);
    }

    public String getName() {
        return name;
    }
    public final String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        if(name==null||name.isEmpty()){
            throw new IllegalArgumentException("Name is Empty");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if(email==null||email.isEmpty()){
            throw new IllegalArgumentException("Name is Empty");
        }
        this.email = email;
    }
    public abstract void displayInfo();
}

