package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
//import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }

    public static void main(String[] args) throws java.io.IOException {
        TransactionController tController = new TransactionController(new MessageController(), new IdController());


//        YouAreEll urll = new YouAreEll(new MessageController(), new IdController());

        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> cmdList = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                cmdList.add(commands[i]);

            }
            System.out.print(cmdList); //***check to see if list was added correctly***
            history.addAll(cmdList);
//            try {
            //display history of shell with index
            if (cmdList.get(cmdList.size() - 1).equals("history")) {
                for (String s : history)
                    System.out.println((index++) + " " + s);
                continue;
            }

            // Specific Commands.

            // ids
            //get all ids
            if (cmdList.contains("ids") && cmdList.size() == 1) {
                List<Id> results = tController.getIds();
                SimpleShell.prettyPrint(results.stream().map(i -> i.toString() + "\n").collect(Collectors.joining()));
                continue;
            }
            //post id
            if (cmdList.get(0).equals("ids") && cmdList.size() == 3) {
                String results = tController.postId(cmdList.get(1), cmdList.get(2));
                SimpleShell.prettyPrint(results);
                continue;
            }
            //put/update name linked to github id
            if (cmdList.get(0).equals("ids") && cmdList.size() == 2) {
                String results = tController.putId(cmdList.get(1));
                SimpleShell.prettyPrint(results);
                continue;
            }

            // messages
            //get all messages
            if (cmdList.contains("messages") && cmdList.size() == 1) {
                List<Message> results = tController.getMessages();
                SimpleShell.prettyPrint(results.stream().map(i -> i.toString() + "\n").collect(Collectors.joining()));
                continue;
            }
            //last 20 messages sent to your github id
            if (cmdList.contains("messages") && cmdList.size() == 2) {
                List<Message> results = tController.getMessageForId(cmdList.get(1));
                SimpleShell.prettyPrint(results.stream().map(i -> i.toString() + "\n").collect(Collectors.joining()));
                continue;
            }

            //seq
            if (cmdList.contains("messages") && cmdList.size() == 3) {
                Message results = tController.messageForSeq(cmdList.get(1), cmdList.get(2));
                SimpleShell.prettyPrint(results.toString());
                continue;
            }

            //from friend to me
            if (cmdList.contains("messages") && cmdList.size() == 4) {
                List<Message> results = tController.messagesFromYouToMe(cmdList.get(1), cmdList.get(3));
                SimpleShell.prettyPrint(results.stream().map(i -> i.toString() + "\n").collect(Collectors.joining()));
                continue;
            }

            //send stuff
            if (cmdList.get(0).equals("send") && cmdList.size() >= 3) {
                if (cmdList.get(cmdList.size() - 2).equals("to")
                        && !cmdList.get(cmdList.size() - 1).contains("'")) {
                    //this will be send to specific person
                    String message = String.join(" ", cmdList.subList(2, (cmdList.size() - 2)));
                    Message results = tController.postMessage(cmdList.get(1), cmdList.get(cmdList.size() - 1), message);
                    SimpleShell.prettyPrint(results.toString());
                } else {
                    //this send to global timeline
                    String message = String.join(" ", cmdList.subList(2, (cmdList.size())));
                    Message results = tController.postMessage(cmdList.get(1), message);
                    SimpleShell.prettyPrint(results.toString());
                }
            }

            //!! command returns the last command in history
            if (cmdList.get(cmdList.size() - 1).equals("!!")) {
                try {
                    pb.command(history.get(history.size() - 2));
                } catch (ArrayIndexOutOfBoundsException aIOOBE) {
                    System.out.println("History is empty, do something else!");
                    continue;
                }

            }//!<integer value i> command
            else if (cmdList.get(cmdList.size() - 1).charAt(0) == '!') {
                int b = Character.getNumericValue(cmdList.get(cmdList.size() - 1).charAt(1));
                if (b <= history.size())//check if integer entered isn't bigger than history size
                    pb.command(history.get(b));
            } else {
                pb.command(cmdList);
            }

//                 // wait, wait, what curiousness is this?
//                 Process process = pb.start();
//
//                 //obtain the input stream
//                 InputStream is = process.getInputStream();
//                 InputStreamReader isr = new InputStreamReader(is);
//                 BufferedReader br = new BufferedReader(isr);
//
//                 //read output of the process
//                 String line;
//                 while ((line = br.readLine()) != null)
//                     System.out.println(line);
//                 br.close();


//            }

            //catch ioexception, output appropriate message, resume waiting for input
//            catch (IOException e) {
//                System.out.println("Input Error, Please try again!");
//            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}