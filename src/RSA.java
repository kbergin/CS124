import java.math.BigInteger;

/**
 * Created by kbergin on 4/3/16.
 */
public class RSA {
    static BigInteger RSA_N = new BigInteger("46947848749720430529628739081");
    static BigInteger RSA_E = new BigInteger("37267486263679235062064536973");

    public static void runRSA(String message){
        char[] charArray = message.toCharArray();
        byte[] byteArray= new byte[message.length()];
        for(int i =0; i<charArray.length; i++){
            int x = convertASCII(charArray[i]);
            byteArray[i] = (byte)x;
        }
        BigInteger answer = reIntify(byteArray);
        BigInteger eOfX;
        if(answer.compareTo(RSA_N)==-1){
            eOfX = answer.modPow(RSA_E, RSA_N);
            System.out.println(eOfX);
        }
    }

    private static int convertASCII(char c){
        return (int)c;
    }

    private static BigInteger reIntify(byte[] byteArray){
        try {
            return new BigInteger(byteArray);
        } catch (NumberFormatException ne) {
            System.out.println("Binary input is invalid");
            ne.printStackTrace();
        }
        return null;
    }
}
