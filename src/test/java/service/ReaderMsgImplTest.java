package service;

import model.Message;
import model.TextMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReaderMsgImplTest {


    private List<Message> toQueue;
    private List<Message> outQueue;
    private ReaderMsg readerMsg;


    @Before
    public void setUp() throws Exception {
        toQueue = new ArrayList<>();
        outQueue = new ArrayList<>();
        readerMsg = new ReaderMsgImpl(toQueue, outQueue);
    }

    @After
    public void tearDown() throws Exception {
        toQueue = null;
        outQueue = null;
        readerMsg = null;
    }

    @Test
    public void readMessageById() throws Exception {

        Message actual = new TextMessage();
        actual.setBody("Test");
        actual.setReceiver("Player");
        actual.setMessageId(123);
        outQueue.add(actual);

        Message message = readerMsg.readMessageById(123).get();
        assertEquals("Test", message.getBody());
        assertEquals("Player", message.getReceiver());
        assertEquals(123, message.getMessageId());

    }

    @Test
    public void readMessageByName() throws Exception {

        Message actual = new TextMessage();
        actual.setBody("Test2");
        actual.setReceiver("Player2");
        actual.setMessageId(321);
        toQueue.add(actual);

        Message message = readerMsg.readMessageByName("Player2").get();
        assertEquals("Test2", message.getBody());
        assertEquals("Player2", message.getReceiver());
        assertEquals(321, message.getMessageId());
    }

}