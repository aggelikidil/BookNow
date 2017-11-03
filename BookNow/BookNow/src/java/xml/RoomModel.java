package xml;

import entities.RoomPojo;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


public class RoomModel {

    @XmlAttribute public Long id;
    @XmlElement public Long owner_id;
    @XmlElement public Long room_details;
    @XmlElement public String room_type;
    @XmlElement public Integer max_cost;
    @XmlElement public String address;
    @XmlElement public Integer area;
    @XmlElement public String latitude;
    @XmlElement public String longitude;
    @XmlElement public Integer max_people;
    @XmlElement public Integer min_cost;
    @XmlElement public Integer extra_cost_per_person;
    @XmlElement public String thumbnail_url;
    @XmlElement public String description;
    @XmlElement public Integer bathrooms;
    @XmlElement public Integer bedrooms;
    @XmlElement public Boolean living_room;
    @XmlElement public Boolean kitchen;
    @XmlElement public RoomDetailsModel roomDetails;
    
    
    @XmlElementWrapper(name = "rents")
    @XmlElement(name = "rent")
    public List<RentModel> rents = new ArrayList<>(); 
    
    @XmlElementWrapper(name = "ratings")
    @XmlElement(name = "rating")
    public List<RatingModel> ratings = new ArrayList<>(); 

    public RoomModel(RoomPojo object) {
        if (object != null) {
            this.id = object.getId();
            this.owner_id = object.getOwner_id();
            this.room_details = object.getRoom_details();
            this.address = object.getAddress();
            this.area=object.getArea();
            this.latitude=object.getLatitude();
            this.longitude=object.getLongitude();
            this.max_people=object.getMax_people();
            this.min_cost=object.getMin_cost();
            this.extra_cost_per_person=object.getExtra_cost_per_person();
            this.thumbnail_url=object.getThumbnail_url();
            this.description=object.getDescription();
            this.bathrooms=object.getBathrooms();
            this.bedrooms=object.getBedrooms();
            this.living_room=object.getLiving_room();
            this.kitchen=object.getKitchen();
                    
        }
    }
}
