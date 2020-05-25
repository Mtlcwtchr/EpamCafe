package by.epam.mtlcwtchr.ecafe.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Client extends Actor implements Serializable {

    private static final long serialVersionUID = -7011995140618075637L;

    private int id;
    private User user;
    private String name;
    private boolean isBanned = false;
    private int loyaltyPoints;
    private int bonuses;

    private final ArrayList<Order> orders = new ArrayList<>();

    public Client(){
    }
    public Client(User user) {
        this();
        this.user = user;
        this.name = user.getUsername();
    }
    public Client(User user, String name) {
        this(user);
        this.name = name;
    }
    public Client(User user, int id, String name, int loyaltyPoints, int bonuses, boolean isBanned){
        this(user, name);
        this.id = id;
        this.loyaltyPoints = loyaltyPoints;
        this.bonuses = bonuses;
        this.isBanned = isBanned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    public void addOrder(Order order){
        orders.add(order);
        order.setCustomer(this);
    }

    public Order removeOrder(Order order) {
        order.setCustomer(null);
        return orders.remove(orders.indexOf(order));
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                isBanned == client.isBanned &&
                loyaltyPoints == client.loyaltyPoints &&
                bonuses == client.bonuses &&
                Objects.equals(user, client.user) &&
                Objects.equals(name, client.name) &&
                Objects.equals(orders, client.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, isBanned, loyaltyPoints, bonuses, orders);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+ "{" +
                "id=" + id + ", "
                + user +
                ", name='" + name + '\'' +
                ", status:'" + (isBanned ? "banned" : "free") + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", bonuses=" + bonuses + ", "
                + orders +
                '}';
    }

}
