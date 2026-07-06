public class ProductSearch {
    // Linear Search: O(n) complexity
    public static int linearSearch(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId() == targetId) return i;
        }
        return -1;
    }

    // Binary Search: O(log n) complexity
    public static int binarySearch(Product[] sortedProducts, int targetId) {
        int left = 0, right = sortedProducts.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedProducts[mid].getProductId() == targetId) return mid;
            if (sortedProducts[mid].getProductId() < targetId) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}