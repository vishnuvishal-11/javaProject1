package org.tech;

import techno.GsonSample;

import java.util.Date;
import java.util.List;

public record EmployeeData( int modelNo,String modelName,Date PurchasedOn,String brandName,List<Employee>ownedBy) {
}
