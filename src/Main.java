import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by WingS on 2017. aug. 12.
 * <p>
 * Coordinates:
 * search box : 746 70
 * first hero portrait : 371 145
 * chat : 40 682
 */

public class Main {
    private static Robot robot;
    private static Scanner scanner = new Scanner(System.in);
    private static String champ, chat;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Something went wrong : " + e);
        }
        System.out.print("Position the LoL client in the upper left corner.\nMake sure : \n -this window isn't covering the champ selecting area\n -you are running the client in 1280x720 resolution\nIf you type \"0\" for champion name the program will exit.\nNow press [ENTER]!");
    }


    public static void main(String... args) {
        while (true) {
			scanner.nextLine();
            System.out.print("Enter hero name : ");
            Main.champ = scanner.nextLine();
			if(Main.champ.equals("0")){break;}
            System.out.print("Enter chat message : ");
            Main.chat = scanner.nextLine();
            System.err.println("Now press [Enter] when you are in champ select.");
            try {
                System.in.read();
            } catch (IOException e) {
                System.err.println("java.lang.IOException;");
            }
            sendChatMsg();
            searchForChamp();
        }
    }

    private static void searchForChamp() {
        robot.mouseMove(746, 70);
        click();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        copy(Main.champ);
        robot.delay(200);
        robot.mouseMove(371, 145);
        click();
    }

    private static void sendChatMsg() {
        robot.mouseMove(40, 682);
        click();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        copy(Main.chat);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private static void copy(String cpy) {
        StringSelection stringSelection = new StringSelection(cpy);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    private static void click() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
