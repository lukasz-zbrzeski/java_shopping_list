import java.util.ArrayList;

public class Category {
    private String categoryName;
    private final ArrayList<Product> products = new ArrayList<>();

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(String name) {
        Product newProduct = new Product();
        newProduct.setProductName(name);
        products.add(newProduct);
    }
}
