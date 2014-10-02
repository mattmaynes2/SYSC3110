import java.util.Stack;
import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initializes all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Inventory inventory;
    private Stack<Room> history;
        
    /**
     * Create the game and initialize its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        history = new Stack<Room>();
        inventory = new Inventory();
    }
    
    public static void main(String [] args){
    	Game mainGame = new Game();
    	mainGame.play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
        
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // Initialize room exits
        // Outside
        outside.setExit("east", theater);
        outside.setExit("right", theater);
        outside.setExit("south", lab);
        outside.setExit("down", lab);
        outside.setExit("west", pub);
        outside.setExit("left", pub);
        outside.addItem(new Item("lamp", 2));
        
        // Theater
        theater.setExit("west", outside);
        theater.setExit("left", outside);
        theater.addItem(new Item("sack", 2));
        
        // Pub
        pub.setExit("east", outside);
        pub.setExit("right", outside);
        pub.addItem(new Item("beer", 1));
        
        // Lab
        lab.setExit("north", outside);
        lab.setExit("up", outside);
        lab.setExit("east", office);
        lab.setExit("right", office);
        lab.addItem(new Item("computer", 8));
        
        // Office
        office.setExit("west", lab);
        office.setExit("left", lab);
        office.addItem(new Item("stapler", 2));
                
        currentRoom = outside;  // start game outside
    }

    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    public void printLocationInfo(){
    	System.out.println("You are " + currentRoom.getDescription());
    	System.out.print("Exits: " + getExitString() + "\n");
    }
    
    public void printItemInfo(){
    	String result = "There is a ";
    	ArrayList<Item> items = currentRoom.getItems();
    	
    	
    	if(items.size() > 1){
	    	for(int i = 0; i < items.size() - 1; i++){
	    		result += items.get(i).getDescription() + ", a ";
	    	}
	    	result = result.substring(0, result.length() - 2) + "and a " + items.get(items.size() - 1);
    	}
    	else if (items.size() == 1){
    		result += items.get(0);
    	}
    	else{
    		return;
    	}
    	System.out.println(result + " here");   	
    	
    }
    
    public void printInventory(){
    	if(inventory.getItems().size() == 0)
    	{
    		System.out.println("You are currently not carrying anything");
    	}
    	else{
	    	System.out.println("You are currently carrying: ");
	    	for(Item i : inventory.getItems()){
	    		System.out.println("A " + i.getDescription());
	    	}
	    }
    }
    
    public String getExitString(){
    	String result = "";
    	for (String s :currentRoom.getExits().keySet()){
    		result += s + " ";
    	}
    	return result;
    }
    
    public void takeItem(Command command){
    	ArrayList<Item> items = currentRoom.getItems();
    	for (int i = 0; i < items.size(); i++){
    		Item item = items.get(i);
    		if(command.getSecondWord().equals(item.getDescription())){
    			if(inventory.addItem(item)){
    				currentRoom.removeItem(item);
    				System.out.println("Picked up item: " + item.getDescription());
    			}
    			else{
    				System.out.println("Your inventory is full!");    				
    			}
    			return;
    		}
    	}
    	System.out.println("That item is not here");
    }
    
    public void dropItem(Command command){
    	ArrayList<Item> items = inventory.getItems();
    	for (int i = 0; i < items.size(); i++){
    		Item item = items.get(i);
    		if(command.getSecondWord().equals(item.getDescription())){
    			inventory.removeItem(item);
    			currentRoom.addItem(item);
    			System.out.println("Dropped item: " + item.getDescription());
    			break;
    		}
    	}
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("look")){
        	printLocationInfo();
        	printItemInfo();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if(commandWord.equals("back")){
        	goBackRoom();
        } 
        else if(commandWord.equals("take")){
        	takeItem(command);
        }
        else if(commandWord.equals("drop")){
        	dropItem(command);
        }
        else if(commandWord.equals("inventory")){
        	printInventory();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    } 

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
    	String commands = "";
    	
    	for (String s : CommandWords.validCommands){
    		commands += s + ' ';
    	}
    	
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(commands);
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        
        if (currentRoom.getExits().containsKey(direction)){
        	history.push(currentRoom);
        	currentRoom = currentRoom.getExits().get(direction);
            printLocationInfo();
        }
        else{
            System.out.println("There is no door!");
        }
    }
    
    private void goBackRoom(){
    	if(history.isEmpty()){
    		System.out.println("There is nowhere to go!");
    	}
    	else{
    		currentRoom = history.pop();
    		printLocationInfo();
    	}
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
