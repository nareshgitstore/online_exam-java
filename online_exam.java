import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class online_exam {
    private Map<String, String> userCredentials;
    private Map<String, String> userProfile;
    private Map<String, String> userAnswers;
    private int remainingTime;

    public online_exam() {
        userCredentials = new HashMap<>();
        userProfile = new HashMap<>();
        userAnswers = new HashMap<>();
        remainingTime = 0;
    }

    public void registerUser(String username, String password, String name) {
        userCredentials.put(username, password);
        userProfile.put(username, name);
    }

    public boolean loginUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            String storedPassword = userCredentials.get(username);
            if (password.equals(storedPassword)) {
                return true;
            }
        }
        return false;
    }

    public void updateProfile(String username, String name, String password) {
        if (userCredentials.containsKey(username)) {
            userProfile.put(username, name);
            userCredentials.put(username, password);
            System.out.println("Profile and password updated successfully.");
        } else {
            System.out.println("Invalid username. Profile update failed.");
        }
    }

    public void selectAnswer(String username, String question, String answer) {
        String userQuestion = username + "_" + question;
        userAnswers.put(userQuestion, answer);
    }

    public void startTimer(int minutes) {
        remainingTime = minutes * 60;
        System.out.println("Timer started for " + minutes + " minutes.");
    }

    public void autoSubmit() {
        System.out.println("Auto submit. Exam is submitted.");
        remainingTime = 0;
    }

    public void closeSession() {
        remainingTime = 0;
        System.out.println("Session closed.");
    }

    public void logout() {
        System.out.println("Logged out successfully.");
    }

    public static void main(String[] args) {
        online_exam examinationSystem = new online_exam();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("Online Examination System");
            System.out.println("-------------------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Profile and Password");
            System.out.println("4. Select Answers for MCQs");
            System.out.println("5. Start Timer");
            System.out.println("6. Auto Submit");
            System.out.println("7. Close Session and Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String registerUsername = scanner.next();
                    System.out.print("Enter password: ");
                    String registerPassword = scanner.next();
                    System.out.print("Enter name: ");
                    String registerName = scanner.next();
                    examinationSystem.registerUser(registerUsername, registerPassword, registerName);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.next();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.next();
                    boolean isLoggedIn = examinationSystem.loginUser(loginUsername, loginPassword);
                    if (isLoggedIn) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Invalid username or password. Login failed.");
                    }
                    break;
                case 3:
                    System.out.print("Enter username: ");
                    String updateUsername = scanner.next();
                    System.out.print("Enter name: ");
                    String updateName = scanner.next();
                    System.out.print("Enter new password: ");
                    String updatePassword = scanner.next();
                    examinationSystem.updateProfile(updateUsername, updateName, updatePassword);
                    break;
                case 4:
                    System.out.print("Enter username: ");
                    String selectAnswerUsername = scanner.next();
                    System.out.print("Enter question: ");
                    String question = scanner.next();
                    System.out.print("Enter answer: ");
                    String answer = scanner.next();
                    examinationSystem.selectAnswer(selectAnswerUsername, question, answer);
                    break;
                case 5:
                    System.out.print("Enter time in minutes: ");
                    int minutes = scanner.nextInt();
                    examinationSystem.startTimer(minutes);
                    break;
                case 6:
                    examinationSystem.autoSubmit();
                    break;
                case 7:
                    examinationSystem.closeSession();
                    examinationSystem.logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }
}
