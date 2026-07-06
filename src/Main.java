public class Main {
    public static void main(String[] args) {

        Product[] products = {
                new Product(1, "Laptop", "Electronics"),
                new Product(2, "Phone", "Electronics"),
                new Product(3, "Book", "Stationery")
        };


        int resultLinear = ProductSearch.linearSearch(products, 2);
        System.out.println("Linear Search found at index: " + resultLinear);
        
        int resultBinary = ProductSearch.binarySearch(products, 2);
        System.out.println("Binary Search found at index: " + resultBinary);
    }
}