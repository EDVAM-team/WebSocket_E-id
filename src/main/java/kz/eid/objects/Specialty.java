package kz.eid.objects;

import com.google.gson.annotations.SerializedName;

public class Specialty {
    @SerializedName("id_group")
    private final int idGroup;
    @SerializedName("name")
    private final String name;
    @SerializedName("id_specialty")
    private final int idSpecialty;

    public Specialty(int idGroup, String name, int idSpecialty) {
        this.idGroup = idGroup;
        this.name = name;
        this.idSpecialty = idSpecialty;
    }
}
