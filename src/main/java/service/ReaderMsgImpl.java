package service;

import model.Message;

import java.util.List;
import java.util.Optional;

/**
 * Implementation for interface {@link ReaderMsg} for realisation reading message.
 * <p>
 *
 * @author Lazutin Evgeny
 */
public class ReaderMsgImpl implements ReaderMsg{

    private List<Message> toQueue;
    private List<Message> outQueue;

    public ReaderMsgImpl(List<Message> toQueue, List<Message> outQueue) {
        this.outQueue = outQueue;
        this.toQueue = toQueue;
    }

    /**
     * Read message by id.
     *
     * @param messageId id of message
     * @return message {@link Message}
     */
    @Override
    public Optional<Message> readMessageById(int messageId) {

        Optional<Message> result = outQueue.stream().filter(queue -> queue.getMessageId()==messageId).findAny();
        result.ifPresent(message -> outQueue.remove(message));
        return result;
    }

    /**
     * Receive message by name of receiver.
     *
     * @param name of receiver
     * @return message {@link Message}
     */
    @Override
    public Optional<Message> readMessageByName(String name) {

        Optional<Message> result = toQueue.stream().filter(queue -> queue.getReceiver().equals(name)).findAny();
        result.ifPresent(message ->{
            System.out.printf("Read Message: %s%n", message.getBody());
            toQueue.remove(message);});
        return result;
    }
}
