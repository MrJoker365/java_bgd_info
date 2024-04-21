package com.ldubgd.info.jpa.converter;

//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.util.Arrays;
//import java.util.List;

//@Converter(autoApply = true)
//public class ListToArrayConverter implements AttributeConverter<List<String>, String[]> {
//    @Override
//    public String[] convertToDatabaseColumn(List<String> attribute) {
//        return attribute != null ? attribute.toArray(new String[0]) : null;
//    }
//
//    @Override
//    public List<String> convertToEntityAttribute(String[] dbData) {
//        return dbData != null ? Arrays.asList(dbData) : null;
//    }
//}

//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.sql.Array;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//
//@Converter(autoApply = true)
//public class ListToArrayConverter implements AttributeConverter<List<String>, Array> {
//    @Override
//    public Array convertToDatabaseColumn(List<String> attribute) {
//        if (attribute == null || attribute.isEmpty()) {
//            return null;
//        }
//        try {
//            return connection.createArrayOf("text", attribute.toArray());
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to convert list to database column", e);
//        }
//    }
//
//    @Override
//    public List<String> convertToEntityAttribute(Array dbData) {
//        if (dbData == null) {
//            return null;
//        }
//        try {
//            String[] strings = (String[]) dbData.getArray();
//            return Arrays.asList(strings);
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to convert database column to list", e);
//        }
//    }
//}




import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter(autoApply = true)
public class ListToStringConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return String.join(",", list);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return Arrays.asList(string.split(","));
    }
}