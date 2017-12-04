/*package TableElements;
import java.io.IOException;

public class tester {

    public static void main(String[] args) {
        Deck testDeck = null;
        try {
            testDeck = new Deck();
        }catch(IOException ioe){
            System.out.println(ioe);
        }

        TableStack testTableStack1 = new TableStack();
        TableStack testTableStack2 = new TableStack();
        TableStack testTableStack3 = new TableStack();
        TableStack testTableStack4 = new TableStack();
        TableStack testTableStack5 = new TableStack();
        TableStack testTableStack6 = new TableStack();
        TableStack testTableStack7 = new TableStack();
        TableStack testTableStack8 = new TableStack();
        int e = 0;
        for(int i = 0; i < testDeck.getSize(); i++){
            switch(e){
                case 0:
                    testTableStack1.push(testDeck.dealCard());
                    break;
                case 1:
                    testTableStack2.push(testDeck.dealCard());
                    break;
                case 2:
                    testTableStack3.push(testDeck.dealCard());
                    break;
                case 3:
                    testTableStack4.push(testDeck.dealCard());
                    break;
                case 4:
                    testTableStack5.push(testDeck.dealCard());
                    break;
                case 5:
                    testTableStack6.push(testDeck.dealCard());
                    break;
                case 6:
                    testTableStack7.push(testDeck.dealCard());
                    break;
                case 7:
                    testTableStack8.push(testDeck.dealCard());
                    break;
            }
            e++;
            if(e == 8){ e = 0;}
        }
        String test = "";
        for(int i = 0; i < 19; i++){
            test += testTableStack1.peek().toString() + testTableStack1.peek().toString() + testTableStack1.peek().toString() + testTableStack1.peek().toString() + testTableStack1.peek().toString() + testTableStack1.peek().toString() + testTableStack1.peek().toString() + testTableStack1.peek().toString() + testTableStack1.peek().toString();
        }
        System.our.println(string);


    }

}
*/