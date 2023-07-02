package Twitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class UserView implements PropertyChangeListener {
    private User user;
    private JFrame frame;
    private JTextArea tweetList;
    private JTextField tweetField, followField;
    private DefaultListModel<String> followingModel;
    private DefaultListModel<String> followersModel;
    private static Map<String, UserView> openUserViews = new HashMap<>();


    private Color lightBlue = new Color(135, 206, 235);

    public UserView(final User user) {
        this.user = user;
        user.addPropertyChangeListener(this);
        frame = new JFrame(user.getID());
        frame.getContentPane().setBackground(lightBlue);  // Set frame's background color to light blue

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(lightBlue);  // Set panel's background color to light blue

        JLabel label = new JLabel("Current User: " + user.getID());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        label = new JLabel("News Feed");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        tweetList = new JTextArea();
        tweetList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tweetList);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        panel.add(scrollPane);

        label = new JLabel("Tweet Message");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        tweetField = new JTextField();
        tweetField.setMaximumSize(new Dimension(Integer.MAX_VALUE, tweetField.getMinimumSize().height));
        panel.add(tweetField);

        JButton button = new JButton("Post Tweet");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user.postTweet(tweetField.getText());
                tweetField.setText("");
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);

        label = new JLabel("Follow User");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        followField = new JTextField();
        followField.setMaximumSize(new Dimension(Integer.MAX_VALUE, followField.getMinimumSize().height));
        panel.add(followField);

        button = new JButton("Follow User");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User target = AdminControlPanel.getInstance().getUser(followField.getText());
                if (target != null) {
                    user.follow(target);
                    followField.setText("");
                }
            }
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);

        contentPane.add(panel);

        frame.pack();
        frame.setVisible(true);

        openUserViews.put(user.getID(), this);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("postTweet") || event.getPropertyName().equals("updateNewsFeed")) {
            if (event.getNewValue() instanceof String) {
                displayNewTweet((String) event.getNewValue());
            }
        }
    }
 // Display a new tweet in the news list
    private void displayNewTweet(String tweet) {
        tweetList.append(tweet + "\n");
        tweetList.setCaretPosition(tweetList.getDocument().getLength());
    }
}
