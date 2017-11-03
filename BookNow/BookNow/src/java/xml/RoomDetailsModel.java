package xml;

import entities.RoomDetailsPojo;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


public class RoomDetailsModel {
    @XmlAttribute public Long id;
    @XmlElement public Integer beds;
    @XmlElement public Boolean smoking;
    @XmlElement public Boolean pets;
    @XmlElement public Boolean event;
    @XmlElement public Boolean tv;
    @XmlElement public Boolean wifi;
    @XmlElement public Boolean heat;
    @XmlElement public Boolean aircondition;
    @XmlElement public Boolean parking;
    @XmlElement public Boolean elevator;
    @XmlElement public Integer minimum_dates;
    @XmlElement public String neighbourhood;
    @XmlElement public String public_transport;
    @XmlElement public String host_review;
    @XmlElement public Boolean confirmation;
    
     public RoomDetailsModel(RoomDetailsPojo object) {
         if (object != null) {
            this.id = object.getId();
            this.beds = object.getBeds();
            this.smoking = object.getSmoking();
            this.pets = object.getPets();
            this.event = object.getEvent();
            this.tv = object.getTv();
            this.heat = object.getHeat();
            this.aircondition = object.getAircondition();
            this.parking = object.getParking() ; 
            this.minimum_dates = object.getMinimum_dates() ;
            this.neighbourhood = object.getNeighbourhood() ;
            this.public_transport = object.getPublic_transport() ; 
            this.host_review = object.getHost_review() ;
            this.confirmation = object.getConfirmation() ;
            
         }
    }
}
