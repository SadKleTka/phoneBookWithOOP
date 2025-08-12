import java.util.*;
public class phoneBook {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        HashMap<phoneBookName, phoneBookNumber> phoneBook = new HashMap<>();
        System.out.println("Press \"ENTER\" to get in phoneBook");
        scan.nextLine();
        phoneBook.put(new phoneBookName("Aman"), new phoneBookNumber("+996504513053"));
        phoneBook.put(new phoneBookName("Uzan"), new phoneBookNumber("+996707754532"));
        phoneBook.put(new phoneBookName("Daniel"), new phoneBookNumber("+996505053597"));

        while (true) {
            System.out.println("\n📱 PHONEBOOK MENU");
            System.out.println("--------------------------");
            System.out.println("If you want to add a new contact write: \"add\" ➕");
            System.out.println("If you want to find a contact write: \"find\" 🔍");
            System.out.println("If you want to see a list of contacts write: \"list\" 📋");
            System.out.println("If you want to edit some contacts write: \"edit\" ✏\uFE0F");
            System.out.println("If you want to remove some contacts write: \"remove\" ❌");
            System.out.println("If you want to exit from a phoneBook, please write: \"exit\" \uD83D\uDED1");
            String mainmenu = scan.nextLine();
            Set<String> setOfCommands = Set.of("find", "add", "list", "exit", "remove", "edit");
            if (setOfCommands.contains(mainmenu)) {
                switch (mainmenu) {
                    case "find":
                        findContact(phoneBook, scan);
                        break;

                    case "add":
                        addContact(phoneBook, scan);
                        break;

                    case "list":
                        listOfContacts(phoneBook);
                        System.out.println(enterOneOption());
                        scan.nextLine();
                        break;

                    case "remove":
                        removeContacts(phoneBook, scan);
                        break;

                    case "edit":
                        editContacts(phoneBook, scan);
                        break;



                    case "exit":
                        System.exit(0);
                        break;
                }
            }
            else {
                System.out.println("There are no commands like that. Please press \"ENTER\" to return to Main menu");
                scan.nextLine();
            }
        }


    }

    public static void findContact(HashMap<phoneBookName, phoneBookNumber> phoneBook, Scanner scan) {
        while (true) {
            System.out.println("🔍 Contact Search");
            System.out.println("\nEnter \"back\" to back in the main menu or \"exit\" to leave from the app");
            System.out.println("\nYou want to find a contact by name, or number? Enter \"name\", or \"number\"");
            String byWhat = scan.nextLine();
            if (byWhat.equalsIgnoreCase("back"))
                break;
            if (byWhat.equalsIgnoreCase("exit"))
                System.exit(0);
            if (byWhat.equalsIgnoreCase("name")) {
                System.out.print("\nWrite who's number you wanna find: ");
                phoneBookName find = new phoneBookName(scan.nextLine());
                if (find.toString().equals("back"))
                    return;
                if (find.toString().equals("exit"))
                    System.exit(0);
                phoneBookNumber number = phoneBook.get(find);
                if (number != null) {
                    System.out.println("=================================================================================================");
                    System.out.println("Founded contact:\nName: " + find + "\nNumber: " + number);
                    System.out.println("=================================================================================================");
                } else {
                    System.out.println("There are nobody with that name in your contacts.");
                }

                System.out.println(enterTwoOptions());
                String stop = scan.nextLine();
                if (stop.equalsIgnoreCase("back"))
                    break;

            } else if (byWhat.equalsIgnoreCase("number")) {
                System.out.println("\n\uD83D\uDED1❗Write number with country code \"+996\" and 9 digits after it❗");
                System.out.print("\nNumber: ");
                phoneBookNumber findByNumber = new phoneBookNumber(scan.nextLine());

                if (findByNumber.toString().equalsIgnoreCase("back"))
                    break;
                if (findByNumber.toString().equalsIgnoreCase("exit"))
                    System.exit(0);

                if (findByNumber.toString().matches("\\+996\\d{9}")) {
                    boolean found = false;
                    for (phoneBookName name : phoneBook.keySet()) {
                        if (phoneBook.get(name).equals(findByNumber)) {
                            System.out.println("=================================================================================================");
                            System.out.println("Founded contact:\nName: " + name + "\nNumber: " + findByNumber);
                            System.out.println("=================================================================================================");
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("‼️No contact found with that number‼️");
                    }
                } else {
                    System.out.println("❌ Wrong format. Try again.");
                }
            }
        }
    }


    public static void addContact(HashMap<phoneBookName, phoneBookNumber> phoneBook, Scanner scan) {
        while (true) {
            System.out.println("\n➕ Add New Contact");
            System.out.println("\nIf you want to back to the Main menu write \"back\"");
            System.out.print("\nWrite name: ");
            phoneBookName name = new phoneBookName(scan.nextLine());
            if (name.toString().isEmpty()) {
                System.out.println("\uD83D\uDED1❗This field cannot be empty‼\uFE0F");
                continue;
            }
            if (name.toString().equalsIgnoreCase("back"))
                return;
            if (phoneBook.containsKey(name)) {
                System.out.println("You already have a contact with that name\uD83D\uDEA8");
                System.out.println("Press \"ENTER\" to continue");
                scan.nextLine();
            } else {
                System.out.print("\nWrite number: ");
                phoneBookNumber number = new phoneBookNumber(scan.nextLine());
                if (phoneBook.containsValue(number)) {
                    System.out.println("You already have a contact with that number‼\uFE0F");
                    System.out.println("Press \"ENTER\" to continue");
                    scan.nextLine();
                } else {
                    if (number.toString().equalsIgnoreCase("back"))
                        return;
                    if (!number.toString().matches("\\+996\\d{9}")) {
                        System.out.println("That is invalid type of number. Press \"ENTER\" to continue.");
                        scan.nextLine();
                    } else {
                        phoneBook.put(name, number);
                        System.out.println("=================================================================================================");
                        System.out.println("You have successfully added a new contact:\nName: " + name + "\nNumber: " + number);
                        System.out.println("=================================================================================================");
                        System.out.println(enterTwoOptions());
                        String stop = scan.nextLine();
                        if (stop.equalsIgnoreCase("back"))
                            break;
                    }
                }
            }
        }
    }

    public static void listOfContacts(HashMap<phoneBookName, phoneBookNumber> phoneBook) {
        System.out.println("\n📋 Contact List:");
        System.out.println("\n=================================================================================================");
        for (phoneBookName name : phoneBook.keySet()) {
            System.out.println("Name: " + name + "\t Number: " + phoneBook.get(name));
        }
        System.out.println("=================================================================================================");
    }


    public static void removeContacts(HashMap<phoneBookName, phoneBookNumber> phoneBook, Scanner scan) {
        while(true) {
            System.out.println("\nRemove contact ❌");
            System.out.println("\nWrite \"back\" if you want to return in the Main menu");
            System.out.print("\n\nWrite name: ");
            phoneBookName name = new phoneBookName(scan.nextLine());
            if(name.toString().equalsIgnoreCase("back"))
                break;
            if (phoneBook.containsKey(name)) {
                phoneBook.remove(name);
                System.out.println("✅ Contact removed.");
                System.out.println("Do you want to see new list of contacts? Print \"yes\" or \"no\"");
                String decision = scan.nextLine();
                if (decision.equalsIgnoreCase("yes")) {
                    listOfContacts(phoneBook);
                    System.out.println(enterTwoOptions());
                    String back = scan.nextLine();
                    if(back.equalsIgnoreCase("back"))
                        break;
                } else if (decision.equalsIgnoreCase("no")) {
                    System.out.println(enterOneOption());
                    scan.nextLine();
                    break;
                } else {
                    System.out.println("There are no commands like that.");
                    System.out.println(enterTwoOptions());
                    String enter = scan.nextLine();
                    if(enter.equalsIgnoreCase("back"))
                        break;
                }
            } else {
                System.out.println("❌ Contact not found.");
                System.out.println(enterTwoOptions());
                String returnAfterError = scan.nextLine();
                if (returnAfterError.equalsIgnoreCase("back"))
                    break;
            }
        }
    }

    public static void editContacts(HashMap<phoneBookName, phoneBookNumber> phoneBook, Scanner scan) {
        while (true) {
            System.out.println("\n✏\uFE0FRedacting contacts");
            System.out.println("\nWrite \"back\" if you want to return in the Main menu");
            System.out.print("\n\nWrite a contact that you want to redact: ");
            phoneBookName name = new phoneBookName(scan.nextLine());
            if (name.toString().equalsIgnoreCase("back")) {
                break;
            }
            else if (!name.toString().isEmpty() && !name.toString().equalsIgnoreCase("back")) {
                if (phoneBook.containsKey(name)) {
                    System.out.print("\nWrite new name for contact: ");
                    phoneBookName newname = new phoneBookName(scan.nextLine());
                    if (newname.toString().isEmpty()) {
                        System.out.println("This pole cannot be empty.");
                        System.out.println(enterTwoOptions());
                        String back = scan.nextLine();
                        if (back.equalsIgnoreCase("back"))
                            break;
                        else if (back.isEmpty())
                            continue;
                        else if (!back.isEmpty() && !back.equalsIgnoreCase("back")) {
                            System.out.println("Error");
                            break;
                        }
                    }
                    else {
                        if (newname.toString().equalsIgnoreCase("back"))
                            break;
                        else {
                            if (phoneBook.containsKey(newname) && !newname.toString().equalsIgnoreCase(name.toString())) {
                                System.out.println("This name has been already added to contacts.");
                                System.out.println(enterTwoOptions());
                                String action = scan.nextLine();
                                if (action.equalsIgnoreCase("back"))
                                    break;
                                if (action.isEmpty())
                                    continue;
                            }
                        }
                    }
                    System.out.print("\nWrite new number for contact: ");
                    phoneBookNumber newnumber = new phoneBookNumber(scan.nextLine());
                    if (newnumber.toString().equalsIgnoreCase("back"))
                        break;
                    else {
                        if (!phoneBook.containsValue(newnumber)) {
                            if (newnumber.toString().matches("\\+996\\d{9}")) {
                                phoneBook.remove(name);
                                phoneBook.put(newname, newnumber);
                                System.out.println("You have successfully edited a contact: ");
                                System.out.println("\n=================================================================================================");
                                System.out.println("\nName: " + newname + "\t Number: " + phoneBook.get(newname));
                                System.out.println("\n=================================================================================================");
                                System.out.println("Do you want to see new list of contacts? Print \"yes\" or \"no\"");
                                String decision = scan.nextLine();
                                if (decision.equalsIgnoreCase("yes")) {
                                    listOfContacts(phoneBook);
                                    System.out.println(enterTwoOptions());
                                } else if (decision.equalsIgnoreCase("no")) {
                                    System.out.println(enterOneOption());
                                    break;
                                } else {
                                    System.out.println("Invalid code press \"ENTER\" to continue");
                                }
                                String back = scan.nextLine();
                                if (back.equalsIgnoreCase("back"))
                                    break;
                            } else {
                                String function1 = enterTwoOptions();
                                System.out.println("Invalid type of number." + " " + function1);
                                String action = scan.nextLine();
                                if (action.equalsIgnoreCase("back"))
                                    break;
                            }
                        } else {
                            System.out.println("This number had been already used in some contact‼\uFE0F");
                            System.out.println(enterTwoOptions());
                            String back = scan.nextLine();
                            if (back.equalsIgnoreCase("back"))
                                break;
                        }
                    }
                } else if(name.toString().isEmpty())
                    System.out.println("This pole cannot be empty.");
                System.out.println(enterTwoOptions());
                String back = scan.nextLine();
                if (back.equalsIgnoreCase("back"))
                    break;
                else {
                    String function2 = enterTwoOptions();
                    System.out.println("There are no contacts with that name." + " " + function2);
                    String back1 = scan.nextLine();
                    if (back1.equalsIgnoreCase("back"))
                        break;
                }
            }
            else  {
                System.out.println("Error");
                break;
            }
        }

    }



            public static String enterTwoOptions () {
                return "Press \"ENTER\" to continue, or enter \"back\" to return in the Main menu";
            }
            public static String enterOneOption () {
                return "Press \"ENTER\" to return in the main menu";
            }

        }