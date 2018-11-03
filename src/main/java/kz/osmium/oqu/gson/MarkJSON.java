package kz.osmium.oqu.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarkJSON {

    @SerializedName("n")
    @Expose
    private Integer n;
    @SerializedName("mark")
    @Expose
    private Integer mark;
    @SerializedName("id_account")
    @Expose
    private Integer idAccount;

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }
}
