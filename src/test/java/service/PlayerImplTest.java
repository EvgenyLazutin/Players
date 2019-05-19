package service;

import data.RegisterPlayer;
import model.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerImplTest {

    private List<Message> toQueue;
    private List<Message> outQueue;
    private ReaderMsg readerMsg;
    private WriterMsg writerMsg;
    private RegisterPlayer registerPlayer;
    private Player player;

    @Before
    public void setUp() throws Exception {
        toQueue = new ArrayList<>();
        outQueue = new ArrayList<>();
        readerMsg = new ReaderMsgImpl(toQueue, outQueue);
        writerMsg = new WriterMsgImpl(toQueue, outQueue);
        registerPlayer = new RegisterPlayer();
        player = new PlayerImpl("player", readerMsg, writerMsg, registerPlayer);
    }

    @After
    public void tearDown() throws Exception {
        toQueue = null;
        outQueue = null;
        readerMsg = null;
        writerMsg = null;
        registerPlayer = null;
        player = null;
    }

    @Test
    public void sendMessage() throws Exception {
        assertEquals(0, toQueue.size());
        player.sendMessage("Test message", "OtherPlayer");
        assertEquals(1, toQueue.size());
        assertEquals("Test message", toQueue.get(0).getBody());
        assertEquals("OtherPlayer", toQueue.get(0).getReceiver());
    }

    @Test
    public void receiveMessageByIdAndByName() throws Exception {
        assertEquals(0, toQueue.size());
        int id = player.sendMessage("Test message", "player");
        assertEquals(1, toQueue.size());
        player.receiveMessageByName("OtherPlayer");
        assertEquals(0, toQueue.size());
        assertEquals(1, outQueue.size());
        Message actual = player.receiveMessageById(id);
        assertEquals(0, outQueue.size());
        assertEquals("Test message Counter: player=1", actual.getBody());
        assertEquals("player", actual.getReceiver());
    }

}