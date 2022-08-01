package com.example.ecommerce.ECommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pid")
    public long pid;

    @Column(name = "pname")
    public String pname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid",referencedColumnName ="cid")
    public Category category;

    @Column(name = "price")
    public double price;

    @Column(name = "description")
    public String description;

    @Column(name="imageName")
    public String imageName;

    public Product() {
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
