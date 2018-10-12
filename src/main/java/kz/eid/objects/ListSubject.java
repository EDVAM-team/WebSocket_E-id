package kz.eid.objects;

import com.google.gson.annotations.SerializedName;

public class ListSubject {
    @SerializedName("id_list_subject")
    private final int idListSubject;
    @SerializedName("name")
    private final String name;

    public ListSubject(int idListSubject, String name) {
        this.idListSubject = idListSubject;
        this.name = name;
    }
}
