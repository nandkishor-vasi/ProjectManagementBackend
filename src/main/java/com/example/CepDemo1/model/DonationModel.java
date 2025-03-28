package com.example.CepDemo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donations")
public class DonationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "donor_id", nullable = false)
    @JsonIgnore
    private DonorModel donor;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id", nullable = false)
    @JsonIgnore
    private DeviceModel device;

    private Date donationAcceptedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DonorModel getDonor() {
        return donor;
    }

    public void setDonor(DonorModel donor) {
        this.donor = donor;
    }

    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.device = device;
    }

    public Date getDonationAcceptedDate() {
        return donationAcceptedDate;
    }

    public void setDonationAcceptedDate(Date donationAcceptedDate) {
        this.donationAcceptedDate = donationAcceptedDate;
    }
}
