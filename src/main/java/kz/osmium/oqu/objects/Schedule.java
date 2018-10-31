/*
 * Copyright 2018 Osmium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kz.osmium.oqu.objects;

import com.google.gson.annotations.SerializedName;

public class Schedule {
    @SerializedName("id_schedule")
    private int id_schedule;
    @SerializedName("d")
    private int d;
    @SerializedName("num")
    private int num;
    @SerializedName("subject")
    private String subject;
    @SerializedName("type")
    private int type;
    @SerializedName("id_teacher")
    private int id_teacher;
    @SerializedName("name")
    private String name;
    @SerializedName("s_name")
    private String s_name;
    @SerializedName("l_name")
    private String l_name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("room")
    private String room;
    @SerializedName("change")
    private int change;

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setChange(int change) {
        this.change = change;
    }
}
