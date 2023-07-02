package Twitter;

public class ConcreteVisitor implements Visitor {
    private int userCount = 0;
    private int groupCount = 0;

    public void visit(User user) {
        userCount++;
    }

    public void visit(UserGroup group) {
        groupCount++;
        for (Object member : group.getUsers()) {
            if (member instanceof User) {
                visit((User) member);
            } else if (member instanceof UserGroup) {
                visit((UserGroup) member);
            }
        }
    }
// get total users
    public int getUserCount() {
        return userCount;
    }
// get total users
    public int getGroupCount() {
        return groupCount;
    }
}
