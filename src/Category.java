import java.util.ArrayList;

public class Category {
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(String name) {
        Product newProduct = new Product();
        newProduct.setProductName(name);
        products.add(newProduct);
    }
}
