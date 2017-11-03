package entities;


public class RoomPojo {
    
    //1.properties
    private Long id;
    private Long owner_id;
    private Long room_details;
    private String room_type;
    private Integer max_cost;
    private String address;
    private Integer area;
    private String latitude;
    private String longitude;
    private Integer max_people;
    private Integer min_cost;
    private Integer extra_cost_per_person;
    private String thumbnail_url;
    private String description;
    private Integer bathrooms;
    private Integer bedrooms;
    private Boolean living_room;
    private Boolean kitchen;
    
    // related lists
    private RoomDetailsPojo details;
    private OfferPojo offerdetails; 
    
    // 2. default c-tor
    public RoomPojo() {

    }
         
    // 3. user-defined c-tor (alt-insert)
    public RoomPojo(Long id, Long owner_id, Long room_details, String room_type, Integer max_cost, String address, Integer area, String latitude, String longitude, Integer max_people, Integer min_cost, Integer extra_cost_per_person, String thumbnail_url, String description, Integer bathrooms, Integer bedrooms, Boolean living_room, Boolean kitchen) {
        this.id = id;
        this.owner_id = owner_id;
        this.room_details = room_details;
        this.room_type = room_type;
        this.max_cost = max_cost;
        this.address = address;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.max_people = max_people;
        this.min_cost = min_cost;
        this.extra_cost_per_person = extra_cost_per_person;
        this.thumbnail_url = thumbnail_url;
        this.description = description;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.living_room = living_room;
        this.kitchen = kitchen;
    }
    
     // 4. getters / setters (alt-insert)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Long getRoom_details() {
        return room_details;
    }

    public void setRoom_details(Long room_details) {
        this.room_details = room_details;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Integer getMax_cost() {
        return max_cost;
    }

    public void setMax_cost(Integer max_cost) {
        this.max_cost = max_cost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getMax_people() {
        return max_people;
    }

    public void setMax_people(Integer max_people) {
        this.max_people = max_people;
    }

    public Integer getMin_cost() {
        return min_cost;
    }

    public void setMin_cost(Integer min_cost) {
        this.min_cost = min_cost;
    }

    public Integer getExtra_cost_per_person() {
        return extra_cost_per_person;
    }

    public void setExtra_cost_per_person(Integer extra_cost_per_person) {
        this.extra_cost_per_person = extra_cost_per_person;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Boolean getLiving_room() {
        return living_room;
    }

    public void setLiving_room(Boolean living_room) {
        this.living_room = living_room;
    }

    public Boolean getKitchen() {
        return kitchen;
    }

    public void setKitchen(Boolean kitchen) {
        this.kitchen = kitchen;
    }
    
    // 5. to html
    public String toHTML(){
        String html = "";
        
       html += "<li>" + "Id : " + id + "</li>";
       html += "<li>" + "Owner Id : " + owner_id + "</li>";
       html += "<li>" + "Room Details : "  + room_details + "</li>";
       html += "<li>" + "Room Type : " + room_type + "</li>";
       html += "<li>" + "Max Cost : " + max_cost + "</li>";
       html += "<li>" + "Address : " + address + "</li>";
       html += "<li>" + "Area : " + area + "</li>";
       html += "<li>" + "Latitude : " + latitude + "</li>";
       html += "<li>" + "Longitude : " + longitude + "</li>";
       html += "<li>" + "Max People : " + max_people + "</li>";
       html += "<li>" + "Min Cost : " + min_cost + "</li>";
       html += "<li>" + "Extra Cost Per Person : " + extra_cost_per_person + "</li>";
       html += "<li>" + "Thumbnail Url : " + thumbnail_url + "</li>";
       html += "<li>" + "Description : " + description + "</li>";
       html += "<li>" + "Bathrooms : " + bathrooms + "</li>";
       html += "<li>" + "Bedrooms : " + bedrooms + "</li>";
       html += "<li>" + "Living Room : " + living_room + "</li>";
       html += "<li>" + "Kitchen : " + kitchen + "</li>";
       
       return html;
    }

    public RoomDetailsPojo getDetails() {
        return details;
    }

    public void setDetails(RoomDetailsPojo details) {
        this.details = details;
    }

    public OfferPojo getOfferdetails() {
        return offerdetails;
    }

    public void setOfferdetails(OfferPojo offerdetails) {
        this.offerdetails = offerdetails;
    }
 
    
}
