package service;

import model.Message;

import java.util.Optional;

/**
 * Interface for Reader class
 * <p>
 *
 * @author Lazutin Evgeny
 */
public interface ReaderMsg {

    Optional<Message> readMessageById(int messageId);
    Optional<Message> readMessageByName(String name);

}
