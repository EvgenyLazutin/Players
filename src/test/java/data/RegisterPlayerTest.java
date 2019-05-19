package data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class RegisterPlayerTest {


    private RegisterPlayer registerPlayer;

    @Before
    public void init() { registerPlayer = new RegisterPlayer();}

    @After
    public void tearDown() { registerPlayer = null; }

    @Test
    public void iterCountMessage() throws Exception {

        int result=0;
        for (int i = 0; i <10 ; i++) {
            result= registerPlayer.iterCountMessage("test");
        }
        assertEquals(10, result);
        assertEquals( 1,registerPlayer.iterCountMessage("test2") );

    }

}