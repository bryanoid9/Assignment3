package Twitter;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {
    private String id;
    private List<User> users;
    private List<UserGroup> userGroups;

    public UserGroup(String id) {
        this.id = id;
        this.users = new ArrayList<>();
        this.userGroups = new ArrayList<>();
    }
//allows user to add user to usergroup
    public void addUser(User user) {
        this.users.add(user);
    }
//allows user to add subgroups
    public void addGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
    }
//returns IDs
    public String getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    @Override
    public String toString() {
        return id;
    }
}
