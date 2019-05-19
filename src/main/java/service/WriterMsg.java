package service;

import model.Message;

/**
 * Interface for writer class
 * <p>
 *
 * @author Lazutin Evgeny
 */
public interface WriterMsg {

    void writeMessageOut(Message message);
    void writeMessageIn(Message message);
}
