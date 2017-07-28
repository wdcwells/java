package com.wdc.learning.mybatise.domain.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdachong on 2017/7/28.
 */
public class Computer {
    private Integer id;
    private String name;
    private BrandEnum brand;
    private TypeEnum type;
    private ScaleEnum scale;

    public enum BrandEnum {
        Lenovo(1, "联想"),
        HP(2, "惠普");

        private int code;
        private String name;

        BrandEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        static Map<String, BrandEnum> nameEnumMap = new HashMap<>();
        static {
            Arrays.stream(BrandEnum.values()).forEach(sexEnum -> nameEnumMap.put(sexEnum.name, sexEnum));
        }

        public static BrandEnum getByName(String name) {
            return nameEnumMap.get(name);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum TypeEnum {
        NoteBook(1, "笔记本"),
        TaiShiPC(2, "台式电脑");

        private int code;
        private String name;

        TypeEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        static Map<Integer, TypeEnum> codeEnumMap = new HashMap<>();
        static {
            Arrays.stream(TypeEnum.values()).forEach(typeEnum -> codeEnumMap.put(typeEnum.code, typeEnum));
        }

        public static TypeEnum getByCode(Integer code) {
            return codeEnumMap.get(code);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum ScaleEnum {
        Super(1, "巨型"),
        Big(2, "大型"),
        Small(3, "小型");

        private int code;
        private String name;

        ScaleEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandEnum getBrand() {
        return brand;
    }

    public void setBrand(BrandEnum brand) {
        this.brand = brand;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public ScaleEnum getScale() {
        return scale;
    }

    public void setScale(ScaleEnum scale) {
        this.scale = scale;
    }
}
