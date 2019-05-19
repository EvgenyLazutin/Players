package service;

import data.RegisterPlayer;
import model.Message;
import model.TextMessage;

import java.util.Optional;

/**
 * Implementation for interface {@link Player} for realisation play.
 * <p>
 *
 * @author Lazutin Evgeny
 */
public class PlayerImpl extends Thread implements Player {

    final private String nick;
    final private ReaderMsg readerMsg;
    final private WriterMsg writerMsg;
    final private RegisterPlayer registerPlayer;
    private volatile boolean run;


    public PlayerImpl(String nick, ReaderMsg readerMsg, WriterMsg writerMsg, RegisterPlayer registerPlayer) {
        this.nick = nick;
        this.readerMsg = readerMsg;
        this.writerMsg = writerMsg;
        this.registerPlayer = registerPlayer;
        run = true;
    }

    /**
     * Send message.
     *
     * @param textMessage body of message
     * @param receiver    of message
     * @return id massage
     */
    @Override
    public int sendMessage(String textMessage, String receiver) {

        Message message = new TextMessage();
        int messageId = generateId();
        message.setBody(textMessage);
        message.setInitiator(nick);
        message.setMessageId(messageId);
        message.setReceiver(receiver);
        writerMsg.writeMessageIn(message);
        return messageId;
    }

    /**
     * Receive message by id.
     *
     * @param idMessage id of message
     * @return message {@link Message}
     */
    public Message receiveMessageById(int idMessage) {
        while (true) {
            Optional<Message> result = readerMsg.readMessageById(idMessage);
            if (result.isPresent()) {
                return result.get();
            }
        }
    }

    /**
     * Receive message by name of receiver.
     *
     * @param name of receiver
     */
    @Override
    public void receiveMessageByName(String name) {
        Optional<Message> result = receiveMessageByNick(nick);
        result.ifPresent(message -> {
            int count = registerPlayer.iterCountMessage(nick);
            writerMsg.writeMessageOut(buildMessage(message, count));
        });
    }

    @Override
    public void run() {
        while (run) {
            Optional<Message> result = receiveMessageByNick(nick);
            result.ifPresent(message -> {
                int count = registerPlayer.iterCountMessage(nick);
                writerMsg.writeMessageOut(buildMessage(message, count));
                if (count == 10) {
                    stopApp();
                }
            });
        }
    }

    private int generateId() {
        return (int) (Math.random() * Integer.MAX_VALUE);
    }

    private Optional<Message> receiveMessageByNick(String nick) {
        return readerMsg.readMessageByName(nick);
    }

    private void stopApp() {
        run = false;
    }

    private Message buildMessage(Message message, int count) {
        String body = message.getBody();
        Message returnMessage = new TextMessage();
        body += " Counter: " + nick + "=" + String.valueOf(count);
        returnMessage.setBody(body);
        returnMessage.setInitiator(nick);
        returnMessage.setMessageId(message.getMessageId());
        returnMessage.setReceiver(message.getInitiator());
        return returnMessage;
    }

}

