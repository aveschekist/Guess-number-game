package org.nesteruk.menu;

import org.nesteruk.game.*;
import org.nesteruk.rating.Rating;
import org.nesteruk.rating.RatingImpl;

import java.util.LinkedList;
import java.util.Scanner;

public class MenuConsole implements Menu {

    private final int NEWGAME = 1;
    private final int RATING = 2;
    private final int NEXT = -1;
    private final int EXIT = 0;

    private void mainMenuDescr(){
        System.out.println("**************************************************");
        System.out.println("*             Guess Number.Menu                  *");
        System.out.println("**************************************************");
        System.out.println("*1. New game                                     *");
        System.out.println("*2. Rating                                       *");
        System.out.println("*0. Exit                                         *");
        System.out.println("**************************************************");
    }

    private void modeMenuDescr(){
        System.out.println("**************************************************");
        System.out.println("*             Choose game mode                   *");
        System.out.println("**************************************************");
        System.out.println(String.format("*%d. EASY                                         *", Mode.EASY));
        System.out.println(String.format("*%d. MID                                          *", Mode.MID));
        System.out.println(String.format("*%d. HARD                                         *", Mode.HARD));
        System.out.println("*0. Return to main menu                          *");
        System.out.println("**************************************************");
    }

    private void ratingMenuDescr(){
        System.out.println("**************************************************");
        System.out.println("*                 Scores                         *");
        System.out.println("**************************************************");
     }

    @Override
    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choose;
        mainMenuDescr();
        //try catch here
        do{
            try {
                System.out.print(">> ");
                choose = scanner.nextInt();
                switch (choose) {
                    case NEWGAME:
                        newGame();
                        mainMenuDescr();
                        break;
                    case RATING:
                        showRating();
                        mainMenuDescr();
                        break;
                    case EXIT:
                        //Nothing at all. Simply exit
                        break;
                    default:
                        System.err.println("Unknown menu item");
                }
            }catch (java.util.InputMismatchException ex){
                System.err.println("Error: Incorrect input symbol");
                choose = NEXT;
                scanner.nextLine();
            } catch (Exception ex){
                System.err.println("Error: Something wrong. Terminate app");
                choose = 0;
            }
        }while(choose != 0);

        System.out.println("Good Bye!");
    }

    private void showRating(){
        ratingMenuDescr();
        Rating rating = new RatingImpl();
        rating.read();

        try {
            LinkedList<Player> table = rating.getTable();
            for (int i = 0; i < table.size(); i++) {
                Player player = table.get(i);
                System.out.println(String.format("%d. %s - %d", i + 1, player.getName(), player.getSteps()));
            }
        } catch (NullPointerException ex){
            System.err.println("Error: Problem with read rating file!");
        }
    }

    private void newGame() throws Exception{

        Game game = null;
        GameFactory gameFactory = new GameFactoryConsole();

        Scanner scanner = new Scanner(System.in);
        int choose;
        char answer;

        modeMenuDescr();

        do{
            try {
                System.out.print(">> ");
                choose = scanner.nextInt();
                if(choose != EXIT) {
                    game = gameFactory.createGame(choose);
                    game.start();
                    int steps = game.getSteps();

                    System.out.println(String.format("Game over. Total steps = %d", steps));
                    System.out.print("Please, print your name: ");
                    String name = scanner.next();

                    Player player = new Player(name, steps);
                    Rating rating = new RatingImpl();

                    rating.read();
                    rating.insert(player);
                    rating.write();

                    showRating();

                    System.out.print("Start new game?[y]: ");
                    answer = scanner.next().charAt(0);
                    if (answer != 'y') {
                        //return to main menu
                        choose = EXIT;
                    } else {
                        modeMenuDescr();
                    }
                }

            }catch (java.util.InputMismatchException ex){
                System.err.println("Error: Incorrect input symbol");
                choose = NEXT;
                scanner.nextLine();
            }catch(NullPointerException ex){
                System.err.println("Unknown menu item");
                choose = NEXT;
            }catch (Exception ex){
                choose = EXIT;
                throw new Exception();
            }

        }while(choose != EXIT);

    }


}
