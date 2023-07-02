package Twitter;

public interface Visitor {
    void visit(User user);
    void visit(UserGroup group);
}
