package model;

/**
 * Created by Christophe on 17/09/2015.
 */
public class AllEventInformationStatic {

    public int id;
    public String name;
    public String type;
    public int min_slots;
    public int max_slots;
    public String date;
    //public String end;
    public String desc;
    public int owner_id;
    public int address_id;
    //public String languag1e;
    public String location;

    public AllEventInformationStatic(int id, String name, String type, int min_slots, int max_slots, String date, String desc, int owner_id, int address_id, String location)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.min_slots = min_slots;
        this.max_slots = max_slots;
        this.date = date;
        this.desc = desc;
        this.owner_id = owner_id;
        this.address_id = address_id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
