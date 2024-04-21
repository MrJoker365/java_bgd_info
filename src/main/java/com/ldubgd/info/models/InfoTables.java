package com.ldubgd.info.models;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
//import com.ldubgd.info.jpa.converter.ListToArrayConverter;
import com.ldubgd.info.jpa.converter.ListToStringConverter;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

//@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@Entity
@Data
public class InfoTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableName;

    private String accessRight;



//    @Convert(converter = ListToArrayConverter.class)
//    @Column(name = "string_array", columnDefinition = "text[]")
//    private List<String> stringArray;


    @Convert(converter = ListToStringConverter.class)
    private List<String> buttons;


    @Convert(converter = ListToStringConverter.class)
    private List<String> searchInclude;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
    @Column(columnDefinition = "jsonb")
    private JsonNode listFormParam;


    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
    @Column(columnDefinition = "jsonb")
    private JsonNode columnsParam;

//    @OneToMany(mappedBy = "infoTables")
//    private List<TableColumns> tableColumns;

    @ManyToOne
    @JoinColumn(name = "system_managers_id")
    private SystemManagers systemManagers;
}

