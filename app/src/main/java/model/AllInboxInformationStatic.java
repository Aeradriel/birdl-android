package model;

/**
 * Created by Serkan on 11/11/2015.
 */
public class AllInboxInformationStatic {
    public int id;
    public String name;
    public String date;
    public int owner_id;

    public AllInboxInformationStatic(int id, String name, String date)
    {
        this.id = id;
        this.name = name;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }
}
