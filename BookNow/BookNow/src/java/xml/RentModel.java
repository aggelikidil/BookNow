package xml;

import entities.RentPojo;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


public class RentModel {
     // 1. properties
   @XmlAttribute public Long id;
   @XmlElement public String date_from;
   @XmlElement public String date_to;
   @XmlElement public Long user_id;
   
   public RentModel(RentPojo pojo) {
       id = pojo.getId();
       date_from = pojo.getDate_from();
       date_to = pojo.getDate_to();
   }
   
}
