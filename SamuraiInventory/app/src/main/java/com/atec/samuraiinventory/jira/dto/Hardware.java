package com.atec.samuraiinventory.jira.dto;

import java.util.Objects;

public class Hardware {
    private String key;
    private String name;
    private String details;
    private String responsible;
    private String department;
    private String placement;
    private String price;
    private String status;
    private String comment;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", responsible='" + responsible + '\'' +
                ", department='" + department + '\'' +
                ", placement='" + placement + '\'' +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return Objects.equals(key, hardware.key) &&
                Objects.equals(name, hardware.name) &&
                Objects.equals(details, hardware.details) &&
                Objects.equals(responsible, hardware.responsible) &&
                Objects.equals(department, hardware.department) &&
                Objects.equals(placement, hardware.placement) &&
                Objects.equals(price, hardware.price) &&
                Objects.equals(status, hardware.status) &&
                Objects.equals(comment, hardware.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, details, responsible, department, placement, price, status, comment);
    }
}
