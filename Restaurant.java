import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Restaurant {

    static int [] menuItemNumber = {110, 120, 130, 140, 210, 220, 230, 240, 310, 320, 330, 340, 410, 420, 430, 450, 460, 470, 480, 490, 510, 520, 530, 540, 550, 551, 560, 570, 600, 610, 620, 710, 711, 720, 721, 730, 740, 810, 811, 820, 821, 830, 831, 840, 910, 911, 920, 930, 940, 950, 951, 952, 953, 960};
    static String [] menuItemName = {"Sweet corn", "Sweet corn with chicken", "Cream of Tomato/Mushroom", "Veg soup", "Jacket Potato with Baked Beans", "Jacket Potato with Cheese", "Jacket Potato with Tuna", "Jacket Potato with Coleslaw", "Cheese", "Ham & Cheese", "Bacon Egg Cheese", "Sausage Egg cheese", "Ham&Brie", "Grilled Chicken Mayo", "Prawn salad", "Egg May", "Tuna sweetcorn", "Chicken Burger", "Hamburger", "Veg Burger", "Steak & Ale Pie", "Fish & chips", "Breaded Scampi w/ fries", "Chicken pasta", "Lasagne", "Lasagne veg", "Spaghetti Bolognese", "Hotdog w/ fries", "Chicken nuggets", "Macaroni Cheese", "Pigs in a blanket", "French fries S", "French fries L",  "Onion rings S", "Onion rings L", "Garlic bread", "Nachos cheese/guac/salsa", "Chocolate cookie", "Brownie", "Vanilla Cheesecake", "Lemon Cheesecake", "Apple Pie", "Cherry Pie", "Pancakes (2)", "Tea cup", "Tea pot", "Coffee", "Cappuccino", "Hot Chocolate", "Pop drink Fanta", "Pop drink Pepsi", "Pop drink Cola", "Pop drink Lemonade", "Sparkling Water"};
    static double [] menuItemPrice = {1.99, 2.35, 2.10, 2.25, 3.20, 2.50, 3.75, 2.75, 2.25, 3.20, 3.75, 3.55, 3.75, 3.25, 3.95, 2.95, 3.29, 5.65, 5.75, 4.95, 6.95, 7.95, 6.95, 6.79, 6.95, 5.25, 5.99, 3.50, 2.55, 2.50, 2.95, 2.50, 3.50, 2.99, 3.99, 2.99, 2.25, 2.10, 2.55, 3.99, 3.99, 2.25, 2.25, 2.99, 2.10, 2.99, 2.50, 2.75, 2.95, 2.35, 2.35, 2.35, 2.35, 1.75};
    static String [] staffTableAllocation = {"Kiran", "Sam", "Jill", "Jill", "Sam", "Jill", "Sam", "Sam", "Jill"};
    // The orderByTableNumber double table stores for each table (9 tables) the item's number and quantity ordered, assuming a customer can only order 10 different items as a maximum (20 entries)
    static int [][] orderByTableNumber = new int[9][20]; 
    // The commentsByTableNumber double table stores the comments for each table items, assuming customer can only order 10 different items as a maximum 
    static String [][] commentsByTableNumber = new String[9][10]; 
    // The orderNumberByTable stores the order numbers at the indexed table number, assuming only one order number can be stored at a time at a table, when a table number is empty (0) it means there is no active order at that table
    static int [] orderNumberByTable = new int[9]; 
    static int orderNumber = 101;  // Start with the order number #101
    static int optionSelected; 
    static Scanner input = new Scanner(System.in);

 
/**
 * The main method serves as the entry point for the restaurant order management system.
 * It displays a looped menu with options to place an order, display an invoice, or exit the program.
 * The menu repeats itself until the user chooses to exit.
 */

    public static void main(String[] args) {
        String menuOption1 = "Place order";
        String menuOption2 = "Display invoice";
        String menuOption3 = "Exit";
        do {
            System.out.println("Menu");
            System.out.println("1. " + menuOption1);
            System.out.println("2. " + menuOption2);
            System.out.println("3. " + menuOption3);
            optionSelected = validateRangeNumbers(1, 3, "Enter your option (1-3): ", "Try again. An integer input between 1 and 3 is required.");
            if ( optionSelected == 3) {
                System.out.print("Thank you for using the program. Have a lovely day!");

            } else {
                switch(optionSelected) {
                case 1:
                System.out.println("You\'ve chosen " + menuOption1 + ".");
                printMenu();
                placeOrder();
                break;
                case 2:
                System.out.println("You\'ve chosen " + menuOption2 + ".");
                displayInvoice();
                break;
                }      
            }     
        } while (optionSelected != 3);
    }

/**
 * The printSubMenu method prints the submenu items within a specified range of menu item numbers.
 * This method helps categorize menu items for better readability and organization.
 * The parameter x represents the starting menu item number for the submenu.
 * The parameter y represents the ending menu item number for the submenu.
 */

    public static void printSubMenu(int x, int y){
        for (int i = 0; i < menuItemNumber.length; i++) {
            if (menuItemNumber[i] > x && menuItemNumber[i] < y) {
                System.out.println(menuItemNumber[i] + " " + menuItemName[i] + " £" + menuItemPrice[i]);
            }
        }
    }

/**
 * The printMenu method prints the entire menu, organized into categories.
 * Uses the printSubMenu method to display each category's items.
 */

    public static void printMenu() {  
        System.out.println("Menu");
        System.out.println("");
        System.out.println("Soup of the day");
        printSubMenu(100, 200);
        System.out.println("");
        System.out.println("Baked Potato w/ filling");
        printSubMenu(200, 300);
        System.out.println("");
        System.out.println("Toasties");
        printSubMenu(300, 400);
        System.out.println("");
        System.out.println("Baguette");
        printSubMenu(440, 460);
        System.out.println("");
        System.out.println("Burger");
        printSubMenu(470, 500);
        System.out.println("");
        System.out.println("Main Courses");
        printSubMenu(500, 600);
        System.out.println("");
        System.out.println("Kids Menu");
        printSubMenu(600, 700);
        System.out.println("");
        System.out.println("Sides");
        printSubMenu(700, 800);
        System.out.println("");
        System.out.println("Desserts");
        printSubMenu(800, 900);
        System.out.println("");
        System.out.println("Drinks");
        printSubMenu(900, 990);
        System.out.println("");
    }

/**
 * The getPriceByItemNumber method retrieves the price of a menu item based on its item number.
 * The parameter itemNumber represents the unique identifier for the menu item.
 * Returns the price associated with the given item number.
 */    

    public static double getPriceByItemNumber(int itemNumber) {  
        double itemPrice = 0;
        for (int i =0; i < menuItemNumber.length; i++){
            if (menuItemNumber[i] == itemNumber){
                itemPrice = menuItemPrice[i];
            }
        }
        return itemPrice;
    }

/**
 * The getNameByItemNumber method retrieves the name of a menu item based on its item number.
 * The parameter itemNumber represents the unique identifier for the menu item.
 * Returns the name of the menu item.
 */    

    public static String getNameByItemNumber(int itemNumber) {
        String itemName = "";
        for (int i =0; i < menuItemNumber.length; i++){
            if (menuItemNumber[i] == itemNumber){
                itemName += menuItemName[i];
            }
        }
        return itemName;
    }

/**
 * The isItemNumberValid method checks if a given item number corresponds to a valid menu item.
 * The parameter itemNumber represents the menu item number to validate.
 * Returns true if the item number is valid, false otherwise.
 */

    public static  boolean isItemNumberValid(int itemNumber) {
        boolean isValid = false;
        for (int i = 0; i < menuItemNumber.length; i++){
            if (menuItemNumber[i] == itemNumber) isValid = true;
        }
        return isValid;
    }

/**
 * The emptyTableByOrderNumber method clears the order details associated with a specific table.
 * This method is typically called after an order is paid for and the table is ready to be reset for new customers.
 * The parameter orderNumber represents the order number associated with the table to be cleared.
 */

    public static void emptyTableByOrderNumber(int orderNumber) {
        for (int i =0; i < orderByTableNumber[orderNumber].length; i++){
            if (orderByTableNumber[orderNumber][i] > 0){
                orderByTableNumber[orderNumber][i] = 0;
            }
        }
        orderNumberByTable[orderNumber] = 0;
    }

/**
 * The calculateVat method calculates the Value Added Tax (VAT) for a given total amount.
 * The parameter total represents the total amount before VAT is applied.
 * The parameter vatRate represents the VAT rate to apply to the total amount.
 * Returns the calculated VAT amount.
 */    

    public static  double calculateVat(double total, double vatRate) {
        double vat = total * vatRate/(1 + vatRate);
        return vat;
    }

/**
 * The validateRangeNumbers method prompts the user for a number within a specific range and validates the input.
 * Re-prompts the user in case of invalid input until a valid number is provided.
 * The parameter min represents the minimum acceptable value.
 * The parameter max represents the maximum acceptable value.
 * The parameter question represents the prompt message for the user.
 * The parameter errorMessage represents the error message displayed for invalid inputs.
 * This method will be used for validating: table number, item number, and quantity of the ordered items
 * Returns a valid number entered by the user within the specified range.
 */    

    public static int validateRangeNumbers(int min, int max, String question, String errorMessage){        
        boolean continueInput = true;  
        int number = 0;
        do {           
            try {
                System.out.println(question);
                number = input.nextInt();
                if (number < min || number > max) {
                    System.out.println(errorMessage);
                    input.nextLine();
                } else continueInput = false;
            }
            catch (InputMismatchException ex){
                System.out.println(errorMessage);
                input.nextLine();
            }
        } while (continueInput);
        return number;
    }

/**
 * The yesOrNoInput method asks a yes-or-no question and validates the user's response.
 * Accepts 'y' or 'n' (case insensitive) as valid inputs and re-prompts the user on invalid input.
 * The parameter question represents the yes-or-no question to ask the user.
 * The parameter errorMessage represents the message to display for invalid inputs.
 * Returns the user's response as a "y" or "n".
 */

    public static String yesOrNoInput(String question, String errorMessage) { 
        boolean continueInput = true;  
        String answer = "";
        do {  
            try {
                System.out.println(question);
                answer = input.next();
                input.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    continueInput = false;
                } else if ((answer.equalsIgnoreCase("n"))) {
                    continueInput = false;
                } else {
                    System.out.println(errorMessage);
                }
            }
            catch (InputMismatchException ex){
                System.out.println(errorMessage);
                input.nextLine();
            }
        } while (continueInput);
        return answer;      
    }

