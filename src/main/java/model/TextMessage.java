package model;


/**
 * Implementation for interface {@link Message} for realisation messaging.
 * <p>
 *
 * @author Lazutin Evgeny
 */
public class TextMessage implements Message {

    private String initiator;
    private int quantityMessage;
    private int messageId;
    private String body;
    private String receiver;

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String getInitiator() {
        return initiator;
    }

    @Override
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    @Override
    public int getQuantityMessage() {
        return quantityMessage;
    }

    @Override
    public void setQuantityMessage(int quantityMessage) {
        this.quantityMessage = quantityMessage;
    }

    @Override
    public int getMessageId() {
        return messageId;
    }

    @Override
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }
}
