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

package kz.osmium.account.objects.gson;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("id_account")
    private final int idAccount;
    @SerializedName("f_name")
    private final String fName;
    @SerializedName("l_name")
    private final String lName;
    @SerializedName("patronymic")
    private final String patronymic;
    @SerializedName("phone")
    private final String phone;
    @SerializedName("email")
    private final String email;
    @SerializedName("id_room")
    private final int idRoom;
    @SerializedName("id_group")
    private final int idGroup;
    @SerializedName("type")
    private final int type;

    public Account(int idAccount, String fName, String lName, String patronymic, String phone, String email, int idRoom, int idGroup, int type) {
        this.idAccount = idAccount;
        this.fName = fName;
        this.lName = lName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.idRoom = idRoom;
        this.idGroup = idGroup;
        this.type = type;
    }
}
