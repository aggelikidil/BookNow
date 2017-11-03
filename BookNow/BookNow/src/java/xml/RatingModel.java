package xml;

import entities.RatingPojo;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


public class RatingModel {
     // 1. properties
   @XmlAttribute public Long id;
   @XmlElement public Integer value ;
   @XmlElement public Long room_id;
   
   public RatingModel(RatingPojo pojo) {
       id = pojo.getId();
//       room_id = pojo.getRoom_id() ;
       value = pojo.getValue() ;
   }
   
}
