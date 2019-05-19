package service;

import model.Message;
import model.TextMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WriterMsgImplTest {

    private List<Message> toQueue;
    private List<Message> outQueue;
    private WriterMsg writerMsg;

    @Before
    public void setUp() throws Exception {
        toQueue = new ArrayList<>();
        outQueue = new ArrayList<>();
        writerMsg = new WriterMsgImpl(toQueue, outQueue);
    }

    @After
    public void tearDown() throws Exception {
        toQueue = null;
        outQueue = null;
        writerMsg = null;
    }


    @Test
    public void writeMessageOut() throws Exception {

        Message actual = new TextMessage();
        actual.setBody("Test2");
        actual.setReceiver("Player2");
        actual.setMessageId(321);

        assertEquals(0, outQueue.size());
        writerMsg.writeMessageOut(actual);
        assertEquals(1, outQueue.size());

        assertEquals("Test2", outQueue.get(0).getBody());
        assertEquals("Player2", outQueue.get(0).getReceiver());
        assertEquals(321, outQueue.get(0).getMessageId());

    }

    @Test
    public void writeMessageIn() throws Exception {

        Message actual = new TextMessage();
        actual.setBody("Test2");
        actual.setReceiver("Player2");
        actual.setMessageId(321);

        assertEquals(0, toQueue.size());
        writerMsg.writeMessageIn(actual);
        assertEquals(1, toQueue.size());

        assertEquals("Test2", toQueue.get(0).getBody());
        assertEquals("Player2", toQueue.get(0).getReceiver());
        assertEquals(321, toQueue.get(0).getMessageId());

    }

}