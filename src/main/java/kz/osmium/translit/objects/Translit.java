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

package kz.osmium.translit.objects;

public class Translit {

    /**
     * Переводит кирилицу в латиницу
     *
     * @param str - контент
     * @return
     */
    public static String сyrlToLatn(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int a : str.toCharArray())
            if (a != 1066 &&
                    a != 1098 &&
                    a != 1068 &&
                    a != 1100)
                switch (a) {
                    case 1063: // Ч
                        stringBuilder.append((char) 67 + "" + (char) 104);
                        break;
                    case 1095: // ч
                        stringBuilder.append((char) 99 + "" + (char) 104);
                        break;
                    case 1064: // Ш
                        stringBuilder.append((char) 83 + "" + (char) 104);
                        break;
                    case 1096: // ш
                        stringBuilder.append((char) 115 + "" + (char) 104);
                        break;
                    case 1071: // Я
                        stringBuilder.append((char) 73 + "" + (char) 97);
                        break;
                    case 1103: // я
                        stringBuilder.append((char) 305 + "" + (char) 97);
                        break;
                    case 1070: // Ю
                        stringBuilder.append((char) 73 + "" + (char) 253);
                        break;
                    case 1102: // ю
                        stringBuilder.append((char) 305 + "" + (char) 253);
                        break;
                    case 1062: // Ц
                        stringBuilder.append((char) 84 + "" + (char) 115);
                        break;
                    case 1094: // ц
                        stringBuilder.append((char) 116 + "" + (char) 115);
                        break;
                    case 1025: // Ё
                        stringBuilder.append((char) 73 + "" + (char) 111);
                        break;
                    case 1105: // ё
                        stringBuilder.append((char) 305 + "" + (char) 111);
                        break;
                    default:
                        stringBuilder.append(getCyrl(a));
                        break;
                }

