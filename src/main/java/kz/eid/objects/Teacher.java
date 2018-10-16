/*
 * Copyright 2018 EDVAM
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

package kz.eid.objects;

import com.google.gson.annotations.SerializedName;

public class Teacher {
    @SerializedName("id_teacher")
    private final int idTeacher;
    @SerializedName("name")
    private final String name;
    @SerializedName("s_name")
    private final String sName;
    @SerializedName("l_name")
    private final String lName;
    @SerializedName("phone")
    private final String phone;
    @SerializedName("email")
    private final String email;
    @SerializedName("id_room")
    private final int idRoom;

    public Teacher(int idTeacher, String name, String sName, String lName, String phone, String email, int idRoom) {
        this.idTeacher = idTeacher;
        this.name = name;
        this.sName = sName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.idRoom = idRoom;
    }
}
