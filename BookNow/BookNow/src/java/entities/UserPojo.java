package entities;


public class UserPojo {

    // 1. properties
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private Boolean is_admin;
    private Boolean is_host;
    private Boolean is_renter;
    private String photo_url;
    private String city;
    private String country;
    private String location;
    private String latitude;
    private String longitude;
    private Boolean is_approved;

    // 2. default c-tor
    public UserPojo() {

    }

    // 3. user-defined c-tor 
    public UserPojo(Long id, String username, String password, String firstname, String lastname, String email, String phonenumber, Boolean is_admin, Boolean is_host, Boolean is_renter, String photo_url, String city, String country, String location, String latitude, String longitude, Boolean is_approved) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.is_admin = is_admin;
        this.is_host = is_host;
        this.is_renter = is_renter;
        this.photo_url = photo_url;
        this.city = city;
        this.country = country;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.is_approved = is_approved;
    }

    // 4. getters / setters 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Boolean getIs_host() {
        return is_host;
    }

    public void setIs_host(Boolean is_host) {
        this.is_host = is_host;
    }

    public Boolean getIs_renter() {
        return is_renter;
    }

    public void setIs_renter(Boolean is_renter) {
        this.is_renter = is_renter;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Boolean getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(Boolean is_approved) {
        this.is_approved = is_approved;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    

    // 5. to html
    public String toHTML() {
        String html = "";

        html += "<li>" + "Id :  " + id + "</li>";
        html += "<li>" + "Username : " + username + "</li>";
        html += "<li>" + "Password : " + password + "</li>";
        html += "<li>" + "Firstname : " + firstname + "</li>";
        html += "<li>" + ":Lastname : " + lastname + "</li>";
        html += "<li>" + "Email : " + email + "</li>";
        html += "<li>" + "Phonenumber: " + phonenumber + "</li>";
        html += "<li>" + "Is admin : " + is_admin + "</li>";
        html += "<li>" + "Is host : " + is_host + "</li>";
        html += "<li>" + "Is renter : " + is_renter + "</li>";
        html += "<li>" + "Photo url: " + photo_url + "</li>";
        html += "<li>" + "City : " + city + "</li>";
        html += "<li>" + "Location : " + location + "</li>";
        html += "<li>" + "Latitude : " + latitude + "</li>";
        html += "<li>" + "Longitude : " + longitude + "</li>";
        html += "<li>" + "Is approved : " + is_approved + "</li>";
        
        return html;
    }

}
