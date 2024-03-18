import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Options {
    private final ArrayList<Category> productList;
    private final ArrayList<Category> shoppingList;
    public Options(ArrayList<Category> pl, ArrayList<Category> sl) {
        this.productList = new ArrayList<>(pl);
        this.shoppingList = new ArrayList<>(sl);
    }

    private void showCategories(ArrayList<Category> list) {
        if (list.size() == 0) {
            System.out.println("Lista jest pusta.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).getCategoryName());
            }
        }
    }

    private void showProducts(Category category) {
        for (Product product : category.getProducts()) {
            System.out.println("- " + product.getProductName());
        }
    }

    private void showList(ArrayList<Category> list) {
        if (list.size() == 0) {
            System.out.println("Lista jest pusta.");
        } else {
            for (Category category : list) {
                System.out.println(category.getCategoryName());
                showProducts(category);
            }
        }
    }

    private void addProductToShoppingList(Scanner scanner) {
        System.out.println("Dodawanie produktu do listy zakupów");
        System.out.println("Dostępne kategorie:");
        showCategories(productList);

        int numberOfCategory;
        int numberOfProduct;
        try {
            System.out.print("Wybierz kategorię: ");
            numberOfCategory = scanner.nextInt();

            showProducts(productList.get(numberOfCategory - 1));

            System.out.print("Wybierz produkt: ");
            numberOfProduct = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Podano nieprawidłową wartość.");
        }

        String categoryName = productList.get(numberOfCategory - 1).getCategoryName();
        String productName = productList.get(numberOfCategory - 1).getProducts().get(numberOfProduct - 1).getProductName();

        int indexOfCategory = -1;
        for (Category category : shoppingList) {
            if (category.getCategoryName().equals(categoryName)) {
                indexOfCategory++;
                break;
            }
        }

        if (indexOfCategory == -1) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.addProduct(productName);
            shoppingList.add(category);
        } else {
            Category category = shoppingList.get(indexOfCategory);
            category.addProduct(productName);
        }

        System.out.println("Dodano produkt do listy zakupów.");

    }

    private void showWholeProductsOfShoppingList() {
        if (shoppingList.size() == 0) {
            System.out.println("Lista jest pusta.");
        } else {
            System.out.println("Cała lista zakupów:");
            showList(shoppingList);
        }
    }

    private void showSelectedCategoryShoppingList(Scanner scanner) {
        if (shoppingList.size() == 0) {
            System.out.println("Lista jest pusta.");
        } else {
            System.out.println("Dostępne kategorie:");
            showCategories(shoppingList);

            System.out.print("\nWybierz kategorię: ");
            int numberOfCategory;
            try {
                numberOfCategory = scanner.nextInt();
            } catch (Exception e) {
                throw new IllegalArgumentException("Podano nieprawidłową wartość.");
            }

            showProducts(shoppingList.get(numberOfCategory - 1));
        }
    }

    private void deleteWholeProductsOfShoppingList() {
        if (shoppingList.size() == 0) {
            System.out.println("Lista jest pusta.");
        } else {
            shoppingList.clear();
            System.out.println("Usunięto wszystkie produkty z listy zakupów.");
        }
    }

    private void deleteOneCategoryOfShoppingList(Scanner scanner) {
        if (shoppingList.size() == 0) {
            System.out.println("Lista jest pusta.");
        } else {
            System.out.println("Kategorie:");
            showCategories(shoppingList);

            System.out.print("Wybierz kategorię: ");
            int numberOfCategory;
            try {
                numberOfCategory = scanner.nextInt();
            } catch (Exception e) {
                throw new IllegalArgumentException("Podano nieprawidłową wartość.");
            }

            shoppingList.remove(numberOfCategory - 1);

            System.out.println("Usunięto produkty z jednej kategorii z listy zakupów.");
        }
    }

    private void deleteOneProductOfShoppingList(Scanner scanner) {
        if (shoppingList.size() == 0) {
            System.out.println("Lista jest pusta.");
        } else {
            System.out.println("Lista zakupów:");
            showList(shoppingList);
            int numberOfCategory;
            int numberOfProduct;
            try {
                System.out.print("Wybierz kategorię: ");
                numberOfCategory = scanner.nextInt();

                System.out.print("Wybierz produkt: ");
                numberOfProduct = scanner.nextInt();
            } catch (Exception e) {
                throw new IllegalArgumentException("Podano nieprawidłową wartość.");
            }

            ArrayList<Product> products = shoppingList.get(numberOfCategory - 1).getProducts();
            products.remove(numberOfProduct - 1);

            if (products.size() == 0) {
                shoppingList.remove(numberOfCategory - 1);
            }

            System.out.println("Usunięto produkt z listy zakupów.");
        }
    }

    private void saveShoppingListOnDisk() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("shopping_list.txt"));

        for (Category category : shoppingList) {
            bw.write(category.getCategoryName() + ":\n");
            for (Product product : category.getProducts()) {
                bw.write(product.getProductName() + "\n");
            }
        }

        bw.close();

        System.out.println("Zapisano listę na dysku w pliku shopping_list.txt");
    }

    public void chooseOption(int option, Scanner scanner) throws IOException {
        if (option == 1) {
            addProductToShoppingList(scanner);
        } else if (option == 2) {
            showWholeProductsOfShoppingList();
        } else if (option == 3) {
            showSelectedCategoryShoppingList(scanner);
        } else if (option == 4) {
            deleteWholeProductsOfShoppingList();
        } else if (option == 5) {
            deleteOneCategoryOfShoppingList(scanner);
        } else if (option == 6) {
            deleteOneProductOfShoppingList(scanner);
        } else if (option == 7) {
            saveShoppingListOnDisk();
        }
    }


}