/**
 * The method placeOrder handles the process of placing an order for a table, including taking item numbers,
 * quantities, and optional comments for each item. Calculates and displays the total cost
 * as items are added and asks if the user wants to add more items until the order is complete.
 */

    public static void placeOrder() {

        boolean addAnotherItem = true;
        double totalCost = 0.00;
        String selectionsNameAndQuantity = ""; // Accumulates names and quantities of selected items for display
        // Prompt for table number with validation
        int tableNumber = validateRangeNumbers(0, 8, "Please enter the table number(choose a number from 0 to 8, table 0 is allocated for take aways):", "Try again. An integer input between 0 and 8 is required."); // Validate and obtain table number from user 
        // Assign a new order number to the table if it does not already have an active order
        if (orderNumberByTable[tableNumber] == 0) {
            orderNumberByTable[tableNumber] = orderNumber; // Assign and increment the global order number 
            orderNumber++;
        } else orderNumberByTable[tableNumber] = orderNumberByTable[tableNumber]; 
        int selection = 0; 
        String comment = ""; 
        do {  
            do {
                selection = validateRangeNumbers(100, 1000, "Please enter the item number from the menu that you would like to order:",  "The number is not valid. Please try again.");
            } while (!isItemNumberValid(selection));  // Validate and obtain item number from user
            // Optionally collect comments for the item
            comment = "";
            String answer = yesOrNoInput("Would you like to add any comments for this choice? y/n", "Try again. Please enter only one character 'y' or 'n', depending on your choice.");
            if (answer.equalsIgnoreCase("y")) {
                            System.out.println("Please enter your comment:");
                            comment = input.nextLine();  
            }
            double selectionPrice = 0.00;
            int firstFreePosition = 0;   
            // Find the first free position in the order array for the current tableNumber, where we will add the new selection  
            while (orderByTableNumber[tableNumber][firstFreePosition] > 0){
                firstFreePosition += 2; // Skip to the next available slot, considering item and quantity slots
            }
            for (int i = 0; i < menuItemNumber.length; i++) {
                if (menuItemNumber[i] == selection) {
                    selectionsNameAndQuantity += menuItemName[i] + " ";
                    selectionPrice += menuItemPrice[i];   
                    orderByTableNumber[tableNumber][firstFreePosition] = selection;    // Add the item number in orderByTableNumber at the first free position 
                }
            }
            commentsByTableNumber[tableNumber][firstFreePosition] = comment;
            // Validate and obtain quantity for the item
            int quantity = validateRangeNumbers(1, 10, "Please enter the quantity (1 to maximum 10)", "Try again. An integer input between 1 and 10 is required.");
            selectionsNameAndQuantity += " x " + quantity + ", ";
            totalCost += selectionPrice * quantity;
            firstFreePosition++;  // Go to the next index in the orderByTableNumber at the current tableNumber
            orderByTableNumber[tableNumber][firstFreePosition] = quantity; // Add the quantity after the ordered item number in the table
            System.out.println("You have chosen " + selectionsNameAndQuantity + "total to pay so far: £" + String.format("%.2f", totalCost) + ". ");
            // Ask if the user wants to add more items
            answer = yesOrNoInput("Would you like to add more items to your order? y/n", "Try again. Please enter only one character 'y' or 'n', depending on your choice.");  
            if ((answer.equalsIgnoreCase("n"))) {
                        addAnotherItem = false;
            }
        } while (addAnotherItem == true);
        // Display invoice for takeaway orders immediately after completion
        if (tableNumber == 0) {
            displayInvoice();
        }
    }

