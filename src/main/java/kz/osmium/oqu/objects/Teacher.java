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

public class Teacher {
    @SerializedName("id_account")
    private final int idAccount;
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
    private final String idRoom;

    public Teacher(int idAccount, String name, String sName, String lName, String phone, String email, String idRoom) {
        this.idAccount = idAccount;
        this.name = name;
        this.sName = sName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.idRoom = idRoom;
    }
}
