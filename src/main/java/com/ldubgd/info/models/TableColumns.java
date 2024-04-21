package com.ldubgd.info.models;

import com.fasterxml.jackson.databind.JsonNode;
//import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;

@Entity
@Data
//@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class TableColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    @SequenceGenerator(name = "columnId_generator", sequenceName = "columnId_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "columnId_generator")
    private Long columnId;

    /*TODO треба ще одну колонку column_id*/


    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
//    @Type(value = JsonNodeBinaryType.class)
    @Column(columnDefinition = "jsonb")
//    @Type(type = "jsonb")
    private JsonNode json;

    @ManyToOne
    @JoinColumn(name = "info_tables_id")
    private InfoTables infoTables;
}

