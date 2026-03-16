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

    public Medicine() {
        this.genericName  = "";
        this.brandName    = "";
        this.description  = "";
        this.mg           = 0.0;
    }

    public Medicine(String genericName, String brandName, String description, double mg) {
        this.genericName  = genericName;
        this.brandName    = brandName;
        this.description  = description;
        this.mg           = mg;
    }

    public String getGenericName()  { return genericName;  }
    public String getBrandName()    { return brandName;    }
    public String getDescription()  { return description;  }
    public double getMg()           { return mg;           }

    public void setGenericName(String genericName)   { this.genericName  = genericName;  }
    public void setBrandName(String brandName)       { this.brandName    = brandName;    }
    public void setDescription(String description)   { this.description  = description;  }
    public void setMg(double mg)                     { this.mg           = mg;           }

    @Override
    public String toString() {
        return String.format(
            "[Generic: %s | Brand: %s | Desc: %s | Dosage: %.1f mg]",
            genericName, brandName, description, mg
        );
    }

}
