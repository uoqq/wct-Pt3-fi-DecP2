package sit.int202.classicmodeltue.models;

public interface CartItem {
    public int getQuantity();

    public void setQuantity(int quantity);

    public double getUnitPrice();

    public double getTotal();

    public double getPercentDiscount();
}
