import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PolynomialSolver {

    public static void main(String[] args) {
        try {
            // Step 1: Parse the JSON file
            String jsonData = readFileAsString("testcase.json");  // Adjust path as needed
            JSONObject jsonObject = new JSONObject(jsonData);
            
            // Step 2: Extract n, k, and root data from JSON
            JSONObject keys = jsonObject.getJSONObject("keys");
            int n = keys.getInt("n");
            int k = keys.getInt("k");

            List<Integer> xValues = new ArrayList<>();
            List<Integer> yValues = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                JSONObject root = jsonObject.getJSONObject(String.valueOf(i));
                int base = root.getInt("base");
                String value = root.getString("value");
                
                // Decode y value based on its base
                int y = Integer.parseInt(value, base);
                xValues.add(i);  // Key 'i' represents x
                yValues.add(y);
            }

            // Step 3: Find the constant term `c` of the polynomial
            int constantTerm = findConstantTerm(xValues, yValues, k);
            System.out.println("The constant term (c) of the polynomial is: " + constantTerm);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to read the JSON file content as a string
    private static String readFileAsString(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter("\\Z");
        String content = scanner.next();
        scanner.close();
        return content;
    }

    // Method to compute the constant term using Lagrange interpolation
    private static int findConstantTerm(List<Integer> xValues, List<Integer> yValues, int k) {
        // Using Lagrange interpolation to calculate the constant term
        double result = 0;

        for (int i = 0; i < k; i++) {
            double term = yValues.get(i);
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    term *= (0 - xValues.get(j)) / (double)(xValues.get(i) - xValues.get(j));
                }
            }
            result += term;
        }

        // Cast to int as we are told coefficients are integers
        return (int) Math.round(result);
    }
}
