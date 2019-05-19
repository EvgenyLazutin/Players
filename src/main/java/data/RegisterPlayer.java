package data;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to simulate the DAO layer.
 * In a real application, such data is best stored in a database.
 * <p>
 *
 * @author Lazutin Evgeny
 */
public class RegisterPlayer {

    private Map<String, Integer> register;


    public RegisterPlayer() {
        this.register = new HashMap<>();
    }

    /**
     * Calculate the quantity and cost of packaging.
     *
     * @param name of player for calculate.
     *
     * @return quantity message of player
     */
    public int iterCountMessage(String name){

        int result = register.getOrDefault(name, 0);
        if(result==0){
            register.put(name, 1);
            return 1;
        }
        register.put(name, ++result);
        return result;
    }
}
