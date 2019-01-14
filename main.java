import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;

public class main{
  public static void main(String[] args){
    //int inputNum = -1;
    //Status status_type = Status.wait;

    String mainLabel;
    mainLabel = "ready...";
    System.out.println(mainLabel);
    System.out.println("Enter -> 100Calcs Game Start!!");

    String str = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try{
      str = br.readLine();
    }catch(IOException e){
      System.out.println("input error:" + e.getMessage());
    }

    Formula fml = new Formula();
    String operation = null;
    int answer = -1;
    int leftQ = -1;
    int rightQ = -1;

    operation = fml.getOperation();
    answer = fml.getAnswer("+");

    //System.out.println("operation -> " + operation);
    System.out.println("answer -> " + answer);

    //}else{
    //System.out.println("waiting... please put s key!!");
    //}
  }

  public static class Formula{

    private static final int OPE_ADD_CODE = 0;
    private static final int OPE_SUB_CODE = 1;
    private static final int OPE_MUL_CODE = 2;
    private static final int OPE_DIV_CODE = 3;
    private static final String OPE_ADD_NAME = "+";
    private static final String OPE_SUB_NAME = "-";
    private static final String OPE_MUL_NAME = "x";
    private static final String OPE_DIV_NAME = "/";

    /*
    * select Operation
    */
    public String getOperation(){
      final int OPE_COUNT = 4;
      int opeCode = -1;
      String opeName = null;
      Random rnd =   new Random();
      opeCode = rnd.nextInt(OPE_COUNT);

      switch (opeCode){
        case OPE_ADD_CODE:
          return OPE_ADD_NAME;
        case OPE_SUB_CODE:
          return OPE_SUB_NAME;
        case OPE_MUL_CODE:
          return OPE_MUL_NAME;
        case OPE_DIV_CODE:
          return OPE_DIV_NAME;
        default:
          System.out.println("error(getOperationName)");
          return opeName = null;
      }
    }

    /*
    * get Answer
    */
    public int getAnswer(String opeName){
      int min = -1;
      int max = -1;
      switch (opeName){
        case OPE_ADD_NAME:
          min = 2;
          max = 99;
          return getRandom(min, max);

        case OPE_SUB_NAME:
          min = 2;
          max = 98;
          return getRandom(min, max);

        case OPE_MUL_NAME:

          int multiAnswers[];
          int randomIndex;
          Random rnd = new Random();

          multiAnswers = getMultiAnswers();
          randomIndex = rnd.nextInt(multiAnswers.length);

          return multiAnswers[randomIndex];

        case OPE_DIV_NAME:
          min = 2;
          max = 49;
          return getRandom(min, max);
        
        default:
          System.out.println("error(getOperationName)");
          return -1;

        }
    }

    private int getRandom(int min,int max){
      Random rnd = new Random();
      return rnd.nextInt(max - min + 1) + min;
    }


    private boolean[] getPrimeNumbers(int max){
      boolean sieve[] = new boolean[max + 1]; //(0~99)

      for(int i = 0; i <= max; i++){
        sieve[i] = true; //init
      }

      //sieve[x] <- True (primeNumber)
      sieve[0] = false;
      sieve[1] = false;

      // comment out
      for (int i = 2; i <= Math.sqrt(max); i++){
        if(sieve[i] == true){
          for (int j = i * 2; j <= max; j = j + i){
            sieve[j] = false;
          }
        }
      }
      return sieve;
    }


    private int[] getMultiAnswers(){
      final int MAX = 99; //(0~99)

      boolean primeNumbers[];
      primeNumbers = getPrimeNumbers(MAX);

      int multiAnswerCount = 0;
      for(int i = 2; i <= MAX; i++){
        if(primeNumbers[i] == false){
          multiAnswerCount = multiAnswerCount + 1;
        }
      }

      int numCount = 0;
      int multiAnswers[] = new int[multiAnswerCount];
      for(int i = 2; i <= MAX; i++){
        if (primeNumbers[i] == false){
          multiAnswers[numCount] = i;
          numCount = numCount + 1;
        }
      }

      return multiAnswers;

      // test code 1
      /*
      int cnt = 0;
      for (int s: multiAnswers){
        System.out.println("sieve[" + cnt + "] -> " + s);
        cnt++;
      }
      */
    }
  }
}