        return stringBuilder.toString();
    }

    /**
     * Переводит латиницу в кирилицу
     *
     * @param str - контент
     * @return
     */
    public static String latnToCyrl(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (i + 1 != str.length()) {

                if (str.charAt(i) == 67 ||
                        str.charAt(i) == 99) { // Ч

                    if (str.charAt(i + 1) == 104) {

                        if (str.charAt(i) == 67)
                            stringBuilder.append((char) 1063);
                        else
                            stringBuilder.append((char) 1095);

                        i++;
                    }
                } else if (str.charAt(i) == 73 ||
                        str.charAt(i) == 305) { // Ю и Я

                    if (str.charAt(i + 1) == 253) { // Ю

                        if (str.charAt(i) == 73)
                            stringBuilder.append((char) 1070);
                        else
                            stringBuilder.append((char) 1102);

                        i++;
                    } else if (str.charAt(i + 1) == 97) { // Я

                        if (str.charAt(i) == 73)
                            stringBuilder.append((char) 1071);
                        else
                            stringBuilder.append((char) 1103);

                        i++;
                    } else if (str.charAt(i + 1) == 111) { // Ё

                        if (str.charAt(i) == 73)
                            stringBuilder.append((char) 1025);
                        else
                            stringBuilder.append((char) 1105);

                        i++;
                    } else {

                        stringBuilder.append(getLatn(str.charAt(i)));
                    }
                } else if (str.charAt(i) == 83 ||
                        str.charAt(i) == 115) { // Ш

                    if (str.charAt(i + 1) == 104) {

                        if (str.charAt(i) == 83)
                            stringBuilder.append((char) 1064);
                        else
                            stringBuilder.append((char) 1096);

                        i++;
                    }
                } else if (str.charAt(i) == 84 ||
                        str.charAt(i) == 116) { // Ц

                    if (str.charAt(i + 1) == 115) {

                        if (str.charAt(i) == 84)
                            stringBuilder.append((char) 1062);
                        else
                            stringBuilder.append((char) 1094);

                        i++;
                    }
                } else {

                    stringBuilder.append(getLatn(str.charAt(i)));
                }
            } else {

                stringBuilder.append(getLatn(str.charAt(i)));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Ищет и переводит символ кирилицы в латиницу
     *
     * @param a - символ
     * @return
     */
    private static char getCyrl(int a) {

        switch (a) {
            case 1040: // А
                return 65;
            case 1072: // а
                return 97;
            case 1240: // Ә
                return 193;
            case 1241: // ә
                return 225;
            case 1041: // Б
                return 66;
            case 1073: // б
                return 98;
            case 1042: // В
                return 86;
            case 1074: // в
                return 118;
            case 1043: // Г
                return 71;
            case 1075: // г
                return 103;
            case 1170: // Ғ
                return 500;
            case 1171: // ғ
                return 501;
            case 1044: // Д
                return 68;
            case 1076: // д
                return 100;
            case 1045: // Е
                return 69;
            case 1077: // е
                return 101;
            case 1046: // Ж
                return 74;
            case 1078: // ж
                return 106;
            case 1047: // З
                return 90;
            case 1079: // з
                return 122;
            case 1048: // И
                return 73;
            case 1080: // и
                return 305;
            case 1049: // Й
                return 73;
            case 1081: // й
                return 305;
            case 1050: // К
                return 75;
            case 1082: // к
                return 107;
            case 1178: // Қ
                return 81;
            case 1179: // қ
                return 113;
            case 1051: // Л
                return 76;
            case 1083: // л
                return 108;
            case 1052: // М
                return 77;
            case 1084: // м
                return 109;
            case 1053: // Н
                return 78;
            case 1085: // н
                return 110;
            case 1186: // Ң
                return 323;
            case 1187: // ң
                return 324;
            case 1054: // О
                return 79;
            case 1086: // о
                return 111;
            case 1256: // Ө
                return 211;
            case 1257: // ө
                return 243;
            case 1055: // П
                return 80;
            case 1087: // п
                return 112;
            case 1056: // Р
                return 82;
            case 1088: // р
                return 114;
            case 1057: // С
                return 83;
            case 1089: // с
                return 115;
            case 1058: // Т
                return 84;
            case 1090: // т
                return 116;
            case 1059: // У
                return 221;
            case 1091: // у
                return 253;
            case 1200: // Ұ
                return 85;
            case 1201: // ұ
                return 117;
            case 1198: // Ү
                return 218;
            case 1199: // ү
                return 250;
            case 1060: // Ф
                return 70;
            case 1092: // ф
                return 102;
            case 1061: // Х
                return 72;
            case 1093: // х
                return 104;
            case 1210: // Һ
                return 72;
            case 1211: // һ
                return 104;
            case 1067: // Ы
                return 89;
            case 1099: // ы
                return 121;
            case 1030: // І
                return 73;
            case 1110: // і
                return 105;
            case 1069: // Э
                return 69;
            case 1101: // э
                return 101;
            default:
                return (char) a;
        }
    }

    /**
     * Ищет и переводит символ латиницы в кирилицу
     *
     * @param a - символ
     * @return
     */
    private static char getLatn(int a) {

        switch (a) {
            case 65: // A
                return 1040;
            case 97: // a
                return 1072;
            case 193: // Á
                return 1240;
            case 225: // á
                return 1241;
            case 66: // B
                return 1041;
            case 98: // b
                return 1073;
            case 86: // V
                return 1042;
            case 118: // v
                return 1074;
            case 71: // G
                return 1043;
            case 103: // g
                return 1075;
            case 500: // Ǵ
                return 1170;
            case 501: // ǵ
                return 1171;
            case 68: // D
                return 1044;
            case 100: // d
                return 1076;
            case 69: // E
                return 1045;
            case 101: // e
                return 1077;
            case 74: // J
                return 1046;
            case 106: // j
                return 1078;
            case 90: // Z
                return 1047;
            case 122: // z
                return 1079;
            case 73: // I
                return 1048;
            case 305: // ı
                return 1080;
            case 75: // K
                return 1050;
            case 107: // k
                return 1082;
            case 81: // Q
                return 1178;
            case 113: // q
                return 1179;
            case 76: // L
                return 1051;
            case 108: // l
                return 1083;
            case 77: // M
                return 1052;
            case 109: // m
                return 1084;
            case 78: // N
                return 1053;
            case 110: // n
                return 1085;
            case 323: // Ń
                return 1186;
            case 324: // ń
                return 1187;
            case 79: // O
                return 1054;
            case 111: // o
                return 1086;
            case 211: // Ó
                return 1256;
            case 243: // ó
                return 1257;
            case 80: // P
                return 1055;
            case 112: // p
                return 1087;
            case 82: // R
                return 1056;
            case 114: // r
                return 1088;
            case 83: // S
                return 1057;
            case 115: // s
                return 1089;
            case 84: // T
                return 1058;
            case 116: // t
                return 1090;
            case 221: // Ý
                return 1059;
            case 253: // ý
                return 1091;
            case 85: // U
                return 1200;
            case 117: // u
                return 1201;
            case 218: // Ú
                return 1198;
            case 250: // ú
                return 1199;
            case 70: // F
                return 1060;
            case 102: // f
                return 1092;
            case 72: // H
                return 1061;
            case 104: // h
                return 1093;
            case 89: // Y
                return 1067;
            case 121: // y
                return 1099;
            case 105: // i
                return 1110;
            default:
                return (char) a;
        }
    }
}