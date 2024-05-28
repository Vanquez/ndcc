package com.kindsonthegenius.fleetmsv2.assetm.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;


import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AssetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  asset_category_id;
    private String asset_category_name;
    private String asset_category_description;






}
