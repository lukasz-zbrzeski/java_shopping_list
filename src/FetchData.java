import java.io.*;
import java.util.ArrayList;

public class FetchData {
    public ArrayList<Category> fetchList(String fileName) throws IOException {
        ArrayList<Category> list = new ArrayList<>();

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            if (line.indexOf(":") == line.length() - 1) {
                Category newCat = new Category();
                newCat.setCategoryName(line.replace(":", ""));
                list.add(newCat);
            } else {
                Category category = list.get(list.size() - 1);
                category.addProduct(line);
            }
        }

        fr.close();
        br.close();

        return list;
    }
}
