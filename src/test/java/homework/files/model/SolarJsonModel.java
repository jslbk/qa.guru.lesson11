package homework.files.model;

import java.util.List;

public class SolarJsonModel {

    public String model;
    public String name;
    public double price;
    public Integer warranty;
    public List<String> color;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warrany) {
        this.warranty = warrany;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
