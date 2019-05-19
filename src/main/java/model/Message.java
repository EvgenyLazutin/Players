package model;

import java.util.List;

/**
 * Interface for message class
 * <p>
 *
 * @author Lazutin Evgeny
 */
public interface Message {

    String getReceiver();

    void setReceiver(String receiver);

    String getInitiator();

    void setInitiator(String initiator);

    int getQuantityMessage();

    void setQuantityMessage(int quantityMessage);

    int getMessageId();

    void setMessageId(int messageId);

    String getBody();

    void setBody(String body);
}
