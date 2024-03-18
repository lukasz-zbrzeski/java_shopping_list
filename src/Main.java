import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Category> loadList(String filename) {
        FetchData fetchData = new FetchData();
        ArrayList<Category> list = null;
        try {
            list = fetchData.fetchList(filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }

    private static int showMenu(Scanner scanner) {
        Menu menu = new Menu();
        menu.showMenu();

        int op = -1;
        try {
            op = menu.chooseOption(scanner);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return op;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Category> productsList = loadList("lista.txt");
        ArrayList<Category> shoppingList = loadList("shopping_list.txt");

        Options options = new Options(productsList, shoppingList);

        Scanner scanner = new Scanner(System.in);
        int option = showMenu(scanner);
        while (option != 8) {
            System.out.println("=====================================================");
            try {
                options.chooseOption(option, scanner);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
            if (option <= 0 || option >= 8) {
                break;
            }
            option = showMenu(scanner);
        }

        scanner.close();
    }
}
