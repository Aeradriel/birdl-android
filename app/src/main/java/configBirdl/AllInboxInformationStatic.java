package configBirdl;

/**
 * Created by Serkan on 11/11/2015.
 */
public class AllInboxInformationStatic {
    public int id;
    public String sender_name;
    public String receiver_name;
    public int sender_id;
    public int receiver_id;
    public String content;

    public AllInboxInformationStatic(int id, int sender_id, int receiver_id, String sender_name, String receiver_name, String content)
    {
        this.id = id;
        this.sender_name = sender_name;
        this.receiver_id = receiver_id;
        this.sender_id = sender_id;
        this.receiver_name = receiver_name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
