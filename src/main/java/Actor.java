public class Actor {
    int actordID;
    String firstName;
    String lastName;

    public Actor(String actordID, String firstName, int lastName) {
        this.actordID = actordID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getActordID() {
        return actordID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
