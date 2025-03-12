package com.example.CepDemo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Entity
public class DevicePost {

    @Id
    private int dId;
    private String dName;
    private String dType;
    private String dCond;
    private String dStatus;

    public DevicePost() {}

    public DevicePost(int dId, String dName, String dType, String dCond, String dStatus) {
        this.dId = dId;
        this.dName = dName;
        this.dType = dType;
        this.dCond = dCond;
        this.dStatus = dStatus;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    public String getdCond() {
        return dCond;
    }

    public void setdCond(String dCond) {
        this.dCond = dCond;
    }

    public String getdStatus() {
        return dStatus;
    }

    public void setdStatus(String dStatus) {
        this.dStatus = dStatus;
    }

    @Override
    public String toString() {
        return "DevicePost{" +
                "dId=" + dId +
                ", dName='" + dName + '\'' +
                ", dType='" + dType + '\'' +
                ", dCond='" + dCond + '\'' +
                ", dStatus='" + dStatus + '\'' +
                '}';
    }

}
