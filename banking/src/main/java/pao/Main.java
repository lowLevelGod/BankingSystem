package pao;

import pao.Utils.Demo;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        Demo demo = new Demo();
        // demo customer service
        demo.customer();

        // demo account service
        demo.account();
        
        // demo transaction service
        demo.transaction();

        // demo card service
        demo.card();
    }
}
