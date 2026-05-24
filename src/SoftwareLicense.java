
package javaproject;

import java.time.LocalDate;


public class SoftwareLicense extends DigitalProduct {
    
    protected String licenseKey;
    protected LocalDate expiryDate;

    public SoftwareLicense(String licenseKey, LocalDate expiryDate,
            double fileSize, String downloadLink, int id, String name, 
            double price, Discount discount, String description, int quantity ,int rating ,int reviews) {
        
        super(fileSize, downloadLink, id, name, price, discount, description, quantity,rating,reviews);
        this.licenseKey = licenseKey;
        this.expiryDate = expiryDate;
    }
    
    public boolean activateLicense() {
        return expiryDate.isAfter(LocalDate.now());
    }

        @Override
    public void download() {
        
    }

}
