package entities;


public class UserHasOfferPojo {
    // 1. properties
    private Long offer_id;
    private Long user_id;
    
    // 2. default c-tor

    public UserHasOfferPojo() {
    
    }
    
    // 3. user-defined c-tor 
    public UserHasOfferPojo(Long offer_id, Long user_id) {
        this.offer_id = offer_id;
        this.user_id = user_id;
    }
    
    // 4. getters / setters 
    public Long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Long offer_id) {
        this.offer_id = offer_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    
    // 5. to html
    public String toHTML() {
        String html = "";
        
         html += "<li>" + "Offer id : " + offer_id + "</li>";
         html += "<li>" + "User id : " + user_id + "</li>";
         
        return html;
    }
}
