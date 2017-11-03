package entities;


public class MessagePojo {

    //1.properties
    private Long id;
    private Long user_sender;
    private Long user_receiver;
    private String text;
    private String date;
    
    private String sender_name;
    private String receiver_name;
    
    private String sender_email;
    private String receiver_email;

    //related lists
    private UserPojo user;

    //2. default c-tor
    public MessagePojo() {

    }

    //3. user-defined c-tor 
    public MessagePojo(Long id, Long user_sender, Long user_receiver, String text, String date) {
        this.id = id;
        this.user_sender = user_sender;
        this.user_receiver = user_receiver;
        this.text = text;
        this.date = date;
    }

    // 4. getters / setters 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_sender() {
        return user_sender;
    }

    public void setUser_sender(Long user_sender) {
        this.user_sender = user_sender;
    }

    public Long getUser_receiver() {
        return user_receiver;
    }

    public void setUser_receiver(Long user_receiver) {
        this.user_receiver = user_receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // 5. to html
    public String toHTML() {
        String html = "";

        html += "<li>" + "Id : " + id + "</li>";
        html += "<li>" + "User sender : " + user_sender + "</li>";
        html += "<li>" + "User receiver : " + user_receiver + "</li>";
        html += "<li>" + "Text : " + text + "</li>";
        html += "<li>" + "Date : " + date + "</li>";

        return html;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getSender_email() {
        return sender_email;
    }

    public void setSender_email(String sender_email) {
        this.sender_email = sender_email;
    }

    public String getReceiver_email() {
        return receiver_email;
    }

    public void setReceiver_email(String receiver_email) {
        this.receiver_email = receiver_email;
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    
}
