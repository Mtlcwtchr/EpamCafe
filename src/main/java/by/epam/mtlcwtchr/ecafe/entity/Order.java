package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order implements Entity, Serializable {

    private static final long serialVersionUID = -9194822421646989651L;

    private int id;
    private Client customer;

    private Date orderDate;
    private boolean isPaid = false;
    private boolean isPrepared = false;
    private boolean isTaken = false;

    private final ArrayList<Meal> meals = new ArrayList<>();

    public Order(){ }
    public Order(Client customer, Date orderDate, Meal... meals){
        this.customer = customer;
        this.orderDate = orderDate;

    }
    public Order(int id, Client customer, Date orderDate, boolean isPrepared, boolean isTaken, boolean isPaid, Meal... meals){
        this(customer, orderDate, meals);
        this.id = id;
        this.isPaid = isPaid;
        this.isPrepared = isPrepared;
        this.isTaken = isTaken;
    }
    public Order(Client customer){
        this.customer = customer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public Meal getMeal(Meal meal){
        return meals.get(meals.indexOf(meal));
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }

    public void removeMeal(int id) {
        meals.stream().filter(meal -> meal.getId() == id).findAny().ifPresent(meals::remove);
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                isPaid == order.isPaid &&
                isPrepared == order.isPrepared &&
                isTaken == order.isTaken &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(meals, order.meals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, orderDate, isPaid, isPrepared, isTaken, meals);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", ordered by " + customer.getName() +
                ", ordered at " + new SimpleDateFormat("<<MM/dd/yyyy hh:mm:ss a>>").format(orderDate) +
                ", status=" + (isPaid ? "paid" : "not paid") + (isPrepared ? "prepared" : "preparing") +
                ", " + (isTaken ? "taken" : "waiting") +
                ", contains meals: " +
                meals.toString() +
                '}'
                ;
    }

}
