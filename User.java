package Twitter;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class User {
    private String id;
    private List<User> followers;
    private List<User> following;
    private List<String> newsFeed;
    private PropertyChangeSupport pcs;
    private long creationTime;
    private long lastUpdateTime;

    public User(String id) {
        this.id = id;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.newsFeed = new ArrayList<>();
        this.pcs = new PropertyChangeSupport(this);
        this.creationTime = System.currentTimeMillis();
        this.lastUpdateTime = this.creationTime;
    }

    public String getID() {
        return this.id;
    }

    public void follow(User user) {
        if (!following.contains(user)) {
            following.add(user);
            user.addFollower(this);
        }
    }

    public void addFollower(User user) {
        if (!followers.contains(user)) {
            followers.add(user);
        }
    }

    public void postTweet(String tweet) {
        newsFeed.add(tweet);
        pcs.firePropertyChange("postTweet", null, tweet);
        updateFollowers(tweet);
        updateLastUpdateTime();
    }

    public void updateNewsFeed(String tweet) {
        newsFeed.add(tweet);
        pcs.firePropertyChange("updateNewsFeed", null, tweet);
        updateLastUpdateTime();
    }

    private void updateFollowers(String tweet) {
        for (User user : followers) {
            user.updateNewsFeed(tweet);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public List<String> getNewsFeed() {
        return new ArrayList<>(newsFeed);
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    private void updateLastUpdateTime() {
        lastUpdateTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return id;
    }
}
