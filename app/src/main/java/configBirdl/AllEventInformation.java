package configBirdl;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Christophe on 17/09/2015.
 */
public class AllEventInformation {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("type")
    public String type;
    @SerializedName("min_slots")
    public int min_slots;
    @SerializedName("max_slots")
    public int max_slots;
    @SerializedName("date")
    public String date;
    //String end
    @SerializedName("desc")
    public String desc;
    @SerializedName("owner_id")
    public int owner_id;
    @SerializedName("address_id")
    public int address_id;
    //String language;
    @SerializedName("location")
    public String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMin_slots() {
        return min_slots;
    }

    public void setMin_slots(int min_slots) {
        this.min_slots = min_slots;
    }

    public int getMax_slots() {
        return max_slots;
    }

    public void setMax_slots(int max_slots) {
        this.max_slots = max_slots;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
