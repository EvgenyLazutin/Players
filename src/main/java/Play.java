import data.RegisterPlayer;
import model.Message;
import service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Application for communicate with other Player(s).
 * <p>
 *
 * @author Lazutin Evgeny
 */
public class Play {


    public static void main(String[] args) {

        List<Message> toQueue = new ArrayList<>();
        List<Message> outQueue = new ArrayList<>();
        ReaderMsg readerMsg = new ReaderMsgImpl(toQueue, outQueue);
        WriterMsg writerMsg = new WriterMsgImpl(toQueue, outQueue);
        RegisterPlayer registerPlayer = new RegisterPlayer();

        Player player1 = new PlayerImpl("player1", readerMsg, writerMsg, registerPlayer);
        Player player2 = new PlayerImpl("player2", readerMsg, writerMsg, registerPlayer);

        for (int i = 1; i < 11; i++) {
            int id = player1.sendMessage("Message: "+ i + " send.", "player2");
            player2.receiveMessageByName("player2");
            System.out.printf("Receive Message: %s %n", player1.receiveMessageById(id).getBody());
        }

    }
}
