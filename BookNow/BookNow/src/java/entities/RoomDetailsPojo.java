package entities;


public class RoomDetailsPojo {
    
    // 1. properties
    private Long id;
    private Integer beds;
    private Boolean smoking;
    private Boolean pets;
    private Boolean event;
    private Boolean tv;
    private Boolean wifi;
    private Boolean heat;
    private Boolean aircondition;
    private Boolean parking;
    private Boolean elevator;
    private Integer minimum_dates;
    private String neighbourhood;
    private String public_transport;
    private String host_review;
    private Boolean confirmation;
    
    // 2. default c-tor
    public RoomDetailsPojo() {
        
    }
    
    // 3. user-defined c-tor 
    public RoomDetailsPojo(Long id, Integer beds, Boolean smoking, Boolean pets, Boolean event, Boolean tv, Boolean wifi, Boolean heat, Boolean aircondition, Boolean parking, Boolean elevator, Integer minimum_dates, String neighbourhood, String public_transport, String host_review, Boolean confirmation) {
        this.id = id;
        this.beds = beds;
        this.smoking = smoking;
        this.pets = pets;
        this.event = event;
        this.tv = tv;
        this.wifi = wifi;
        this.heat = heat;
        this.aircondition = aircondition;
        this.parking = parking;
        this.elevator = elevator;
        this.minimum_dates = minimum_dates;
        this.neighbourhood = neighbourhood;
        this.public_transport = public_transport;
        this.host_review = host_review;
        this.confirmation = confirmation;
    }
    
    // 4. getters / setters 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public Boolean getPets() {
        return pets;
    }

    public void setPets(Boolean pets) {
        this.pets = pets;
    }

    public Boolean getEvent() {
        return event;
    }

    public void setEvent(Boolean event) {
        this.event = event;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getHeat() {
        return heat;
    }

    public void setHeat(Boolean heat) {
        this.heat = heat;
    }

    public Boolean getAircondition() {
        return aircondition;
    }

    public void setAircondition(Boolean aircondition) {
        this.aircondition = aircondition;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getElevator() {
        return elevator;
    }

    public void setElevator(Boolean elevator) {
        this.elevator = elevator;
    }

    public Integer getMinimum_dates() {
        return minimum_dates;
    }

    public void setMinimum_dates(Integer minimum_dates) {
        this.minimum_dates = minimum_dates;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getPublic_transport() {
        return public_transport;
    }

    public void setPublic_transport(String public_transport) {
        this.public_transport = public_transport;
    }

    public String getHost_review() {
        return host_review;
    }

    public void setHost_review(String host_review) {
        this.host_review = host_review;
    }

    public Boolean getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Boolean confirmation) {
        this.confirmation = confirmation;
    }
    
    // 5. to html
    public String toHTML() {
        String html = "";
        
        html += "<li>" + "Id : " + id + "</li>";
        html += "<li>" + "Beds : " + beds + "</li>";
        html += "<li>" + "Smoking : " + smoking + "</li>";
        html += "<li>" + "Pets : " + pets + "</li>";
        html += "<li>" + "Event : " + event + "</li>";
        html += "<li>" + "Tv : " + tv + "</li>";
        html += "<li>" + "Wifi : " + wifi + "</li>";
        html += "<li>" + "Heat : " + heat + "</li>";
        html += "<li>" + "Aircondition : " + aircondition + "</li>";
        html += "<li>" + "Parking : " + parking + "</li>";
        html += "<li>" + "Elevator : " + elevator + "</li>";
        html += "<li>" + "Minimum dates : " + minimum_dates + "</li>";
        html += "<li>" + "Neighbourhood : " + neighbourhood + "</li>";
        html += "<li>" + "Public transport : " + public_transport + "</li>";
        html += "<li>" + "Host review : " + host_review + "</li>";
        html += "<li>" + "Confirmation : " + confirmation + "</li>";
        
        return html;
    }
}
