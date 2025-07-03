package model;

public abstract class Person {
    private String name;
    private final String id;
    private String email;

    public Person(String name, String id, String email) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty!");
        }
        this.id = id;
       setName(name);
       setEmail(email);
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

