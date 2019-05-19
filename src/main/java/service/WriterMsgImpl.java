package service;

import model.Message;

import java.util.List;

/**
 * Implementation for interface {@link WriterMsg} for realisation play.
 * <p>
 *
 * @author Lazutin Evgeny
 */
public class WriterMsgImpl implements WriterMsg {

    private List<Message> outQueue;
    private List<Message> inQueue;

    public WriterMsgImpl(List<Message> inQueue, List<Message> outQueue) {
        this.outQueue = outQueue;
        this.inQueue = inQueue;
    }

    /**
     * Write message in out queue.
     *
     * @param message for write
     */
    @Override
    public void writeMessageOut(Message message) {
        System.out.printf("Write Message: %s%n", message.getBody());
        outQueue.add(message);
    }

    /**
     * Write message in out queue.
     *
     * @param message for write
     */
    @Override
    public void writeMessageIn(Message message) {
        System.out.printf("Send Message: %s%n", message.getBody());
        inQueue.add(message);
    }
}
