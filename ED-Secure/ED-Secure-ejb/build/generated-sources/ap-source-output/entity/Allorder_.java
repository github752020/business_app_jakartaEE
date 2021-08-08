package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-04T05:43:16")
@StaticMetamodel(Allorder.class)
public class Allorder_ { 

    public static volatile SingularAttribute<Allorder, Double> total;
    public static volatile SingularAttribute<Allorder, Double> quantity;
    public static volatile SingularAttribute<Allorder, String> cusid;
    public static volatile SingularAttribute<Allorder, Boolean> paid;
    public static volatile SingularAttribute<Allorder, String> orid;
    public static volatile SingularAttribute<Allorder, Boolean> delivered;
    public static volatile SingularAttribute<Allorder, String> dedate;
    public static volatile SingularAttribute<Allorder, String> ordate;

}