// You should **not** update any call signatures in this file
// only modify the body of each function

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Conversation implements ConversationRequirements {

  // Attributes 
  private Scanner input;
  private ArrayList<String> transcript;
  private static String[] canned = {
      "Interesting!",
      "Mmm-hmm.",
      "Huh?",
      "Ok."
    };
  /**
   * Constructor 
   */
  Conversation() {
    input = new Scanner(System.in);
    transcript = new ArrayList<String>();
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
      System.out.println("How many rounds? ");
      int rounds = input.nextInt();
      input.nextLine();

      // Greeting begins the chat
      String greeting = "Hi there! What's up?";
      System.out.println("\n" + greeting);
      transcript.add(greeting);

      // After the user defines the rounds, the bot responds for that
      // number of times 
      for (int i = 0; i < rounds; i++) {
        String userInput = input.nextLine();
        transcript.add(userInput);

        String response = respond(userInput);
        System.out.println(response);
        transcript.add(response);
      }
    // Goodbye ends the chat, followed by transcript
    String goodbye = "Bye!";
    System.out.println(goodbye);
    transcript.add(goodbye);

  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nTRANSCRIPT");
    for (int i = 0; i < transcript.size(); i++) {
      System.out.println(transcript.get(i));
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String[] words = inputString.split(" ");
    // Boolean "mirrored" variable so that program can add a question mark
    // onto mirrored responses
    
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
