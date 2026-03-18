/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PharmacyPackage2;

public class Medicine {
    private String genericName;
    private String brandName;
    private String description;
    private double mg;
    private int quantity;
    private double price;

    public Medicine() {
        this.genericName  = "";
        this.brandName    = "";
        this.description  = "";
        this.mg           = 0.0;
        this.quantity     = 0;
        this.price        = 0.0;
    }

    public Medicine(String genericName, String brandName, String description,
            double mg, int quantity, double price) {
        this.genericName  = genericName;
        this.brandName    = brandName;
        this.description  = description;
        this.mg           = mg;
        this.quantity     = quantity;
        this.price        = price;
    }

    public String getGenericName()  { return genericName;  }
    public String getBrandName()    { return brandName;    }
    public String getDescription()  { return description;  }
    public double getMg()           { return mg;           }
    public int getQuantity()        { return quantity;     }
    public double getPrice()        { return price;        }

    public void setGenericName(String genericName)   { this.genericName  = genericName;  }
    public void setBrandName(String brandName)       { this.brandName    = brandName;    }
    public void setDescription(String description)   { this.description  = description;  }
    public void setMg(double mg)                     { this.mg           = mg;           }
    public void setQuantity(int quantity)            { this.quantity     = quantity;     }
    public void setPrice(double price)               { this.price        = price;        }

    public double sellMedicine(int quantityToSell) {
        if (quantityToSell <= 0) {
            throw new IllegalArgumentException("Quantity to sell must be positive.");
        }
        if (quantityToSell > quantity) {
            throw new IllegalStateException("Insufficient stock.");
        }
        quantity -= quantityToSell;
        return quantityToSell * price;
    }

    @Override
    public String toString() {
        return String.format(
            "[Generic: %s | Brand: %s | Desc: %s | Dosage: %.1f mg | Qty: %d | Price: %.2f]",
            genericName, brandName, description, mg, quantity, price
        );
    }

}
