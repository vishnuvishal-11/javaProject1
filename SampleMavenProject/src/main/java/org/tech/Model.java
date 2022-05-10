package org.tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import techno.GsonSample;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
public class Model {
    private int modelNo;
    private String modelName;
    private Date PurchasedOn;
    private  String brandName;
    private List<GsonSample> ownedBy;
}
