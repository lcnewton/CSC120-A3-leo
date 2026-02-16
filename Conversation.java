// You should **not** update any call signatures in this file
// only modify the body of each function

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Conversation implements ConversationRequirements {

  // Attributes 
  private Scanner input;
  private String[] transcript;
  private int transcriptIndex;
  /**
   * Constructor 
   */
  Conversation() {
    input = new Scanner(System.in);
    transcriptIndex = 0;
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
      System.out.println("How many rounds? ");
      int rounds = input.nextInt();
      input.nextLine();

      transcript = new String[2*rounds + 2];

      String greeting = "Hi there! What's up?";
      System.out.println("\n" + greeting);
      transcript[transcriptIndex++] = greeting;

      for (int i = 0; i < rounds; i++) {
        String userInput = input.nextLine();
        transcript[transcriptIndex++] = userInput;

        String response = respond(userInput);
        System.out.println(response);
        transcript[transcriptIndex++] = response;
      }
    String goodbye = "Bye!";
    System.out.println(goodbye);
    transcript[transcriptIndex++] = goodbye;

  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nTRANSCRIPT");
    for (int i = 0; i < transcriptIndex; i++) {
      System.out.println(transcript[i]);
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String[] words = inputString.split(" ");
    boolean mirrored = false;
    for (int i = 0; i < words.length; i++) {
      if (words[i].equals("I")) {
        words[i] = "you";
        mirrored = true;

      }
      else if (words[i].equalsIgnoreCase("my")) {
        words[i] = "your";
        mirrored = true;
      }
      else if (words[i].equalsIgnoreCase("am")) {
        words[i] = "are";
        mirrored = true;
      }
      else if (words[i].equalsIgnoreCase("you")) {
        words[i] = "I";
        mirrored = true;
      }
      else if (words[i].equalsIgnoreCase("your")) {
        words[i] = "my";
        mirrored = true;
      }
      else if (words[i].equalsIgnoreCase("are")) {
        words[i] = "am";
        mirrored = true;
      }
    }
    if (mirrored) {
      return String.join(" ", words) + "?";
    }
    String[] canned = {
      "Interesting!",
      "Mmm-hmm.",
      "Huh?",
      "Ok."
    };
    Random rand = new Random();
    return canned[rand.nextInt(canned.length)];
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
