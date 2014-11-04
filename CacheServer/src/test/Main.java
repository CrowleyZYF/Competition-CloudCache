package test;

import com.jfinal.core.JFinal;

/**
 * Created by hello on 14-11-4.
 */
public class Main {

    public static void main(String[] args) {
        JFinal.start(".", 8080, "/", 5);
    }

}
