package kz.eid.objects;

import com.google.gson.annotations.SerializedName;

public class Specialty {
    @SerializedName("id_specialty")
    private final int idSpecialty;
    @SerializedName("name")
    private final String name;

    public Specialty(int idSpecialty, String name) {
        this.idSpecialty = idSpecialty;
        this.name = name;
    }
}
