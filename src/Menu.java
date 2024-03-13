import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void showMenu() {
        System.out.println("Wybierz co chcesz zrobić:");
        System.out.println("1. Dodaj produkt do listy.");
        System.out.println("2. Wyświetl wszystkie produkty z listy.");
        System.out.println("3. Wyświetl wszystkie produkty z danej kategorii.");
        System.out.println("4. Usuń wszystkie produkty z listy.");
        System.out.println("5. Usuń wszystkie produkty z danej kategorii.");
        System.out.println("6. Usuń produkt z listy zakupów.");
        System.out.println("7. Zapisz listę zakupów na dysku.");
    }

    public int chooseOption() {
        System.out.print("Podaj numer opcji: ");

        Scanner scanner = new Scanner(System.in);
        int option;
        try {
            option = scanner.nextInt();
            if (option <= 0 || option >= 8) {
                throw new IllegalArgumentException("Nieprawidłowy numer opcji!");
            }
        } catch (InputMismatchException e) {
            throw new NumberFormatException("Podana wartość nie jest liczbą!");
        }
        scanner.close();

        return option;
    }

}
