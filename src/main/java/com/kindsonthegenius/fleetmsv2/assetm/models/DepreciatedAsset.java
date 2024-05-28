package com.kindsonthegenius.fleetmsv2.assetm.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kindsonthegenius.fleetmsv2.security.models.Auditable;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DepreciatedAsset extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int asset_id;

    private String asset_name;
    private String asset_description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updated_at;
    private String asset_model;
    private String asset_serial_number;




//machine_asset_id
//
//
//    equipmentAsset_equioment_asset_id
//    furnitureAsset_furniture_asset_id


}
