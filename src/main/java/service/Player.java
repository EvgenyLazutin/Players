package service;

import model.Message;

import java.util.List;

/**
 * Interface for Player class
 * <p>
 *
 * @author Lazutin Evgeny
 */
public interface Player {

    int sendMessage(String textMessage, String receiver);
    Message receiveMessageById(int idMessage);
    void receiveMessageByName(String name);
}
