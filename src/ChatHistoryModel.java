import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatHistoryModel {
    private List<ChatObserver> observers = new ArrayList<>();
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "23111974";

    public void addObserver(ChatObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(ChatObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers(String action, String username, String details) {
        for (ChatObserver observer : observers) {
            observer.update(action, username, details);
        }
    }
    public void saveChat(String username, String userInput, String serverResponse, LocalDateTime timestamp) {
        String SQL = "INSERT INTO chat_history(username, user_input, server_response, timestamp) VALUES(?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, ChatHistoryModel.user, ChatHistoryModel.password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, username);
            pstmt.setString(2, userInput);
            pstmt.setString(3, serverResponse);
            pstmt.setTimestamp(4, Timestamp.valueOf(timestamp));
            pstmt.executeUpdate();
            System.out.println("Chat history saved successfully.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        notifyObservers("Chat Saved", username, "User Input: " + userInput + ", Server Response: " + serverResponse);
    }
    public void deleteChatByUsername(String username) {
        String SQL = "DELETE FROM chat_history WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, ChatHistoryModel.user, ChatHistoryModel.password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, username);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Deleted " + affectedRows + " chat history records for username: " + username);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        notifyObservers("Chat Deleted", username, "");
    }
    public void updateUsernameInChat(String oldUsername, String newUsername) {
        String SQL = "UPDATE chat_history SET username = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, ChatHistoryModel.user, ChatHistoryModel.password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, newUsername);
            pstmt.setString(2, oldUsername);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Updated username from " + oldUsername + " to " + newUsername + " in " + affectedRows + " chat history records.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        notifyObservers("Username Updated", newUsername, "Old Username: " + oldUsername);
    }

    public void readChatByUsername(String username) {
        String SQL = "SELECT id, user_input, server_response, timestamp FROM chat_history WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, ChatHistoryModel.user, ChatHistoryModel.password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("id") +
                        ", User Input: " + rs.getString("user_input") +
                        ", Server Response: " + rs.getString("server_response") +
                        ", Timestamp: " + rs.getTimestamp("timestamp"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
