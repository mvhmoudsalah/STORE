
package javaproject;


public class DigitalDownload extends DigitalProduct {

    protected String format;
    protected int downloadLimit;

    public DigitalDownload(String format, int downloadLimit, double fileSize, String downloadLink,
            int id, String name, double price, Discount discount, String description, int quantity ,int rating ,int reviews) {
        super(fileSize, downloadLink, id, name, price, discount, description, quantity,rating,reviews);
        this.format = format;
        this.downloadLimit = downloadLimit;
    }
    
     @Override
    public void download() {

    if(downloadLimit > 0){
        downloadLimit--;
    }
    else{
        System.out.println("Download limit reached");
    }
} 
    
}