/**
 * The method displayInvoice generates and displays an invoice for a specific table number.
 * The invoice includes order details, such as item names, quantities, prices, and the total cost,
 * along with calculated VAT and the net amount due. It also handles payment confirmation.
 */

    public static void displayInvoice() {

        // Prompt for table number with validation
        int tableNumberInput = validateRangeNumbers(0 ,8 ,"Please enter the table number to display the invoice:", "Try again. An integer input between 0 and 8 is required.");

        if (orderNumberByTable[tableNumberInput] == 0) {
            System.out.println("This table has no active order."); // No order for the table.
        } else {
            double totalToPay = 0; // Initialize total payment amount.
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("d-MMM-yyyy");
            String formatedDate = LocalDate.now().format(newFormat); // Format current date for display.

            // Print order and table details header.
            System.out.printf("%-10s %-53d %-10s %-10d %n", "Order# ", orderNumberByTable[tableNumberInput], "Table# ", tableNumberInput);
            System.out.printf("%-10s %-53s %-10s %-8s %n", "Date:  ", formatedDate, "Staff: ",staffTableAllocation[tableNumberInput]);

            // Column headers for item list of order.
            System.out.println();
            System.out.printf("%-10s %-53s %-10s %-10s %-10s %n", "Item#", "Name", "Qty", "Price", "Sub-total");
            
            // Iterate through orders, calculating subtotals and adding to total.
            for (int i = 0; i < orderByTableNumber[tableNumberInput].length; i += 2) {
                // Extract order details and calculate subtotal.
                if (orderByTableNumber[tableNumberInput][i] > 0) {
                    int itemNumber = orderByTableNumber[tableNumberInput][i];
                    double price = getPriceByItemNumber(itemNumber);
                    int quantity = orderByTableNumber[tableNumberInput][i + 1];
                    double subtotal = price * quantity;
                    totalToPay += subtotal;
                    // Append comments to item name if present.
                    String currentComment = commentsByTableNumber[tableNumberInput][i];
                    String name = getNameByItemNumber(itemNumber);
                    String nameAndComment = name;
                    if (!currentComment.equals("")){
                        nameAndComment += " (" + currentComment + ")";
                    }
                    // Print item details.
                    System.out.printf("%-10d %-53s %-10d £%-9.2f £%-10.2f %n", itemNumber/10, nameAndComment, quantity, price, subtotal);
                }
            }
            // Calculate and display VAT and net amount.
            double vat = calculateVat(totalToPay, 0.2);
            double netAmount = totalToPay - calculateVat(totalToPay, 0.2);
            System.out.println("");
            System.out.printf("%-75s %-10s £%-10.2f %n", "", "Total ", totalToPay);
            System.out.printf("%-75s %-8s £%-10.2f %n", "", "VAT (20%) ", vat);
            System.out.printf("%-75s %-8s £%-10.2f %n", "", "Net amount", netAmount);
            // Prompt for payment and process accordingly.
            String answer = yesOrNoInput("Would you like to pay for you order? y/n", "Try again. Please enter only one character 'y' or 'n', depending on your choice.");
            if (answer.equalsIgnoreCase("y")) {
                    emptyTableByOrderNumber(tableNumberInput); // Clear order if paid.
                    System.out.println("Thank you for the payment! Have a lovely day!");
            } else {
                if (tableNumberInput == 0) {   
                    // Handle takeaway orders separately.
                    emptyTableByOrderNumber(tableNumberInput);
                    orderNumber--; // Decrement order number for takeaways not finalized.
                }
            }
        }
    }
}
