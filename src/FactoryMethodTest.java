public class FactoryMethodTest {
    public static void main(String[] args) {
        DocumentFactory factory = new PdfDocumentFactory();
        Document doc = factory.createDocument();
        doc.open();


        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
    }
}